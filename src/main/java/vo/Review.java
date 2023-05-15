package vo;

import java.sql.Date;

public class Review {
	private int r_num;
	private int p_num;
	private int r_rating;
	private String id;
	private String r_title;
	private String r_detail;
	private String r_image;
	private String r_date;
	
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getR_rating() {
		return r_rating;
	}
	public void setR_rating(int r_rating) {
		this.r_rating = r_rating;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getR_title() {
		return r_title;
	}
	public void setR_title(String r_title) {
		this.r_title = r_title;
	}
	public String getR_detail() {
		return r_detail;
	}
	public void setR_detail(String r_detail) {
		this.r_detail = r_detail;
	}
	public String getR_image() {
		return r_image;
	}
	public void setR_image(String r_image) {
		this.r_image = r_image;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	
}
