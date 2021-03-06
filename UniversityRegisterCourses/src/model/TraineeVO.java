package model;

public class TraineeVO {

	int no;
	String sd_num;
	String sd_name;
	String l_num;
	String t_section;
	String t_date;

	// 디폴트 생성자 
	public TraineeVO() {
		super();
	}

	// sd_num, l_num, t_section 생성자 
	public TraineeVO(String sd_num, String l_num, String t_section) {
		super();
		this.sd_num = sd_num;
		this.l_num = l_num;
		this.t_section = t_section;
	}

	// sd_num, l_num, t_section, t_date 생성자 
	public TraineeVO(String sd_num, String l_num, String t_section, String t_date) {
		super();
		this.sd_num = sd_num;
		this.l_num = l_num;
		this.t_section = t_section;
		this.t_date = t_date;
	}

	// 모든 생성자 
	public TraineeVO(int no, String sd_num, String l_num, String t_section, String t_date) {
		super();
		this.no = no;
		this.sd_num = sd_num;
		this.l_num = l_num;
		this.t_section = t_section;
		this.t_date = t_date;
	}

	// 접근자와 설정자 생성 
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSd_num() {
		return sd_num;
	}

	public void setSd_num(String sd_num) {
		this.sd_num = sd_num;
	}

	public String getSd_name() {
		return sd_name;
	}

	public void setSd_name(String sd_name) {
		this.sd_name = sd_name;
	}

	public String getL_num() {
		return l_num;
	}

	public void setL_num(String l_num) {
		this.l_num = l_num;
	}

	public String getT_section() {
		return t_section;
	}

	public void setT_section(String t_section) {
		this.t_section = t_section;
	}

	public String getT_date() {
		return t_date;
	}

	public void setT_date(String t_date) {
		this.t_date = t_date;
	}

}
