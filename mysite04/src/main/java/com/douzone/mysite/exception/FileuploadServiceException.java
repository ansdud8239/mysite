package com.douzone.mysite.exception;

public class FileuploadServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileuploadServiceException(String msg) {
		super(msg);
	}
	
	public FileuploadServiceException() {
		super("Fileupload Exception Occurs");
	}
}
