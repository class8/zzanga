package model;

public class OrderVO {

	private int o_number; // 주문번호
	private int a_number; // 거래처번호
	private int c_number; // 고객번호
	private String f_number; // 제품코드
	private String f_name; // 제품명
	private int o_amount; // 총수량
	private int o_total; // 총금액
	private String c_name; // 예약자명
	private String c_phone; // 예약자연락처
	private String o_email; // 이메일
	private String o_status; // 주문상태
	private String o_registdate; // 주문일
	private String o_address; // 배달주소
	private String o_remarks; // 비고

	public OrderVO() {
		super();
	}

	public OrderVO(int a_number, int c_number, String f_number, int o_amount, int o_total, String o_email,
			String o_status, String o_address, String o_remarks) {
		super();
		this.a_number = a_number;
		this.c_number = c_number;
		this.f_number = f_number;
		this.o_amount = o_amount;
		this.o_total = o_total;
		this.o_email = o_email;
		this.o_status = o_status;
		this.o_address = o_address;
		this.o_remarks = o_remarks;
	}

	public OrderVO(int o_number, int a_number, int c_number, String f_number, String f_name, int o_amount, int o_total,
			String c_name, String c_phone, String o_email, String o_status, String o_registdate, String o_address,
			String o_remarks) {
		super();
		this.o_number = o_number;
		this.a_number = a_number;
		this.c_number = c_number;
		this.f_number = f_number;
		this.f_name = f_name;
		this.o_amount = o_amount;
		this.o_total = o_total;
		this.c_name = c_name;
		this.c_phone = c_phone;
		this.o_email = o_email;
		this.o_status = o_status;
		this.o_registdate = o_registdate;
		this.o_address = o_address;
		this.o_remarks = o_remarks;
	}

	public int getO_number() {
		return o_number;
	}

	public void setO_number(int o_number) {
		this.o_number = o_number;
	}

	public int getA_number() {
		return a_number;
	}

	public void setA_number(int a_number) {
		this.a_number = a_number;
	}

	public int getC_number() {
		return c_number;
	}

	public void setC_number(int c_number) {
		this.c_number = c_number;
	}

	public String getF_number() {
		return f_number;
	}

	public void setF_number(String f_number) {
		this.f_number = f_number;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public int getO_amount() {
		return o_amount;
	}

	public void setO_amount(int o_amount) {
		this.o_amount = o_amount;
	}

	public int getO_total() {
		return o_total;
	}

	public void setO_total(int o_total) {
		this.o_total = o_total;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_phone() {
		return c_phone;
	}

	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}

	public String getO_email() {
		return o_email;
	}

	public void setO_email(String o_email) {
		this.o_email = o_email;
	}

	public String getO_status() {
		return o_status;
	}

	public void setO_status(String o_status) {
		this.o_status = o_status;
	}

	public String getO_registdate() {
		return o_registdate;
	}

	public void setO_registdate(String o_registdate) {
		this.o_registdate = o_registdate;
	}

	public String getO_address() {
		return o_address;
	}

	public void setO_address(String o_address) {
		this.o_address = o_address;
	}

	public String getO_remarks() {
		return o_remarks;
	}

	public void setO_remarks(String o_remarks) {
		this.o_remarks = o_remarks;
	}

}
