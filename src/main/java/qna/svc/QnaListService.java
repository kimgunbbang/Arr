package qna.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.QnaDAO;
import vo.Qna;

public class QnaListService {

	public ArrayList<Qna> qnaAllList() {
		ArrayList<Qna> qnaList = null;
		Connection conn = null;
		try {
			conn=getConnection();
			QnaDAO qnaDAO = QnaDAO.getInstance();
			qnaDAO.setConnection(conn);
			
			qnaList = qnaDAO.qnaAllList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			close(conn);
		}
		return qnaList;
	}
}
