package vo;

import java.sql.Date;

public class BuyInfo {
	private int buyinfo_num; 	//주문상세순번
	private int buy_num;		//구매번호
	private String buy_name;	//수령인
	private String buy_phone;	//수령인번호
	private String buy_zipcode;	//우편번호
	private String buy_addr;	//주소
	private String buy_addr2;	//상세주소
	private String deli_memo;	//배송메모
	private Date buy_date;		//구매날짜
	
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public int getBuyinfo_num() {
		return buyinfo_num;
	}
	public void setBuyinfo_num(int buyinfo_num) {
		this.buyinfo_num = buyinfo_num;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	public String getBuy_name() {
		return buy_name;
	}
	public void setBuy_name(String buy_name) {
		this.buy_name = buy_name;
	}
	public String getBuy_phone() {
		return buy_phone;
	}
	public void setBuy_phone(String buy_phone) {
		this.buy_phone = buy_phone;
	}
	public String getBuy_zipcode() {
		return buy_zipcode;
	}
	public void setBuy_zipcode(String buy_zipcode) {
		this.buy_zipcode = buy_zipcode;
	}
	public String getBuy_addr() {
		return buy_addr;
	}
	public void setBuy_addr(String buy_addr) {
		this.buy_addr = buy_addr;
	}
	public String getBuy_addr2() {
		return buy_addr2;
	}
	public void setBuy_addr2(String buy_addr2) {
		this.buy_addr2 = buy_addr2;
	}
	public String getDeli_memo() {
		return deli_memo;
	}
	public void setDeli_memo(String deli_memo) {
		this.deli_memo = deli_memo;
	}
	
	
	
	
}
