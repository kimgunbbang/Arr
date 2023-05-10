package vo;

public class Product {
	private int p_num;		//상품번호1
	
	private String p_name;	//상품명2
	private int p_price;	//가격3
	private String p_detail;//상품상세내용4
	private String p_image;	//상품이미지명5
	private String p_image2;	//상품이미지명6
	private String category_name;//카테고리명7
	
	private int p_readcount;//조회수8
	private boolean p_hide;//상품삭제(숨기기)
	
	public Product() {}
	
	public Product(int p_num, String p_name, int p_price, String p_detail, String p_image, String p_image2,
			String category_name, int p_readcount, boolean p_hide) {
		super();
		this.p_num = p_num;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_detail = p_detail;
		this.p_image = p_image;
		this.p_image2 = p_image2;
		this.category_name = category_name;
		this.p_readcount = p_readcount;
		this.p_hide = p_hide;
	}


	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_detail() {
		return p_detail;
	}
	public void setP_detail(String p_detail) {
		this.p_detail = p_detail;
	}
	public String getP_image() {
		return p_image;
	}
	public void setP_image(String p_image) {
		this.p_image = p_image;
	}
	public String getP_image2() {
		return p_image2;
	}
	public void setP_image2(String p_image2) {
		this.p_image2 = p_image2;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getP_readcount() {
		return p_readcount;
	}
	public void setP_readcount(int p_readcount) {
		this.p_readcount = p_readcount;
	}
	public boolean isP_hide() {
		return p_hide;
	}
	public void setP_hide(boolean p_hide) {
		this.p_hide = p_hide;
	}
	
	
	
	
	
}
