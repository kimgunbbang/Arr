package qna.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.QnaDAO;

import static db.JdbcUtil.*;
import vo.Qna;

public class QanMyListService {

	public ArrayList<Qna> getQnaMyList(String id) {
		ArrayList<Qna> qnaMyList = new ArrayList<Qna>();
		Connection conn = null;
		try {
			conn=getConnection();
			QnaDAO qnaDAO = QnaDAO.getInstance();
			qnaDAO.setConnection(conn);
			
			qnaMyList = qnaDAO.getQnaMyList(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return qnaMyList;
	}

}
