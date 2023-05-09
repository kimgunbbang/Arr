package vo;

import java.sql.Date;

public class BuyList {
	private Date buy_date;
	private int buy_num;
	private int p_num;
	private int buy_qty;
	private int buy_totalmoney;
	private String id;
	private String p_name;
	private String buy_state;
	private String p_image;
	
	
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getBuy_qty() {
		return buy_qty;
	}
	public void setBuy_qty(int buy_qty) {
		this.buy_qty = buy_qty;
	}
	public int getBuy_totalmoney() {
		return buy_totalmoney;
	}
	public void setBuy_totalmoney(int buy_totalmoney) {
		this.buy_totalmoney = buy_totalmoney;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getBuy_state() {
		return buy_state;
	}
	public void setBuy_state(String buy_state) {
		this.buy_state = buy_state;
	}
	public String getP_image() {
		return p_image;
	}
	public void setP_image(String p_image) {
		this.p_image = p_image;
	}
	
	
}
