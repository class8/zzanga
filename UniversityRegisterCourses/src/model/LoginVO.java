package model;

public class LoginVO { 
	
	private String id;
	
	// 디폴트 생성자 생성
	public LoginVO() {
		super();
	}

	// 모든 생성자 생성
	public LoginVO(String id) {
		super();
		this.id = id;
	}

	// 접근자와 설정자 생성 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 
	
	
	
	

}
