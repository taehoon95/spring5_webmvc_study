package spring5_webmvc_study.controller;

@SuppressWarnings("serial")
public class MemberNotFoundException extends RuntimeException{

	public MemberNotFoundException() {
	}

	public MemberNotFoundException(String message) {
		super(message);
	}
}
