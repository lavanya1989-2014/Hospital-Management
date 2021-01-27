package com.hospital.mgt.restException;

public class DuplicateRecordRestException extends RuntimeException {

	public DuplicateRecordRestException() {
	}

	public DuplicateRecordRestException(String arg0) {
		super(arg0);
	}

	public DuplicateRecordRestException(Throwable arg0) {
		super(arg0);
	}

	public DuplicateRecordRestException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DuplicateRecordRestException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
