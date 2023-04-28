package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Product;

import static db.JdbcUtil.*;

public class ProductDAO {
	private static ProductDAO productDAO;//싱글톤1 : 선언
	private Connection conn; //★★★★★DAO에서 사용할 커넥트 무조건만들어야함!!!!
	private ProductDAO() {}//싱글톤2 : 객채생성막기
	
	public static ProductDAO getInstance() { //싱글톤3 : 메서드로 작성
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}
		return productDAO;
	}

	public void setConnection(Connection conn) {//연결메서드
		this.conn = conn;
		
	}

	public int insertProduct(Product product) {//상품등록하기
		int insertCount = 0;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int num;
		String sql = "insert into product values(?,?,?,?,?,?,?,0,false)";
		try {
			pstmt = conn.prepareStatement("select max(p_num) from product");
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				num=1;
			}else {
				num=rs.getInt(1)+1;
			}
			close(rs);
			close(pstmt);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, product.getP_name());
			pstmt.setInt(3, product.getP_price());
			pstmt.setString(4, product.getP_detail());
			pstmt.setString(5, product.getP_image());
			pstmt.setString(6, product.getP_image2());
			pstmt.setString(7, product.getCategory_name());
			insertCount=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("DAO insertProduct 에러임"+e);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}

	public ArrayList<Product> selectProductAllList() {//전체상품목록가져오기
		ArrayList<Product> productList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product";
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				productList = new ArrayList<Product>();
				do {
					productList.add(new Product(
							rs.getInt("p_num"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_detail"),
							rs.getString("p_image"),
							rs.getString("p_image2"),
							rs.getString("category_name"),
							rs.getInt("p_readcount"),
							rs.getBoolean("p_hide")
							));
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("DAO selectProductAllList 에러임"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return productList;
	}

	public ArrayList<Product> selectProductSelectList(String category_name) {
		ArrayList<Product> productList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product where category_name = '"+category_name+"'";
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				productList = new ArrayList<Product>();
				do {
					productList.add(new Product(
							rs.getInt("p_num"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_detail"),
							rs.getString("p_image"),
							rs.getString("p_image2"),
							rs.getString("category_name"),
							rs.getInt("p_readcount"),
							rs.getBoolean("p_hide")
							));
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("DAO selectProductSelectList 에러임"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return productList;
	}

	public Product getProduct(int p_num) {//상품한개가져와서보여주기
	    Product product = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	try {
	    		String sql1 = "update product set p_readcount=p_readcount+1 where p_num='"+p_num+"'";
				pstmt=conn.prepareStatement(sql1);
				int result=pstmt.executeUpdate();
				if(result>0) commit(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
	    	
	    	String sql = "select * from product where p_num='"+p_num+"'";
	    	pstmt=conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            product = new Product();
	            product.setP_num(rs.getInt("p_num"));
	            product.setP_name(rs.getString("p_name"));
	            product.setP_price(rs.getInt("p_price"));
	            product.setP_detail(rs.getString("p_detail"));
	            product.setP_image(rs.getString("p_image"));
	            product.setP_image2(rs.getString("p_image2"));
	            product.setCategory_name(rs.getString("category_name"));
	            product.setP_readcount(rs.getInt("p_readcount"));
	            product.setP_hide(rs.getBoolean("p_hide"));

	        }
	    } catch (Exception e) {
	        System.out.println("DAO getProduct 에러임"+e);
	    } finally {
	        close(rs);
	        close(pstmt);
	    }
	    return product;
	}

	public int modifyProduct(Product product) {
		int modifyCount=0;
		PreparedStatement pstmt=null;
		String sql = "update product set category_name=?, p_name=?, p_price=?, p_detail=?"+
					", p_hide=? ";
		if(product.getP_image() != null)	sql+=", p_image=? ";
		if(product.getP_image2() != null) 	sql+=", p_image2=? ";

		sql+="where p_num='"+product.getP_num()+"'";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, product.getCategory_name());
			pstmt.setString(2, product.getP_name());
			pstmt.setInt(3, product.getP_price());
			pstmt.setString(4, product.getP_detail());
			pstmt.setBoolean(5, product.isP_hide());
			if(product.getP_image() != null && product.getP_image2() != null) {
				pstmt.setString(6, product.getP_image());
				pstmt.setString(7, product.getP_image2());
			}else if(product.getP_image() != null) {
				pstmt.setString(6, product.getP_image());
			}else if(product.getP_image2() != null) {
				pstmt.setString(6, product.getP_image2());
			}
			modifyCount=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("DAO modifyProduct 에러임"+e);
		}finally {
			close(pstmt);
		}
		return modifyCount;
	}
	
	
	
}
