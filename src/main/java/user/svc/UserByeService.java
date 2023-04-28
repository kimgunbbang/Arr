package user.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.UserDAO;

public class UserByeService {

	public boolean byeUser(String id) {
		boolean bye=false;
		Connection conn= getConnection();

		try {
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.setConnection(conn);
			int byeResult= userDAO.byeUser(id);
			
			if(byeResult>0) {
				bye=true;
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
		return bye;
	}

}