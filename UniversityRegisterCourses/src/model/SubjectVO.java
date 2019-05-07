package model;

public class SubjectVO {

	private int no;
	private String s_num;
	private String s_name;

	// 叼弃飘 积己磊 
	public SubjectVO() {
		super();
	}

	// 葛电 积己磊 
	public SubjectVO(int no, String s_num, String s_name) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.s_name = s_name;
	}

	// s_num, s_name 积己磊 
	public SubjectVO(String s_num, String s_name) {
		super();
		this.s_num = s_num;
		this.s_name = s_name;
	}

	// 立辟磊客 汲沥磊 积己
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

}
