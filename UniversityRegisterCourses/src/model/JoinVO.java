package model;

public class JoinVO {

	String id;
	String password;
	String name;

	// 叼弃飘 积己磊 积己
	public JoinVO() {
		super();
	}

	// id, password 积己磊 
	public JoinVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	// 葛电 积己磊 
	public JoinVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}

	// 立辟磊客 汲沥磊 积己 
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
