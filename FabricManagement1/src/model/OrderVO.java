package model;

public class OrderVO {

	private int o_number; // 주문번호
	private int a_number; // 거래처번호
	private String f_number; // 제품코드
	private String o_name; // 예약자명
	private String o_phone; // 예약자연락처
	private String o_address; // 배달주소
	private int o_amount; // 총수량
	private int o_price; // 총액
	private String o_status; // 주문상태
	private String o_registdate; // 주문일
	private String o_remarks; // 비고
	private String a_email; // 담당자이메일

	public OrderVO() {
		super();
	}

	public OrderVO(int o_number, int a_number, String f_number, String o_name, String o_phone, String o_address,
			int o_amount, int o_price, String o_status, String o_registdate, String o_remarks, String a_email) {
		super();
		this.o_number = o_number;
		this.a_number = a_number;
		this.f_number = f_number;
		this.o_name = o_name;
		this.o_phone = o_phone;
		this.o_address = o_address;
		this.o_amount = o_amount;
		this.o_price = o_price;
		this.o_status = o_status;
		this.o_registdate = o_registdate;
		this.o_remarks = o_remarks;
		this.a_email = a_email;
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

	public String getF_number() {
		return f_number;
	}

	public void setF_number(String f_number) {
		this.f_number = f_number;
	}

	public String getO_name() {
		return o_name;
	}

	public void setO_name(String o_name) {
		this.o_name = o_name;
	}

	public String getO_phone() {
		return o_phone;
	}

	public void setO_phone(String o_phone) {
		this.o_phone = o_phone;
	}

	public String getO_address() {
		return o_address;
	}

	public void setO_address(String o_address) {
		this.o_address = o_address;
	}

	public int getO_amount() {
		return o_amount;
	}

	public void setO_amount(int o_amount) {
		this.o_amount = o_amount;
	}

	public int getO_price() {
		return o_price;
	}

	public void setO_price(int o_price) {
		this.o_price = o_price;
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

	public String getO_remarks() {
		return o_remarks;
	}

	public void setO_remarks(String o_remarks) {
		this.o_remarks = o_remarks;
	}

	public String getA_email() {
		return a_email;
	}

	public void setA_email(String a_email) {
		this.a_email = a_email;
	}

}
