package model;

public class SubjectVO {

	private int no;
	private String s_num;
	private String s_name;

	// 디폴트 생성자
	public SubjectVO() {
		super();
	}

	// 모든 생성자
	public SubjectVO(int no, String s_num, String s_name) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.s_name = s_name;
	}

	// s_num, s_name 생성자
	public SubjectVO(String s_num, String s_name) {
		super();
		this.s_num = s_num;
		this.s_name = s_name;
	}

	// 접근자와 설정자 생성
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

	@Override
	public String toString() {
		// return "학과명 : " + getS_name() + ", 학과 번호 : " + getS_num();
		return getS_name();
	}

}
