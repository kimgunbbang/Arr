package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import vo.Buy;
import vo.Inventory;

public class InventoryDAO {
	private static InventoryDAO inventoryDAO;//싱글톤1 : 선언
	private Connection conn; //★★★★★DAO에서 사용할 커넥트 무조건만들어야함!!!!
	private InventoryDAO() {}//싱글톤2 : 객채생성막기
	
	public static InventoryDAO getInstance() { //싱글톤3 : 메서드로 작성
		if(inventoryDAO == null) {
			inventoryDAO = new InventoryDAO();
		}
		return inventoryDAO;
	}

	public void setConnection(Connection conn) {//연결메서드
		this.conn = conn;
		
	}

	public int inserInOutQty(Inventory inventory) {//재고입출고등록
		
	    int insertcount=0;
	    PreparedStatement pstmt = null;
	    PreparedStatement pstmt1 = null;
	    PreparedStatement pstmt2 = null;
	    PreparedStatement pstmt3 = null;
	    PreparedStatement pstmt4 = null;
	    
	    ResultSet rs = null;
	    ResultSet rs1 = null;
	    ResultSet rs2 = null;
	    int orgQty=0;
	    int num;

	    String sql = "insert into inventory values(?,?,?,?,?,default)";//순번,상품번호,현재재고량,입고량,출고량,

	    try {
	    	pstmt = conn.prepareStatement("select max(inven_num) from inventory");
	         rs = pstmt.executeQuery();
	         if(!rs.next()) {
	            num=1;
	         }else {
	            num=rs.getInt(1)+1;
	         }
	         close(rs);
	         close(pstmt);
	         
	        //p_num인 인벤토리데이터가져와서 나열했는데
	        pstmt1 = conn.prepareStatement("select * from inventory where p_num='"+inventory.getP_num()+"'");
	        rs1=pstmt1.executeQuery();
	        if(!rs1.next()) {//저장되어있는 p_num 없으면
	        	if(inventory.getInven_in()<inventory.getInven_out()) {//처음재고량이 -일때
	        		System.out.println("입고량보다 출고량이 많을 수 없습니다.");
	        		return 0; 
	        	}
	            pstmt4 = conn.prepareStatement(sql);//그냥추가하고
	            pstmt4.setInt(1, num);       
	            pstmt4.setInt(2, inventory.getP_num());       //상품번호
	            pstmt4.setInt(3, inventory.getInven_in()-inventory.getInven_out());	//맨처음재고  
	            pstmt4.setInt(4, inventory.getInven_in());       //입고
	            pstmt4.setInt(5, inventory.getInven_out());       //출고
	            insertcount = pstmt4.executeUpdate();//실행!!
	        }else {//저장되어 있는 p_num 있으면
	            pstmt2 = conn.prepareStatement("SELECT * FROM inventory WHERE inven_num = (SELECT MAX(inven_num) FROM inventory WHERE p_num='"
	                    +inventory.getP_num()+"')");
	            rs2=pstmt2.executeQuery();
	            if(rs2.next()) {//현재재고있는거 가져와서
	            	orgQty=rs2.getInt("inven_qty");//현재재고따로 저장하고
	            	if(orgQty+inventory.getInven_in()<inventory.getInven_out()) {//현재재고량보다 출고량이 많을수 없습니다.
	            		System.out.println("출고량이 재고량보다 많을 수 없습니다.");
		        		return 0;
	            	}
	            	pstmt3 = conn.prepareStatement(sql);//추가하자
	            	pstmt3.setInt(1, num);       //상품번호
		            pstmt3.setInt(2, inventory.getP_num());       //상품번호
		            pstmt3.setInt(3, orgQty + inventory.getInven_in() - inventory.getInven_out());   //현재재고        
		            pstmt3.setInt(4, inventory.getInven_in());       //입고
		            pstmt3.setInt(5, inventory.getInven_out());
		            insertcount = pstmt3.executeUpdate();//실행!!
	            }
	            
	        }
	        
	        

	    }catch(Exception e) {
	        System.out.println("inventoryDAO inserInOutQty에러임"+e);
	    }finally {
	        close(rs1);
	        close(rs2);
	        
	        close(pstmt1);
	        close(pstmt2);
	        close(pstmt3);
	        close(pstmt4);
	    }
	    return insertcount;
	}

