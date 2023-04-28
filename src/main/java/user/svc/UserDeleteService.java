package user.svc;

import static db.JdbcUtil.*;


import java.sql.Connection;

import dao.UserDAO;


public class UserDeleteService {

	public boolean deleteUser(String id) {
		boolean deleteUser = false;
		Connection conn = null;
		int deleteCount = 0;
		
		
		try {
			conn = getConnection();
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.setConnection(conn);
			deleteCount = userDAO.deleteUser(id);
			
			if(deleteCount>0) {
				commit(conn);
				deleteUser=true;
			}else {
				rollback(conn);
			}
		}catch(Exception e ) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return deleteUser;
		
	}
}
