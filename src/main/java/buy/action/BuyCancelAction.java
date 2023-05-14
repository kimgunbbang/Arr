package buy.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import buy.svc.BuyCancelService;
import buy.svc.BuyInfoDeleteService;
import inventory.svc.InventoryInOutService;
import vo.ActionForward;
import vo.Buy;
import vo.Product;

public class BuyCancelAction implements Action {

	@Override//액션에서는
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;//포워드처리하고
		int buy_num = Integer.parseInt(request.getParameter("buy_num"));//request처리하자
		System.out.println("구매번호는?"+buy_num);
		//함부로삭제하지말자...
		//BuyInfoDeleteService buyInfoDeleteService = new BuyInfoDeleteService();//구매정보부터 삭제
		//boolean isCancel = buyInfoDeleteService.deleteBuyInfo(buy_num);//구매번호로 삭제하기
		boolean isCancel = true;
		
		if(isCancel) {//구매정보 삭제됬을때,
			//인벤토리에 재고 다시 넣어놓고,,
			ArrayList<Buy> productList = new ArrayList<Buy>();//구매번호에 맞는 상품정보 담을객체생성
			BuyCancelService buyCancelService = new BuyCancelService();
			productList = buyCancelService.selectCancelProduct(buy_num);//상품번호랑,구매했던 갯수 담겨있음
			
			InventoryInOutService inventoryInOutService = new InventoryInOutService();
			boolean invenChange = inventoryInOutService.cancelinOutQty(productList);
			
			//구매창state를 cancel로 바꾼담에
			boolean isChange = buyCancelService.buyChangeState(buy_num);//state cancel로 바꾸기
			
			if(invenChange && isChange) {//구매창 cancel 로 바꾸고
				//구매목록가기
				response.setContentType("text/html; charset=utf-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>");
	            out.println("alert('주문취소 성.공.적.')");
	            out.println("location.href='buyListForm.buy'");
	            out.println("</script>");
			}
		}
		
		
		
		return forward;
	}

}
