package qna.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnaDAO;
import vo.Qna;

public class QnaAnswerService {

	public boolean writeAnswer(Qna qna) {
		boolean writeResult = false;
		QnaDAO qnaDAO = QnaDAO.getInstance();
		Connection conn = null;
		
		
		try {
			conn = getConnection();
			qnaDAO.setConnection(conn);
			int writeCount = qnaDAO.writeAnswer(qna);
			
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
