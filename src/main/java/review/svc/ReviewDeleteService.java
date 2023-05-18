package review.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.Review;


public class ReviewDeleteService {

	public boolean deleteReview(String r_num) {
		boolean deleteReview = false;
		Connection conn = null;
		int deleteCount = 0;
		
		
		try {
			conn = getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			deleteCount = reviewDAO.deleteReview(r_num);
			
			if(deleteCount>0) {
				commit(conn);
				deleteReview = true;
			}else {
				rollback(conn);
			}
		}catch(Exception e ) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return deleteReview;
		
	}

}
