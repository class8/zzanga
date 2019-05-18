package model;

public class CustomerVO {

	private int c_number; // 고객번호
	private String c_name; // 고객명
	private String c_cname; // 업체명
	private String c_phone; // 연락처
	private String c_bnumber; // 사업자번호
	private String c_address; // 주소
	private String c_email; // 이메일
	private String c_remarks; // 비고
	private String c_registdate; // 등록일

	// 디폴트 생성자
	public CustomerVO() {
		super();
	}

	// 모든 생성자
	public CustomerVO(int c_number, String c_name, String c_cname, String c_phone, String c_bnumber, String c_address,
			String c_email, String c_remarks, String c_registdate) {
		super();
		this.c_number = c_number;
		this.c_name = c_name;
		this.c_cname = c_cname;
		this.c_phone = c_phone;
		this.c_bnumber = c_bnumber;
		this.c_address = c_address;
		this.c_email = c_email;
		this.c_remarks = c_remarks;
		this.c_registdate = c_registdate;
	}

	// 고객번호와 등록일을 제외한 모든 생성자
	public CustomerVO(String c_name, String c_cname, String c_phone, String c_bnumber, String c_address, String c_email,
			String c_remarks) {
		super();
		this.c_name = c_name;
		this.c_cname = c_cname;
		this.c_phone = c_phone;
		this.c_bnumber = c_bnumber;
		this.c_address = c_address;
		this.c_email = c_email;
		this.c_remarks = c_remarks;
	}
	

	public CustomerVO(int c_number, String c_name, String c_cname, String c_phone, String c_bnumber, String c_address,
			String c_email, String c_remarks) {
		super();
		this.c_number = c_number;
		this.c_name = c_name;
		this.c_cname = c_cname;
		this.c_phone = c_phone;
		this.c_bnumber = c_bnumber;
		this.c_address = c_address;
		this.c_email = c_email;
		this.c_remarks = c_remarks;
	}

	// 접근자와 설정자 생성
	public int getC_number() {
		return c_number;
	}

	public void setC_number(int c_no) {
		this.c_number = c_no;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_cname() {
		return c_cname;
	}

	public void setC_cname(String c_cname) {
		this.c_cname = c_cname;
	}

	public String getC_phone() {
		return c_phone;
	}

	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}

	public String getC_bnumber() {
		return c_bnumber;
	}

	public void setC_bnumber(String c_bnumber) {
		this.c_bnumber = c_bnumber;
	}

	public String getC_address() {
		return c_address;
	}

	public void setC_address(String c_address) {
		this.c_address = c_address;
	}

	public String getC_email() {
		return c_email;
	}

	public void setC_email(String c_email) {
		this.c_email = c_email;
	}

	public String getC_remarks() {
		return c_remarks;
	}

	public void setC_remarks(String c_remarks) {
		this.c_remarks = c_remarks;
	}

	public String getC_registdate() {
		return c_registdate;
	}

	public void setC_registdate(String c_registdate) {
		this.c_registdate = c_registdate;
	}

}
