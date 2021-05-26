package spring5_webmvc_study.controller;

public class AuthInfo {
	private Long id;
	private String email;
	private String name;

	public AuthInfo() {
	}

	public AuthInfo(Long id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return String.format("AuthInfo [id=%s, email=%s, name=%s]", id, email, name);
	}
	
}
