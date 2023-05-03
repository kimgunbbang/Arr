package dao;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import vo.User;

public class UserDAO {
    private static UserDAO userDAO;
    private Connection conn;
    
    private UserDAO() {}
    
    public static UserDAO getInstance() {
        if(userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public String selectLoginID(String id, String user_pass) {
        String loginID = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from user where id='"+id+"' and user_pass='"+user_pass+"'";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                loginID = rs.getString("id");
            }
        }catch (Exception e) {
        	e.printStackTrace();
            System.out.println("selectLoginID에러"+e);
        }finally {
            close(rs);
            close(pstmt);
        }
        return loginID;
    }
	public int insertUser(User user) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "insert into user values(?,?,?,?,?,?,?,'0')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getUser_pass());
			pstmt.setString(3, user.getUser_name());
			pstmt.setString(4, user.getUser_zipcode());
			pstmt.setString(5, user.getUser_addr());
			pstmt.setString(6, user.getUser_addr2());
			pstmt.setString(7, user.getUser_phone());
			
			insertCount = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("insertMember에러"+e);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}

	public ArrayList<User> selectUserList() {
		ArrayList<User> userList=new ArrayList<User>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql = "select * from user";
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					User user=new User();
					user.setId(rs.getString("id"));
					user.setUser_pass(rs.getString("user_pass"));
					user.setUser_name(rs.getString("user_name"));
					user.setUser_zipcode(rs.getString("user_zipcode"));
					user.setUser_addr(rs.getString("user_addr"));
					user.setUser_addr2(rs.getString("user_addr2"));
					user.setUser_phone(rs.getString("user_phone"));
					user.setUser_bye(rs.getBoolean("user_bye"));
					userList.add(user);
					
				}while(rs.next());
			}
			System.out.println(userList);
		}catch(Exception e) {
			System.out.println("selectUserList에러 "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return userList;
	}

	public User selectUser(String id) {
		User user = new User();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from user where id = ?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {//결과가 있으면
				user.setId(rs.getString("id"));
				user.setUser_pass(rs.getString("user_pass"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_zipcode(rs.getString("user_zipcode"));
				user.setUser_addr(rs.getString("user_addr"));
				user.setUser_addr2(rs.getString("user_addr2"));
				user.setUser_phone(rs.getString("user_phone"));
			}
		}catch(Exception e) {
			System.out.println("selectUser에러 :"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return user;
	}

	public int deleteUser(String id) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from user where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("deleteMember에러 : "+e);
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	public int updateUser(User user) {
	    int updateResult = 0;
	    PreparedStatement pstmt = null;
		String sql = "update user set "+
				"user_name=?, user_zipcode=?, user_addr=?, user_addr2=?, user_phone=? ";
		if(user.getUser_pass()!=null) {
			sql+=", user_pass='"+user.getUser_pass()+"' ";
		}
		sql+="where id='"+user.getId()+"'";
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, user.getUser_name());
	        pstmt.setString(2, user.getUser_zipcode());
	        pstmt.setString(3, user.getUser_addr());
	        pstmt.setString(4, user.getUser_addr2());
	        pstmt.setString(5, user.getUser_phone());

	        updateResult = pstmt.executeUpdate();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	    }
	    
	    return updateResult;
	}

	public int byeUser(String id) {
		int byeResult = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE user SET user_bye = '1' WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			byeResult = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return byeResult;
	}

	public User selectUserById(String id) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    User user = null;

	    try {
	        conn = getConnection();
	        String sql = "SELECT * FROM user WHERE id = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            user = new User();
	            user.setId(rs.getString("id"));
	            user.setUser_pass(rs.getString("user_pass"));
	            user.setUser_name(rs.getString("user_name"));
	            user.setUser_zipcode(rs.getString("user_zipcode"));
	            user.setUser_addr(rs.getString("user_addr"));
	            user.setUser_addr2(rs.getString("user_addr2"));
	            user.setUser_phone(rs.getString("user_phone"));
	            user.setUser_bye(rs.getBoolean("user_bye"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conn);
	    }

	    return user;
	}
	
	public int joinIdCheck(String id) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
		int result = -1;
		try {
			conn = getConnection();
			
			String sql = "select id from user where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0;	
			}else {
				result = 1;
			}
			System.out.println("아이디 중복체크 결과 : "+result);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
	        close(rs);
	        close(pstmt);
	        close(conn);
		}
		
		return result;
	}


}
