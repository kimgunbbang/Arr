package product.svc;

import vo.Product;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.ProductDAO;
public class ProductAddService {//서비스에서는

	public boolean productAdd(Product product) {
		boolean success = false;//돌려줄값 초기화
		Connection conn=null;//연결객체 초기화
		
		try {
			conn=getConnection();//연결객체불러와서
			ProductDAO productDAO= ProductDAO.getInstance();//싱글톤하나생성
			productDAO.setConnection(conn);//연결한뒤
			
			int result = productDAO.insertProduct(product);//메서드만들기
			if(result>0) {
				commit(conn);
				success=true;
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return success;
	}

}
