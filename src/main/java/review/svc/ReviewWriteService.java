package review.svc;

import java.sql.Connection; 

import dao.ReviewDAO;
import vo.Review;
import static db.JdbcUtil.*;

public class ReviewWriteService {

	public boolean writeReview(Review review) {
		boolean writeResult = false;
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		Connection conn = null;
		
		try {
			conn = getConnection();
			reviewDAO.setConnection(conn);
			int writeCount = reviewDAO.writeReview(review);
			
			if(writeCount > 0) {
				writeResult = true;
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		
		return writeResult;
	}

}
