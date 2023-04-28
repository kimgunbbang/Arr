package vo;

import java.sql.Date;

public class Inventory {
	private int inven_num;		//순번
	private int p_num;			//상품번호
	private int inven_qty;		//상품총재고
	private int inven_in;		//상품입고수
	private int inven_out;		//상품출고수
	private Date inven_date;	//입출고된 날짜
	
	
	public int getInven_num() {
		return inven_num;
	}
	public void setInven_num(int inven_num) {
		this.inven_num = inven_num;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getInven_qty() {
		return inven_qty;
	}
	public void setInven_qty(int inven_qty) {
		this.inven_qty = inven_qty;
	}
	public int getInven_in() {
		return inven_in;
	}
	public void setInven_in(int inven_in) {
		this.inven_in = inven_in;
	}
	public int getInven_out() {
		return inven_out;
	}
	public void setInven_out(int inven_out) {
		this.inven_out = inven_out;
	}
	public Date getInven_date() {
		return inven_date;
	}
	public void setInven_date(Date inven_date) {
		this.inven_date = inven_date;
	}
	
	
}
