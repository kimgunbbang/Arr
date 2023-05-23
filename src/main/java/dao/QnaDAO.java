package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Qna;
import vo.Qna;

public class QnaDAO {

	private static QnaDAO qnaDAO;
	private Connection conn;
	
	private QnaDAO() {}
	
	public static QnaDAO getInstance() {
        if(qnaDAO == null) {
            qnaDAO = new QnaDAO();
        }
        return qnaDAO;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

	public int writeQna(Qna qna) {
		int writeCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into qna values(?,?,?,?,now(),false,default,?)";
		int num;
        
		try {
			pstmt = conn.prepareStatement("select max(qna_num) from qna");
	        rs = pstmt.executeQuery();
	        if(!rs.next()) {
	           num=1;
	        }else {
	           num=rs.getInt(1)+1;
	        }
	        System.out.println(qna.getP_num());
	        String qnanum = Integer.toString(num);
	        
	        close(rs);
	        close(pstmt);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnanum);
			pstmt.setString(2, qna.getId());
			pstmt.setString(3, qna.getQna_subject());
			pstmt.setString(4, qna.getQna_content());
			pstmt.setString(5, qna.getP_num());
			
			writeCount = pstmt.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return writeCount;
	}

	public ArrayList<Qna> qnaAllList() {
		ArrayList<Qna> qnaList = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qna";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					Qna qna = new Qna();
					qna.setQna_num(rs.getString("qna_num"));
					qna.setId(rs.getString("id"));
					qna.setP_num(rs.getString("p_num"));
					qna.setQna_subject(rs.getString("qna_subject"));
					qna.setQna_content(rs.getString("qna_content"));
					qna.setQna_answer(rs.getString("qna_answer"));
					qna.setQna_reply(rs.getString("qna_reply"));
					qna.setQna_date(rs.getString("qna_date"));
					
					qnaList.add(qna);
				}while(rs.next());
			}
			
		}catch (Exception e) {
			System.out.println("qnaDAO qnaAllList에러임"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return qnaList;
	}

	public int writeAnswer(Qna qna) {
		int writeCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update qna set qna_reply = ?, qna_answer = true where qna_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qna.getQna_reply());
			pstmt.setString(2, qna.getQna_num());

			writeCount = pstmt.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return writeCount;
	}

	public ArrayList<Qna> getQnaMyList(String id) {
		ArrayList<Qna> qnaMyList = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qna where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					Qna qna = new Qna();
					qna.setQna_num(rs.getString("qna_num"));
					qna.setId(id);
					qna.setQna_subject(rs.getString("qna_subject"));
					qna.setQna_content(rs.getString("qna_content"));
					qna.setQna_date(rs.getString("qna_date"));
					qna.setQna_answer(rs.getString("qna_answer"));
					qna.setQna_reply(rs.getString("qna_reply"));
					qna.setP_num(rs.getString("p_num"));
					qnaMyList.add(qna);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return qnaMyList;
	}

}
