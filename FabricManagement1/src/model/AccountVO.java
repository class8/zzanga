package model;

public class AccountVO {

	private int a_number; // 거래처번호
	private String a_cname; // 거래처명
	private String a_mname; // 담당자명
	private String a_phone; // 담당자 연락처
	private String a_email; // 담당자 이메일
	private String a_bnumber; // 사업자번호
	private String a_msubject; // 주종목
	private String a_address; // 주소
	private String a_remarks = null; // 비고
	private String a_registdate; // 등록일

	public AccountVO() {
		super();
	}

	public AccountVO(int a_number, String a_cname, String a_mname, String a_phone, String a_email, String a_bnumber,
			String a_msubject, String a_address, String a_remarks, String a_registdate) {
		super();
		this.a_number = a_number;
		this.a_cname = a_cname;
		this.a_mname = a_mname;
		this.a_phone = a_phone;
		this.a_email = a_email;
		this.a_bnumber = a_bnumber;
		this.a_msubject = a_msubject;
		this.a_address = a_address;
		this.a_remarks = a_remarks;
		this.a_registdate = a_registdate;
	}

	public AccountVO(String a_cname, String a_mname, String a_phone, String a_email, String a_bnumber,
			String a_msubject, String a_address, String a_remarks) {
		super();
		this.a_cname = a_cname;
		this.a_mname = a_mname;
		this.a_phone = a_phone;
		this.a_email = a_email;
		this.a_bnumber = a_bnumber;
		this.a_msubject = a_msubject;
		this.a_address = a_address;
		this.a_remarks = a_remarks;
	}

	public AccountVO(int a_number, String a_cname, String a_mname, String a_phone, String a_email, String a_bnumber,
			String a_msubject, String a_address, String a_remarks) {
		super();
		this.a_number = a_number;
		this.a_cname = a_cname;
		this.a_mname = a_mname;
		this.a_phone = a_phone;
		this.a_email = a_email;
		this.a_bnumber = a_bnumber;
		this.a_msubject = a_msubject;
		this.a_address = a_address;
		this.a_remarks = a_remarks;
	}

	public int getA_number() {
		return a_number;
	}

	public void setA_number(int a_number) {
		this.a_number = a_number;
	}

	public String getA_cname() {
		return a_cname;
	}

	public void setA_cname(String a_cname) {
		this.a_cname = a_cname;
	}

	public String getA_mname() {
		return a_mname;
	}

	public void setA_mname(String a_mname) {
		this.a_mname = a_mname;
	}

	public String getA_phone() {
		return a_phone;
	}

	public void setA_phone(String a_phone) {
		this.a_phone = a_phone;
	}

	public String getA_email() {
		return a_email;
	}

	public void setA_email(String a_email) {
		this.a_email = a_email;
	}

	public String getA_bnumber() {
		return a_bnumber;
	}

	public void setA_bnumber(String a_bnumber) {
		this.a_bnumber = a_bnumber;
	}

	public String getA_msubject() {
		return a_msubject;
	}

	public void setA_msubject(String a_msubject) {
		this.a_msubject = a_msubject;
	}

	public String getA_address() {
		return a_address;
	}

	public void setA_address(String a_address) {
		this.a_address = a_address;
	}

	public String getA_remarks() {
		return a_remarks;
	}

	public void setA_remarks(String a_remarks) {
		this.a_remarks = a_remarks;
	}

	public String getA_registdate() {
		return a_registdate;
	}

	public void setA_registdate(String a_registdate) {
		this.a_registdate = a_registdate;
	}

}
