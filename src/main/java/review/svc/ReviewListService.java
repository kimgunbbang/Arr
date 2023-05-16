package review.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.Review;

import static db.JdbcUtil.*;

public class ReviewListService {

	public ArrayList<Review> reviewAllList() {
		ArrayList<Review> reviewList = null;
		Connection conn = null;
		try {
			conn=getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			
			reviewList = reviewDAO.reviewAllList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			close(conn);
		}
		return reviewList;
	}


}
