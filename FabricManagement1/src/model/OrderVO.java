package model;

public class OrderVO {

	private int o_number; // 주문번호
	private String a_number; // 거래처번호
	private String f_number; // 제품코드
	private String o_email; // 담당자이메일
	private String o_name; // 예약자명
	private String o_phone; // 예약자연락처
	private String o_amount; // 총수량
	private String o_price; // 총액
	private String o_adress; // 배달주소
	private String o_remarks; // 비고
	private String o_status; // 주문상태
	private String o_registdate; // 주문일

	// 디폴트 생성자
	public OrderVO() {
		super();
	}

	// 모든 생성자
	public OrderVO(int o_number, String a_number, String f_number, String o_email, String o_name, String o_phone,
			String o_amount, String o_price, String o_adress, String o_remarks, String o_status, String o_registdate) {
		super();
		this.o_number = o_number;
		this.a_number = a_number;
		this.f_number = f_number;
		this.o_email = o_email;
		this.o_name = o_name;
		this.o_phone = o_phone;
		this.o_amount = o_amount;
		this.o_price = o_price;
		this.o_adress = o_adress;
		this.o_remarks = o_remarks;
		this.o_status = o_status;
		this.o_registdate = o_registdate;
	}

	// 접근자와 설정자 생성
	public int getO_number() {
		return o_number;
	}

	public void setO_number(int o_number) {
		this.o_number = o_number;
	}

	public String getA_number() {
		return a_number;
	}

	public void setA_number(String a_number) {
		this.a_number = a_number;
	}

	public String getF_number() {
		return f_number;
	}

	public void setF_number(String f_number) {
		this.f_number = f_number;
	}

	public String getO_email() {
		return o_email;
	}

	public void setO_email(String o_email) {
		this.o_email = o_email;
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

	public String getO_amount() {
		return o_amount;
	}

	public void setO_amount(String o_amount) {
		this.o_amount = o_amount;
	}

	public String getO_price() {
		return o_price;
	}

	public void setO_price(String o_price) {
		this.o_price = o_price;
	}

	public String getO_adress() {
		return o_adress;
	}

	public void setO_adress(String o_adress) {
		this.o_adress = o_adress;
	}

	public String getO_remarks() {
		return o_remarks;
	}

	public void setO_remarks(String o_remarks) {
		this.o_remarks = o_remarks;
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

}
