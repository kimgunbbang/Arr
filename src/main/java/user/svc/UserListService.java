package user.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UserDAO;
import vo.User;
import db.JdbcUtil.*;

public class UserListService {

	public ArrayList<User> getUserList() {
		ArrayList<User> userList =null;//1. 돌려줄값초기화하고
		Connection conn=null;//2. 커넥션초기화 하고난다음 디비 연결하자!
		UserDAO userDAO=null;//3. (디비연결할)싱글톤객체생성
		
		try {
			conn=getConnection();
			userDAO = UserDAO.getInstance();
			userDAO.setConnection(conn);
			userList=userDAO.selectUserList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		
		return userList;
	}

}