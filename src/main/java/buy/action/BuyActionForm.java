package buy.action;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import delivery.svc.DeliveryListService;
import vo.ActionForward;
import vo.Buy;
import vo.Delivery;

public class BuyActionForm implements Action {

   @Override//액션에서는
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = null;//널처리해주고
      String id = request.getParameter("id");//파라미터처리해주고 //아이디
      String[] p_num = request.getParameterValues("p_num");//상품번호
      System.out.println(Arrays.toString(p_num));
      String[] buy_qty = request.getParameterValues("buy_qty");//구매수량
      String[] p_price = request.getParameterValues("p_price");//상품금액
      
      String[] cartList=null;//cart없애보자.
      if(request.getParameterValues("cart_num") != null) {
         cartList = request.getParameterValues("cart_num");
      }
      
      int lastTotalMoney=0;
      
      ArrayList<Buy> buyList = new ArrayList<Buy>();
      for(int i=0; i<p_num.length;i++) {
         Buy buy = new Buy();
         buy.setId(id); //아이디 set
         buy.setP_num(Integer.parseInt(p_num[i])); //상품번호 set
         buy.setBuy_qty(Integer.parseInt(buy_qty[i])); //수량 set
         buy.setBuy_totalmoney(Integer.parseInt(buy_qty[i])*Integer.parseInt(p_price[i])); //총금액set
         buyList.add(buy);
         lastTotalMoney+=Integer.parseInt(buy_qty[i])*Integer.parseInt(p_price[i]);
         
      }
      
      
      DeliveryListService deliveryListService = new DeliveryListService();
      ArrayList<Delivery> deliveryList = deliveryListService.getDeliveryList(id);
      request.setAttribute("cartList", cartList);//cart번호
      request.setAttribute("deliveryList", deliveryList); //배송지리스트
      request.setAttribute("lastTotalMoney", lastTotalMoney); //찐 전체금액
      request.setAttribute("buyList", buyList); //구매목록들
      request.setAttribute("pagefile","/buy/buyForm.jsp");
      forward = new ActionForward("index.jsp",false);
      //
      return forward;
   }

}