package model;

public class FabricVO {

	private String f_number; // 제품코드
	private String f_sort; // 종류
	private String f_name; // 제품명
	private String f_color; // 색상
	private String f_size; // 사이즈
	private String f_origin; // 원산지
	private String f_cname; // 제조사
	private String f_phone; // 담당자연락처
	private String f_weight; // 중량
	private String f_price; // 가격
	private String f_material; // 소재
	private String f_trait = null; // 특징
	private String f_remarks = null; // 비고
	private String f_registdate; // 등록
	private String filename = null; // 이미지 파일 경로로 필드 추가

	// 디폴트 생성자
	public FabricVO() {
		super();
	}

	// 비고, 특징을 제외한 모든 생성자
	public FabricVO(String f_number, String f_sort, String f_name, String f_color, String f_size, String f_origin,
			String f_cname, String f_phone, String f_weight, String f_price, String f_material, String f_registdate,
			String filename) {
		super();
		this.f_number = f_number;
		this.f_sort = f_sort;
		this.f_name = f_name;
		this.f_color = f_color;
		this.f_size = f_size;
		this.f_origin = f_origin;
		this.f_cname = f_cname;
		this.f_phone = f_phone;
		this.f_weight = f_weight;
		this.f_price = f_price;
		this.f_material = f_material;
		this.f_registdate = f_registdate;
		this.filename = filename;
	}

	// 모든 생성자
	public FabricVO(String f_number, String f_sort, String f_name, String f_color, String f_size, String f_origin,
			String f_cname, String f_phone, String f_weight, String f_price, String f_material, String f_trait,
			String f_remarks, String f_registdate, String filename) {
		super();
		this.f_number = f_number;
		this.f_sort = f_sort;
		this.f_name = f_name;
		this.f_color = f_color;
		this.f_size = f_size;
		this.f_origin = f_origin;
		this.f_cname = f_cname;
		this.f_phone = f_phone;
		this.f_weight = f_weight;
		this.f_price = f_price;
		this.f_material = f_material;
		this.f_trait = f_trait;
		this.f_remarks = f_remarks;
		this.f_registdate = f_registdate;
		this.filename = filename;
	}

	// 등록일을 제외한 모든 생성자

	public FabricVO(String f_number, String f_sort, String f_name, String f_color, String f_size, String f_origin,
			String f_cname, String f_phone, String f_weight, String f_price, String f_material, String f_trait,
			String f_remarks, String filename) {
		super();
		this.f_number = f_number;
		this.f_sort = f_sort;
		this.f_name = f_name;
		this.f_color = f_color;
		this.f_size = f_size;
		this.f_origin = f_origin;
		this.f_cname = f_cname;
		this.f_phone = f_phone;
		this.f_weight = f_weight;
		this.f_price = f_price;
		this.f_material = f_material;
		this.f_trait = f_trait;
		this.f_remarks = f_remarks;
		this.filename = filename;
	}

	// 접근자와 설정자 생성
	public String getF_number() {
		return f_number;
	}

	// 종류, 이름, 등록일을 제외한 모든 생성자
	public FabricVO(String f_number, String f_color, String f_size, String f_origin, String f_cname, String f_phone,
			String f_weight, String f_price, String f_material, String f_trait, String f_remarks, String filename) {
		super();
		this.f_number = f_number;
		this.f_color = f_color;
		this.f_size = f_size;
		this.f_origin = f_origin;
		this.f_cname = f_cname;
		this.f_phone = f_phone;
		this.f_weight = f_weight;
		this.f_price = f_price;
		this.f_material = f_material;
		this.f_trait = f_trait;
		this.f_remarks = f_remarks;
		this.filename = filename;
	}

	public void setF_number(String f_number) {
		this.f_number = f_number;
	}

	public String getF_sort() {
		return f_sort;
	}

	public void setF_sort(String f_sort) {
		this.f_sort = f_sort;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_color() {
		return f_color;
	}

	public void setF_color(String f_color) {
		this.f_color = f_color;
	}

	public String getF_size() {
		return f_size;
	}

	public void setF_size(String f_size) {
		this.f_size = f_size;
	}

	public String getF_origin() {
		return f_origin;
	}

	public void setF_origin(String f_origin) {
		this.f_origin = f_origin;
	}

	public String getF_cname() {
		return f_cname;
	}

	public void setF_cname(String f_cname) {
		this.f_cname = f_cname;
	}

	public String getF_phone() {
		return f_phone;
	}

	public void setF_phone(String f_phone) {
		this.f_phone = f_phone;
	}

	public String getF_weight() {
		return f_weight;
	}

	public void setF_weight(String f_weight) {
		this.f_weight = f_weight;
	}

	public String getF_price() {
		return f_price;
	}

	public void setF_price(String f_price) {
		this.f_price = f_price;
	}

	public String getF_material() {
		return f_material;
	}

	public void setF_material(String f_material) {
		this.f_material = f_material;
	}

	public String getF_trait() {
		return f_trait;
	}

	public void setF_trait(String f_trait) {
		this.f_trait = f_trait;
	}

	public String getF_remarks() {
		return f_remarks;
	}

	public void setF_remarks(String f_remarks) {
		this.f_remarks = f_remarks;
	}

	public String getF_registdate() {
		return f_registdate;
	}

	public void setF_registdate(String f_registdate) {
		this.f_registdate = f_registdate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
