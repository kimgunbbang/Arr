package buy.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import buy.svc.BuyInfoService;
import buy.svc.BuyService;
import vo.ActionForward;
import vo.Buy;

public class BuyInfoFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");//세션에 아이디 받아와서
		
		//구매인포 불러와서 뿌려주쟈
		ArrayList<Buy> buyList = new ArrayList<Buy>();//구매목록담을 배열
		BuyService buyService = new BuyService();
		buyList = buyService.getbuyList(id);
		
		request.setAttribute("buyList", buyList);
		request.setAttribute("pagefile", "buyInfoForm.jsp");
		forward = new ActionForward("index.jsp",false);
		return forward;
	}

}