	public ArrayList<Inventory> inventoryAllList() {
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from inventory";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					Inventory inventory = new Inventory();
					inventory.setInven_num(rs.getInt("inven_num"));
					inventory.setP_num(rs.getInt("p_num"));
					inventory.setInven_qty(rs.getInt("inven_qty"));
					inventory.setInven_in(rs.getInt("inven_in"));
					inventory.setInven_out(rs.getInt("inven_out"));
					inventory.setInven_date(rs.getDate("inven_date"));
					inventoryList.add(inventory);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("inventoryDAO inventoryAllList에러임"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return inventoryList;
	}

	public ArrayList<Inventory> inventorySearchList(String invenSearchOption, String invenSearchValue) {
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();//인벤리스트 생성해주고
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		
		if(invenSearchOption.equals("p_num")) {//검색옵션이p_num일때
			sql = "select * from inventory where "+invenSearchOption;
			sql+=" = '"+invenSearchValue+"'";
			
			try {
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					do {
						Inventory inventory = new Inventory();
						inventory.setInven_num(rs.getInt("inven_num"));
						inventory.setP_num(rs.getInt("p_num"));
						inventory.setInven_qty(rs.getInt("inven_qty"));
						inventory.setInven_in(rs.getInt("inven_in"));
						inventory.setInven_out(rs.getInt("inven_out"));
						inventory.setInven_date(rs.getDate("inven_date"));
						inventoryList.add(inventory);
					}while(rs.next());
				}
				
			}catch(Exception e) {
				System.out.println("inventoryDAO inventorySearchList에러임"+e);
			}finally {
				close(rs);
				close(pstmt);
			}
		}
		if(invenSearchOption.equals("p_name")) {//검색옵션이p_name일때
			//sql = "select * from inventory where "+invenSearchOption;
			//sql+=" like '%"+invenSearchValue+ "%'";
			
			sql="select * from inventory where p_num in (select p_num from product where p_name like '%"
														+invenSearchValue+"%')";
			
			try {
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					do {
						Inventory inventory = new Inventory();
						inventory.setInven_num(rs.getInt("inven_num"));
						inventory.setP_num(rs.getInt("p_num"));
						inventory.setInven_qty(rs.getInt("inven_qty"));
						inventory.setInven_in(rs.getInt("inven_in"));
						inventory.setInven_out(rs.getInt("inven_out"));
						inventory.setInven_date(rs.getDate("inven_date"));
						inventoryList.add(inventory);
					}while(rs.next());
				}
				
			}catch(Exception e) {
				System.out.println("inventoryDAO inventorySearchList에러임"+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
		}
		
		return inventoryList;
	}

	public ArrayList<Inventory> inventorySearchList(String invenSearchOption, String invenStartDate,
			String invenEndDate) {
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();//인벤리스트 생성해주고
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		String start="STR_TO_DATE('"+invenStartDate+" 00:00:00', '%Y-%m-%d %H:%i:%s')";
		String end="STR_TO_DATE('"+invenEndDate+" 23:59:59', '%Y-%m-%d %H:%i:%s')";
		System.out.println(start);
		System.out.println(end);
		
		
		if(!invenStartDate.equals("") && !invenEndDate.equals("")) {//시작과 끝날짜가 있을때
			sql = "select * from inventory where inven_date >= "+start;
			sql+= " and inven_date <= "+end;
			
			try {
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					do {
						Inventory inventory = new Inventory();
						inventory.setInven_num(rs.getInt("inven_num"));
						inventory.setP_num(rs.getInt("p_num"));
						inventory.setInven_qty(rs.getInt("inven_qty"));
						inventory.setInven_in(rs.getInt("inven_in"));
						inventory.setInven_out(rs.getInt("inven_out"));
						inventory.setInven_date(rs.getDate("inven_date"));
						inventoryList.add(inventory);
					}while(rs.next());
				}
				
			}catch(Exception e) {
				System.out.println("inventoryDAO inventorySearchList에러임"+e);
			}finally {
				close(rs);
				close(pstmt);
			}
		}
		if(invenStartDate.equals("") && !invenEndDate.equals("")) {//끝날짜만 있을때
			sql = "select * from inventory where inven_date<="+end;
			try {
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					do {
						Inventory inventory = new Inventory();
						inventory.setInven_num(rs.getInt("inven_num"));
						inventory.setP_num(rs.getInt("p_num"));
						inventory.setInven_qty(rs.getInt("inven_qty"));
						inventory.setInven_in(rs.getInt("inven_in"));
						inventory.setInven_out(rs.getInt("inven_out"));
						inventory.setInven_date(rs.getDate("inven_date"));
						inventoryList.add(inventory);
					}while(rs.next());
				}
				
			}catch(Exception e) {
				System.out.println("inventoryDAO inventorySearchList에러임"+e);
			}finally {
				close(rs);
				close(pstmt);
			}
		}
		if(!invenStartDate.equals("") && invenEndDate.equals("")) {//시작날짜만 있을때
			sql = "select * from inventory where inven_date>="+start;
			try {
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					do {
						Inventory inventory = new Inventory();
						inventory.setInven_num(rs.getInt("inven_num"));
						inventory.setP_num(rs.getInt("p_num"));
						inventory.setInven_qty(rs.getInt("inven_qty"));
						inventory.setInven_in(rs.getInt("inven_in"));
						inventory.setInven_out(rs.getInt("inven_out"));
						inventory.setInven_date(rs.getDate("inven_date"));
						inventoryList.add(inventory);
					}while(rs.next());
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		}
		return inventoryList;
	}

