package user.svc;

import java.sql.Connection;


import dao.UserDAO;
import vo.User;
import static db.JdbcUtil.*;

public class UserJoinService {

	public boolean joinUser(User user) {
		boolean joinResult=false;
		UserDAO userDAO = UserDAO.getInstance();
		Connection conn = null;
		
		try {
			conn = getConnection();
			userDAO.setConnection(conn);
			int insertCount = userDAO.insertUser(user);
			
			if(insertCount>0) {
				joinResult = true;
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}catch (Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		return joinResult;
	}

}
