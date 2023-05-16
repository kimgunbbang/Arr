package dao;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		
		String sql = "insert into review values(?,?,?,?,?,?,?,now())";
		int num;
        
		try {
			pstmt = conn.prepareStatement("select max(r_num) from review");
	        rs = pstmt.executeQuery();
	        if(!rs.next()) {
	           num=1;
	        }else {
	           num=rs.getInt(1)+1;
	        }
	        
	        String rnum = Integer.toString(num);
	        
	        close(rs);
	        close(pstmt);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rnum);
			pstmt.setString(2, review.getP_num());
			pstmt.setString(3, review.getId());
			pstmt.setString(4, review.getR_rating());
			pstmt.setString(5, review.getR_title());
			pstmt.setString(6, review.getR_detail());
			pstmt.setString(7, review.getR_image());
			
			writeCount = pstmt.executeUpdate();
		
		}catch (Exception e) {
			System.out.println("reviewWrite에러"+e);
		}finally {
			close(pstmt);
		}
		
		
		return writeCount;
	}
    
    
}