	public int inserInOutQty(ArrayList<Buy> inventoryCheck) {//insert가 되면 재고된다는뜻
		int insertcount=0;
	    PreparedStatement pstmt = null;
	    System.out.println(inventoryCheck);
	    ResultSet rs = null;
	    int orgQty=0;//현재재고
	    int num;//다음순번
	    String sql = "insert into inventory values(?,?,?,?,?,default)";//순번,상품번호,현재재고량,입고량,출고량,
	    try {
	    	for (Buy buy : inventoryCheck) {//insert반복해야하니까,,얼마만큼?ArrayList사이즈만큼
	    		//다음순번정하기
	    		pstmt = conn.prepareStatement("select max(inven_num) from inventory");
		        rs = pstmt.executeQuery();
		        if(!rs.next()) {
		           num=1;
		        }else {
		           num=rs.getInt(1)+1;
		        }
		        close(rs);
		        close(pstmt);
		        //저장되어 있는 p_num의 현재재고 가져오기
		        pstmt = conn.prepareStatement("SELECT * FROM inventory WHERE inven_num = (SELECT MAX(inven_num) FROM inventory WHERE p_num='"
	                    +buy.getP_num() +"')");
		        rs=pstmt.executeQuery();
		        if(rs.next()) {//현재재고있는거 가져와서 확인먼저 한다음
	            	orgQty=rs.getInt("inven_qty");//현재재고따로 저장하고
	            	if(orgQty-buy.getBuy_qty()<0) {//출고량이 재고량보다 많을수 없으니까 처리해주고..
	            		System.out.println("출고량이 재고량보다 많을 수 없습니다.");
		        		return 0;
	            	}
		        }//아니면 추가가능
		        close(rs);
		        close(pstmt);
		        
		        pstmt = conn.prepareStatement(sql);//추가하자
		        pstmt.setInt(1, num);       //순번
	            pstmt.setInt(2, buy.getP_num());       //상품번호
	            pstmt.setInt(3, orgQty - buy.getBuy_qty());   //현재재고        
	            pstmt.setInt(4, 0);       //입고
	            pstmt.setInt(5, buy.getBuy_qty());//출고
	            insertcount = pstmt.executeUpdate();//실행!!
	            if(!(insertcount>0)) {
	            	return 0;
	            }
		    }//반복끝
	    
	    }catch(Exception e) {
	        System.out.println("inventoryDAO inserInOutQty에러임"+e);
	    }finally {
	    	close(rs);
	        close(pstmt);
	    }
	    return insertcount;
	}

	public int inserCancelInOutQty(ArrayList<Buy> productList) {
		int insertcount=0;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int orgQty=0;//현재재고
	    int num;//다음순번
	    String sql = "insert into inventory values(?,?,?,?,?,default)";//순번,상품번호,현재재고량,입고량,출고량,
	    try {
	    	for (Buy buy : productList) {//insert반복해야하니까,,얼마만큼?ArrayList사이즈만큼
	    		//다음순번정하기
	    		pstmt = conn.prepareStatement("select max(inven_num) from inventory");
		        rs = pstmt.executeQuery();
		        if(!rs.next()) {
		           num=1;
		        }else {
		           num=rs.getInt(1)+1;
		        }
		        close(rs);
		        close(pstmt);
		        //저장되어 있는 p_num의 현재재고 가져오기
		        pstmt = conn.prepareStatement("SELECT * FROM inventory WHERE inven_num = (SELECT MAX(inven_num) FROM inventory WHERE p_num='"
	                    +buy.getP_num() +"')");
		        rs=pstmt.executeQuery();
		        if(rs.next()) {//현재재고있는거 가져와서 확인먼저 한다음
	            	orgQty=rs.getInt("inven_qty");//현재재고따로 저장하고
	            	if(orgQty-buy.getBuy_qty()<0) {//출고량이 재고량보다 많을수 없으니까 처리해주고..
	            		System.out.println("출고량이 재고량보다 많을 수 없습니다.");
		        		return 0;
	            	}
		        }//아니면 추가가능
		        close(rs);
		        close(pstmt);
		        
		        pstmt = conn.prepareStatement(sql);//추가하자
		        pstmt.setInt(1, num);       //순번
	            pstmt.setInt(2, buy.getP_num());       //상품번호
	            pstmt.setInt(3, orgQty + buy.getBuy_qty());   //현재재고        
	            pstmt.setInt(4, buy.getBuy_qty());       //입고
	            pstmt.setInt(5, 0);//출고
	            insertcount = pstmt.executeUpdate();//실행!!
	            if(!(insertcount>0)) {
	            	return 0;
	            }
		    }//반복끝
	    
	    }catch(Exception e) {
	        System.out.println("inventoryDAO inserCancelInOutQty에러임"+e);
	    }finally {
	    	close(rs);
	        close(pstmt);
	    }
	    return insertcount;
	}

	
	
	
}//InventoryDAO끝
