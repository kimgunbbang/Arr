package qna.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.InventoryDAO;
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

	public ArrayList<Qna> qnaAllList(int page, int limit, String id) {
		ArrayList<Qna> qnaList = null;
		Connection conn = null;
		try {
			conn=getConnection();
			QnaDAO qnaDAO = QnaDAO.getInstance();
			qnaDAO.setConnection(conn);
			
			qnaList = qnaDAO.qnaAllList(page,limit,id);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			close(conn);
		}
		return qnaList;
	}

	public int getListCount() {
		int listcount=0;
		Connection conn =null;
		try {
			conn=getConnection();
			QnaDAO qnaDAO = QnaDAO.getInstance();
			qnaDAO.setConnection(conn);
			listcount = qnaDAO.selectListCount();
		}catch(Exception e) {
			System.out.println("getListCount 에러"+e);
		}finally {
			close(conn);
		}
		
		return listcount;
	}
}
