package user.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class UserModifyService {

	public boolean updateUser(User user) {
		boolean update=false;
		Connection conn= getConnection();

		try {
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.setConnection(conn);
			int updateResult= userDAO.updateUser(user);
			
			if(updateResult>0) {
				update=true;
				commit(conn);
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return update;
	}

}
