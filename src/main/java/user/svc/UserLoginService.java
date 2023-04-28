package user.svc;

import java.sql.Connection;


import dao.UserDAO;
import vo.User;

import static db.JdbcUtil.*;



public class UserLoginService {

	public boolean login(String id, String user_pass) {
		boolean loginSuccess = false;
		Connection conn = null;
		UserDAO userDAO = UserDAO.getInstance();
		try {
			conn = getConnection();
			userDAO.setConnection(conn);
			String user_id = userDAO.selectLoginID(id, user_pass);
			if(user_id != null) {
				loginSuccess = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		
		return loginSuccess;
	}

	public User getUser(String id) {
		  UserDAO userDAO = UserDAO.getInstance();
	        User user = userDAO.selectUserById(id);
	        return user;
	}

}
