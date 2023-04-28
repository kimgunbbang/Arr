package inventory.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import inventory.svc.InventoryInOutService;
import vo.ActionForward;
import vo.Inventory;

public class InventoryInOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int p_num=Integer.parseInt(request.getParameter("p_num"));
		int inven_in;
		int inven_out;
		//null처리
		if(request.getParameter("inven_in").equals("")) {
			inven_in=0;
		}else {
			inven_in = Integer.parseInt(request.getParameter("inven_in"));
		}
		
		if(request.getParameter("inven_out").equals("")) {
			inven_out=0;
		}else {
			inven_out = Integer.parseInt(request.getParameter("inven_out"));
		}
		
		
		Inventory inventory = new Inventory();
		inventory.setP_num(p_num);
		inventory.setInven_in(inven_in);
		inventory.setInven_out(inven_out);
		
		
		InventoryInOutService inventoryInOutService = new InventoryInOutService();
		boolean success = inventoryInOutService.inOutQty(inventory);
		
		if(success) {
			forward = new ActionForward("inventoryList.in",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('재고입출고등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}


}
