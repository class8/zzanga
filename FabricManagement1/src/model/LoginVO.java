package model;

public class LoginVO {

	private String id;
	private String passwd;

	// 디폴트 생성자
	public LoginVO() {
		super();
	}

	// 모든 생성자
	public LoginVO(String id, String passwd) {
		super();
		this.id = id;
		this.passwd = passwd;
	}

	// 접근자와 설정자 생성
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
