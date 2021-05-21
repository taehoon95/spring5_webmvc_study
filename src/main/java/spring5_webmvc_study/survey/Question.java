package spring5_webmvc_study.survey;

import java.util.List;

public class Question {
	
//	title : 질문제목
//	options : 답변보기 옵션
	
	private String title;
	private List<String> options;

//	객관식
	public Question(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}

// 주관식	
	public Question(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getOptions() {
		return options;
	}
	
	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
