package spring5_webmvc_study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@PostMapping("/register/step2")
	public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, RegisterRequest registerRequest) {
		if(!agree) {
			return "register/step1";
		}
//		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	
	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest reqReq, Errors errors) {
		new RegisterRequestValidator().validate(reqReq, errors); 
		if(errors.hasErrors()) {
			return "register/step2";
		}
		
		try {
			memberRegisterService.regist(reqReq);
			return "register/step3";
		} catch (DupulicateMemberException e) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
}
