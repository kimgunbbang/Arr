package buy.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import buy.svc.BuyService;
import inventory.svc.InventoryInOutService;
import vo.ActionForward;
import vo.Buy;

public class BuyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;//포워드 널처리하고
		//리퀘스트값 다 불러와서 저장 한 후,
		String id = request.getParameter("id");				//아이디 가져오고
		String[] p_num=request.getParameterValues("p_num");	//p_num들 가져오고
		String buy_memo=request.getParameter("buy_memo");	//주문상세요청
		String[] buy_qty=request.getParameterValues("buy_qty");//주문수량들
		String[] buy_totalmoney=request.getParameterValues("buy_totalmoney");//상품별 총금액들
		 
		ArrayList<Buy> inventoryCheck = new ArrayList<Buy>();
		for(int i=0;i<p_num.length;i++) {
			Buy buy=new Buy();
			buy.setId(id);
			buy.setP_num(Integer.parseInt(p_num[i]));
			buy.setBuy_memo(buy_memo);
			buy.setBuy_qty(Integer.parseInt(buy_qty[i]));
			buy.setBuy_totalmoney(Integer.parseInt(buy_totalmoney[i]));
			inventoryCheck.add(buy);
		}
		//재고확인하고(insert로 확인해보자.)
		InventoryInOutService inventoryInOutService = new InventoryInOutService();
		boolean invenCheck = inventoryInOutService.inOutQty(inventoryCheck);
		
		if(invenCheck) {//재고가 될때
			//구매테이블에 insert하는 service만들기
			BuyService buyService = new BuyService();
			boolean insertCheck = buyService.insertBuyInfo(inventoryCheck);//insert됬는지 체크
			
			if(insertCheck) {//insert 됬으면 구매완료창 띄우고 main가기
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('구매완료')");
				out.println("</script>");
				request.setAttribute("pagefile", "/main.jsp");
				forward = new ActionForward("/index.jsp",false);
			}
		}else {//재고부터안되면 '구매수량을 다시 확인해주세요.'
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('구매수량을 다시 확인해주세요.')");
			out.println("history.back()");//뒤로가기
			out.println("</script>");
			
		}
		
		return forward;
	}

}
