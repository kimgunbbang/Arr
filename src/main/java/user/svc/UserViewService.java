package user.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;


public class UserViewService {

	public User selectUser(String id) {
		User user = new User();
		Connection conn=getConnection();
		try {
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.setConnection(conn);
			user = userDAO.selectUser(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return user;
	}

}