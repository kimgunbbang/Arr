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
		String sql = "insert into product values(?,?,?,?,?,?,?,0,false,0)";
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
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertCount;
	}

	public ArrayList<Product> selectProductAllList() {//전체상품목록가져오기
		ArrayList<Product> productList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from product natural join inventory where "+
				"inven_num in(select max(inven_num) from inventory group by p_num) order by p_num";
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
							rs.getBoolean("p_hide"),
							rs.getInt("inven_qty")
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
		String sql = "select * from product natural join inventory where category_name = '"+category_name+"' and "+
				"inven_num in(select max(inven_num) from inventory group by p_num)";
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
							rs.getBoolean("p_hide"),
							rs.getInt("inven_qty")
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
	    	String sql = 
	    	"select * from inventory natural join product where p_num=? and "+
	    	"inven_num=(select inven_num from inventory where inven_num="+
	    			"(select max(inven_num) from inventory where p_num=?))";
	    	pstmt=conn.prepareStatement(sql);
	    	pstmt.setInt(1, p_num);
	    	pstmt.setInt(2, p_num);
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
	            product.setP_qty(rs.getInt("inven_qty"));
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

	public int inventoryCheck(ArrayList<Product> productList) {
	    int result = 0;
	    PreparedStatement pstmt = null;
	    PreparedStatement pstmt1 = null;
	    ResultSet rs = null;

	    // DB 연결
	    try {

	        //현재재고 검색하는데 없거나 0이면 p_hide는 1로바꾸기
	        String sql = "select inven_qty from inventory where inven_num = (select max(inven_num) FROM inventory where p_num=?)";
	        for (int i = 0; i < productList.size(); i++) {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, productList.get(i).getP_num());
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                int inven_qty = rs.getInt("inven_qty");
	                if (inven_qty == 0) {
	                    pstmt1 = conn.prepareStatement("update product set p_hide='1' where p_num=?");
	                    pstmt1.setInt(1, productList.get(i).getP_num());
	                } else {
	                    pstmt1 = conn.prepareStatement("update product set p_hide='0' where p_num=?");
	                    pstmt1.setInt(1, productList.get(i).getP_num());
	                }
	            } else {
	                pstmt1 = conn.prepareStatement("update product set p_hide='1' where p_num=?");
	                pstmt1.setInt(1, productList.get(i).getP_num());
	            }

	            int updateSuccess = pstmt1.executeUpdate();

	            if (updateSuccess > 0) {
	                result = 1;
	                conn.commit();
	            } else {
	                result = 0;
	                conn.rollback();
	            }

	            close(pstmt1);
	            close(rs);
	            close(pstmt);
	        }
	    } catch (Exception e) {
	        System.out.println("DAO inventoryCheck 에러임"+e);
	    } finally {
	        close(pstmt1);
	        close(rs);
	        close(pstmt);
	    }

	    return result;
	}

	public Product selectProduct(String p_num) {
		Product product = new Product();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from product where p_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_num);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			if(rs.next()) {
				product.setCategory_name(rs.getString("category_name"));
				product.setP_detail(rs.getString("p_detail"));
				product.setP_image(rs.getString("p_image"));
				product.setP_image2(rs.getString("p_image2"));
				product.setP_name(rs.getString("p_name"));
				product.setP_num(rs.getInt("p_num"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_readcount(rs.getInt("p_readcount"));
				product.setP_hide(rs.getBoolean("p_hide"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return product;
	}

	public ArrayList<Product> selectProductBestReadList() {
		ArrayList<Product> productList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product order by p_readcount desc limit 4";
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
							rs.getBoolean("p_hide"),
							0//재고 임의로 넣어놓음...
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

	public ArrayList<Product> selectProductBestSaleList() {
		  ArrayList<Product> productList = new ArrayList<>();
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    String sql = "SELECT p.p_num, p.p_name, p.p_price, p.p_detail, p.p_image, p.p_image2, p.category_name, p.p_readcount, p.p_hide "
		            + "FROM product p "
		            + "JOIN (SELECT i.p_num, SUM(i.inven_out) as total_out "
		            +       "FROM inventory i "
		            +       "GROUP BY i.p_num) i ON p.p_num = i.p_num where p_hide !='1' "
		            + "ORDER BY i.total_out DESC "
		            + "LIMIT 4";
		    try {
		        pstmt = conn.prepareStatement(sql);
		        rs = pstmt.executeQuery();
		        while (rs.next()) {
		            Product product = new Product(
		                    rs.getInt("p_num"),
		                    rs.getString("p_name"),
		                    rs.getInt("p_price"),
		                    rs.getString("p_detail"),
		                    rs.getString("p_image"),
		                    rs.getString("p_image2"),
		                    rs.getString("category_name"),
		                    rs.getInt("p_readcount"),
		                    rs.getBoolean("p_hide"),
		                    0//재고 임의로 넣어놓음..
		            );
		            productList.add(product);
		        }
		    } catch (Exception e) {
		        System.out.println("DAO selectProductBestSaleList 에러: " + e);
		    } finally {
		        close(rs);
		        close(pstmt);
		    }
		    return productList;
		}

	public int productDelete(int p_num) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update product set p_hide='1' where p_num=?";
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}

	public int getProductMaxP_num() {
		int productMaxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select max(p_num) from product";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				productMaxNum=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return productMaxNum;
	}

	
}
