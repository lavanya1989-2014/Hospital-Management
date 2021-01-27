package com.hospital.mgt.restException;

public class RecordNotFoundRestException extends RuntimeException {

	public RecordNotFoundRestException() {
	}

	public RecordNotFoundRestException(String arg0) {
		super(arg0);
	}

	public RecordNotFoundRestException(Throwable arg0) {
		super(arg0);
	}

	public RecordNotFoundRestException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RecordNotFoundRestException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
