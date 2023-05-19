package qna.svc;

import java.sql.Connection;

import dao.QnaDAO;
import vo.Qna;
import static db.JdbcUtil.*;


public class QnaWriteService {

	public boolean writeQna(Qna qna) {
		boolean writeResult = false;
		QnaDAO qnaDAO = QnaDAO.getInstance();
		Connection conn = null;
		
		
		try {
			conn = getConnection();
			qnaDAO.setConnection(conn);
			int writeCount = qnaDAO.writeQna(qna);
			
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
