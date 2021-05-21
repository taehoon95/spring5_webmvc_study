package spring5_webmvc_study.controller;

@SuppressWarnings("serial")
public class DupulicateMemberException extends RuntimeException{

	public DupulicateMemberException(String message) {
		super(message);
	}
}
