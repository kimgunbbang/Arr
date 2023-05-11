package buy.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import buy.svc.BuyInfoDeleteService;
import vo.ActionForward;

public class BuyCancelAction implements Action {

	@Override//액션에서는
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;//포워드처리하고
		int buy_num = Integer.parseInt(request.getParameter("buy_num"));//request처리하자
		System.out.println("구매번호는?"+buy_num);
		
		BuyInfoDeleteService buyInfoDeleteService = new BuyInfoDeleteService();//구매정보부터 삭제
		boolean isCancel = buyInfoDeleteService.deleteBuyInfo(buy_num);//구매번호로 삭제하기
		
		if(isCancel) {//구매정보 삭제됬을때,
			//인벤토리에 재고 다시 넣어놓고,,
			
			//구매창state를 cancel로 바꾼담에
			Buy
			//구매목록가기
			response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('구매정보 삭제성공.')");
            out.println("location.href='buyListForm.buy'");
            out.println("</script>");
		}
		
		
		
		return forward;
	}

}
