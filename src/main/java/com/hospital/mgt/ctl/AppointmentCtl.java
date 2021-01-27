package com.hospital.mgt.ctl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hospital.mgt.dto.AppointmentDTO;
import com.hospital.mgt.dto.PatientDTO;
import com.hospital.mgt.dto.UserDTO;
import com.hospital.mgt.exception.DuplicateRecordException;
import com.hospital.mgt.form.AppointmentForm;
import com.hospital.mgt.form.PatientForm;
import com.hospital.mgt.service.AppointmentServiceInt;
import com.hospital.mgt.service.UserServiceInt;

@Controller
@RequestMapping(value = "/ctl/Appointment")
public class AppointmentCtl extends BaseCtl {

	@Autowired
	AppointmentServiceInt appointmentService;

	@Autowired
	UserServiceInt userService;

	@Autowired
	MessageSource messageSource;

	@ModelAttribute
	public void preload(Model model) {

		UserDTO uDto = new UserDTO();
		uDto.setRoleId(2L);
		List<UserDTO> doclist = userService.search(uDto);

		model.addAttribute("docList", doclist);

		Map<String, String> statusMap = new LinkedHashMap<String, String>();
		statusMap.put("Processing", "Processing");
		statusMap.put("Cancel", "Cancel");
		statusMap.put("Confirm", "Confirm");

		model.addAttribute("statusMap", statusMap);

	}

	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") AppointmentForm form,
			Model model) {

		if (form.getId() > 0) {
			form.populate(appointmentService.findByPK(id));
		}

		return "Appointment";
		}
	
	@RequestMapping(value = "/docName",method = RequestMethod.POST)
	public String displayDoc(@RequestParam(required = false) Long id, @ModelAttribute("form") AppointmentForm form,
			Model model) {
		AppointmentDTO dto = (AppointmentDTO) form.getDto();
		UserDTO uDto=userService.findByPK(dto.getDoctorId());
		System.out.println(uDto.getDays());
		form.setDay(uDto.getDays());
		form.setTime(uDto.getTime());

		return "Appointment";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale, HttpSession session, @RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid AppointmentForm form, BindingResult bindingResult, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:Appointment";
		}

		if (OP_CANCEL.equalsIgnoreCase(operation)) {
			return "redirect:Appointment/Search";
		}
		
		

		if (OP_SAVE.equalsIgnoreCase(operation)) {

			if (bindingResult.hasErrors()) {
				System.err.println("input error");
				return "Appointment";
			}
			AppointmentDTO dto = (AppointmentDTO) form.getDto();
			UserDTO udto = userService.findByPK(dto.getDoctorId());
			dto.setDoctorName(udto.getFirstName() + " " + udto.getLastName());
			try {
				String msg = null;
				if (form.getId() > 0) {
					AppointmentDTO apDto = appointmentService.findByPK(form.getId());
					apDto.setStatus(form.getStatus());
					appointmentService.update(apDto);
					msg = messageSource.getMessage("message.success.update", null, locale);
				}
				if (form.getId() == 0) {
					
					AppointmentDTO apDto=new AppointmentDTO();
					apDto.setDoctorId(dto.getDoctorId());
					apDto.setDate(dto.getDate());
					List<AppointmentDTO> list=appointmentService.search(apDto);
					System.out.println("List Size========"+list.size());
					if(list.size()==5) {
						msg = messageSource.getMessage("message.apFull", null, locale);
						model.addAttribute("error", msg);
						return "Appointment";
					}
					dto.setStatus("Processing");
					UserDTO uDto1 = (UserDTO) session.getAttribute("userLogin");
					dto.setUserId(uDto1.getId());
					
					
					
					appointmentService.add(dto);
					msg = messageSource.getMessage("message.success.add", null, locale);
				}
				model.addAttribute("success", msg);
			} catch (DuplicateRecordException e) {
				String msg = messageSource.getMessage("message.userIsAlreadyExist", null, locale);
				model.addAttribute("error", msg);
			}
			return "Appointment";
		}
		return "Appointment";

	}

	@RequestMapping(value = "/Search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(Locale locale, @ModelAttribute("form") AppointmentForm form,
			@RequestParam(required = false) String operation, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Appointment/Search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/Appointment";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {

				for (long id : form.getIds()) {
					AppointmentDTO dto = new AppointmentDTO();
					dto.setId(id);
					appointmentService.delete(dto);
				}
				String msg = messageSource.getMessage("message.success.delete", null, locale);
				model.addAttribute("success", msg);

				// redirectAttributes.addFlashAttribute("success", msg);
				// return "UserList";

			} else {

				System.out.println("select at least one records");
				String msg = messageSource.getMessage("message.unsuccess.selectatleastonerecords", null, locale);
				model.addAttribute("error", msg);
			}

		}

		UserDTO uDto = (UserDTO) session.getAttribute("userLogin");
		AppointmentDTO dto = (AppointmentDTO) form.getDto();
		if (uDto.getRoleId() == 2) {
			dto.setDoctorName(uDto.getFirstName() + " " + uDto.getLastName());
		}
		if (uDto.getRoleId() == 3) {
			dto.setUserId(uDto.getId());
		}
		List<AppointmentDTO> list = appointmentService.search(dto, pageNo, pageSize);

		List<AppointmentDTO> totallist = appointmentService.search(null);

		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			String msg = messageSource.getMessage("message.unsuccess.recoudNotFound", null, locale);
			model.addAttribute("error", msg);
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);

		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);

		return "AppointmentList";
	}

}
