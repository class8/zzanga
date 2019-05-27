package model;

import java.time.LocalDate;

public class TradeVO {

	private int t_number; // 거래번호
	private String f_number; // 제품코드
	private int c_number; // 고객번호
	private String c_name; // 고객명
	private String c_phone; // 연락처
	private String t_email; // 이메일
	private int t_amount; // 총수량
	private int t_price; // 총가격
	private int t_deposit; // 선금
	private int t_penalty; // 위약금
	private int t_balance; // 잔금
	private int t_receipt; // 수령액
	private int t_unpaid; // 미납금
	private String t_status; // 거래상태
	private String t_registdate; // 거래일
	private String t_address; // 배달주소
	private String t_remarks; // 비고

	private String f_f_sort; // 종류
	private String f_f_name; // 제품명
	private String f_f_color; // 색상
	private String f_f_size; // 사이즈
	private String f_f_weight; // 중량
	private String f_f_price; // 가격
	private String f_f_phone; // 담당자연락처

	public TradeVO() {
		super();
	}

	public TradeVO(String f_number, int c_number, String c_name, String c_phone, String t_email, int t_amount,
			int t_price, String t_address, String t_remarks, String f_f_sort, String f_f_name, String f_f_color,
			String f_f_size, String f_f_weight, String f_f_price, String f_f_phone) {
		super();
		this.f_number = f_number;
		this.c_number = c_number;
		this.c_name = c_name;
		this.c_phone = c_phone;
		this.t_email = t_email;
		this.t_amount = t_amount;
		this.t_price = t_price;
		this.t_address = t_address;
		this.t_remarks = t_remarks;
		this.f_f_sort = f_f_sort;
		this.f_f_name = f_f_name;
		this.f_f_color = f_f_color;
		this.f_f_size = f_f_size;
		this.f_f_weight = f_f_weight;
		this.f_f_price = f_f_price;
		this.f_f_phone = f_f_phone;
	}

	public TradeVO(int t_number, String f_number, int c_number, String c_name, String c_phone, String t_email,
			int t_amount, int t_price, int t_deposit, int t_penalty, int t_balance, int t_receipt, int t_unpaid,
			String t_status, String t_registdate, String t_address, String t_remarks, String f_f_sort, String f_f_name,
			String f_f_color, String f_f_size, String f_f_weight, String f_f_price, String f_f_phone) {
		super();
		this.t_number = t_number;
		this.f_number = f_number;
		this.c_number = c_number;
		this.c_name = c_name;
		this.c_phone = c_phone;
		this.t_email = t_email;
		this.t_amount = t_amount;
		this.t_price = t_price;
		this.t_deposit = t_deposit;
		this.t_penalty = t_penalty;
		this.t_balance = t_balance;
		this.t_receipt = t_receipt;
		this.t_unpaid = t_unpaid;
		this.t_status = t_status;
		this.t_registdate = t_registdate;
		this.t_address = t_address;
		this.t_remarks = t_remarks;
		this.f_f_sort = f_f_sort;
		this.f_f_name = f_f_name;
		this.f_f_color = f_f_color;
		this.f_f_size = f_f_size;
		this.f_f_weight = f_f_weight;
		this.f_f_price = f_f_price;
		this.f_f_phone = f_f_phone;
	}

	public TradeVO(int t_number, String f_number, int c_number, String c_name, String c_phone, String t_email,
			int t_amount, int t_price, int t_deposit, int t_penalty, int t_balance, int t_receipt, int t_unpaid,
			String t_status, String t_registdate, String t_address, String t_remarks) {
		super();
		this.t_number = t_number;
		this.f_number = f_number;
		this.c_number = c_number;
		this.c_name = c_name;
		this.c_phone = c_phone;
		this.t_email = t_email;
		this.t_amount = t_amount;
		this.t_price = t_price;
		this.t_deposit = t_deposit;
		this.t_penalty = t_penalty;
		this.t_balance = t_balance;
		this.t_receipt = t_receipt;
		this.t_unpaid = t_unpaid;
		this.t_status = t_status;
		this.t_registdate = t_registdate;
		this.t_address = t_address;
		this.t_remarks = t_remarks;
	}

	public int getT_number() {
		return t_number;
	}

	public void setT_number(int t_number) {
		this.t_number = t_number;
	}

	public String getF_number() {
		return f_number;
	}

	public void setF_number(String f_number) {
		this.f_number = f_number;
	}

	public int getC_number() {
		return c_number;
	}

	public void setC_number(int c_number) {
		this.c_number = c_number;
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

	public String getT_email() {
		return t_email;
	}

	public void setT_email(String t_email) {
		this.t_email = t_email;
	}

	public int getT_amount() {
		return t_amount;
	}

	public void setT_amount(int t_amount) {
		this.t_amount = t_amount;
	}

	public int getT_price() {
		return t_price;
	}

	public void setT_price(int t_price) {
		this.t_price = t_price;
	}

	public int getT_deposit() {
		return t_deposit;
	}

	public void setT_deposit(int t_deposit) {
		this.t_deposit = t_deposit;
	}

	public int getT_penalty() {
		return t_penalty;
	}

	public void setT_penalty(int t_penalty) {
		this.t_penalty = t_penalty;
	}

	public int getT_balance() {
		return t_balance;
	}

	public void setT_balance(int t_balance) {
		this.t_balance = t_balance;
	}

	public int getT_receipt() {
		return t_receipt;
	}

	public void setT_receipt(int t_receipt) {
		this.t_receipt = t_receipt;
	}

	public int getT_unpaid() {
		return t_unpaid;
	}

	public void setT_unpaid(int t_unpaid) {
		this.t_unpaid = t_unpaid;
	}

	public String getT_status() {
		return t_status;
	}

	public void setT_status(String t_status) {
		this.t_status = t_status;
	}

	public String getT_registdate() {
		return t_registdate;
	}

	public void setT_registdate(String t_registdate) {
		this.t_registdate = t_registdate;
	}

	public String getT_address() {
		return t_address;
	}

	public void setT_address(String t_address) {
		this.t_address = t_address;
	}

	public String getT_remarks() {
		return t_remarks;
	}

	public void setT_remarks(String t_remarks) {
		this.t_remarks = t_remarks;
	}

	public String getF_f_sort() {
		return f_f_sort;
	}

	public void setF_f_sort(String f_f_sort) {
		this.f_f_sort = f_f_sort;
	}

	public String getF_f_name() {
		return f_f_name;
	}

	public void setF_f_name(String f_f_name) {
		this.f_f_name = f_f_name;
	}

	public String getF_f_color() {
		return f_f_color;
	}

	public void setF_f_color(String f_f_color) {
		this.f_f_color = f_f_color;
	}

	public String getF_f_size() {
		return f_f_size;
	}

	public void setF_f_size(String f_f_size) {
		this.f_f_size = f_f_size;
	}

	public String getF_f_weight() {
		return f_f_weight;
	}

	public void setF_f_weight(String f_f_weight) {
		this.f_f_weight = f_f_weight;
	}

	public String getF_f_price() {
		return f_f_price;
	}

	public void setF_f_price(String f_f_price) {
		this.f_f_price = f_f_price;
	}

	public String getF_f_phone() {
		return f_f_phone;
	}

	public void setF_f_phone(String f_f_phone) {
		this.f_f_phone = f_f_phone;
	}

}
