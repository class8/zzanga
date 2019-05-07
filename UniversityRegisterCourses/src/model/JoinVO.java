package model;

public class JoinVO {

	String id;
	String password;
	String name;

	// 디폴트 생성자 생성
	public JoinVO() {
		super();
	}

	// id, password 생성자 
	public JoinVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	// 모든 생성자 
	public JoinVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}

	// 접근자와 설정자 생성 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
