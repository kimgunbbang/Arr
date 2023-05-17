package dao;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.BuyList;
import vo.Review;

import static db.JdbcUtil.*;

public class ReviewDAO {
	private static ReviewDAO reviewDAO;
	private Connection conn;
	
	private ReviewDAO() {}
	
	public static ReviewDAO getInstance() {
        if(reviewDAO == null) {
            reviewDAO = new ReviewDAO();
        }
        return reviewDAO;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

	public int writeReview(Review review) {
		int writeCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into review values(?,?,?,?,?,?,?,now(),?)";
		int num;
        
		try {
			pstmt = conn.prepareStatement("select max(r_num) from review");
	        rs = pstmt.executeQuery();
	        if(!rs.next()) {
	           num=1;
	        }else {
	           num=rs.getInt(1)+1;
	        }
	        System.out.println(review.getP_num());
	        String rnum = Integer.toString(num);
	        
	        close(rs);
	        close(pstmt);
			pstmt = conn.prepareStatement(sql);
			System.out.println(review.getP_num());
			pstmt.setString(1, rnum);
			pstmt.setString(2, review.getP_num());
			pstmt.setString(3, review.getId());
			pstmt.setString(4, review.getR_rating());
			pstmt.setString(5, review.getR_title());
			pstmt.setString(6, review.getR_detail());
			pstmt.setString(7, review.getR_image());
			pstmt.setString(8, review.getBuy_num());
			
			writeCount = pstmt.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return writeCount;
	}

	public ArrayList<Review> reviewAllList() {
		ArrayList<Review> reviewList = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					Review review = new Review();
					review.setR_num(rs.getString("r_num"));
					review.setP_num(rs.getString("p_num"));
					review.setId(rs.getString("id"));
					review.setR_title(rs.getString("r_title"));
					review.setR_detail(rs.getString("r_detail"));
					review.setR_image(rs.getString("r_image"));
					review.setR_date(rs.getString("r_date"));
					
					reviewList.add(review);
				}while(rs.next());
			}
			
		}catch (Exception e) {
			System.out.println("reviewDAO reviewAllList에러임"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return reviewList;
	}

	public ArrayList<Boolean> reviewCheckList(ArrayList<Integer> buyNumList, ArrayList<BuyList> buyList) {
		ArrayList<Boolean> reviewCheck = new ArrayList<Boolean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review where buy_num = ? and p_num = ?";
		

		try {
	        for (int i = 0; i < buyList.size(); i++) {
	            boolean check = false;
	            for (int j = 0; j < buyNumList.size(); j++) {
	                pstmt = conn.prepareStatement(sql);
	                pstmt.setInt(1, buyNumList.get(j));
	                pstmt.setInt(2, buyList.get(i).getP_num());
	                System.out.println(pstmt);
	                rs = pstmt.executeQuery();
	                if (rs.next()) {
	                    // 같은 p_num인 상품에 대해 buy_num이 같은 경우에만 true로 설정
	                    if (buyNumList.get(j) == buyList.get(i).getBuy_num()) {
	                        check = true;
	                        break; // 이미 true로 설정되었으므로 더 이상 확인할 필요 없음
	                    }
	                }
	                close(rs);
	                close(pstmt);
	            }
	            reviewCheck.add(check);
	        }

		}catch (Exception e) {
			System.out.println("reviewDAO reviewCheckList에러임"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return reviewCheck;
	}

	public int deleteReview(String id, String r_num) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from review where id = ? and r_num = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("deleteDelivery에러 : "+e);
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
    
    
}
