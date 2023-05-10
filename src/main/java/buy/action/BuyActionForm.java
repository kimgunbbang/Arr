package buy.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartListService;
import delivery.svc.DeliveryListService;
import vo.ActionForward;
import vo.Buy;
import vo.Cart;
import vo.Delivery;

public class BuyActionForm implements Action {

   @Override//액션에서는
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = null;//널처리해주고
      String id = null;//파라미터처리해주고 //아이디
      String[] p_num = {};//상품번호
      String[] buy_qty = {};//구매수량
      String[] p_price = {};//상품금액
      int lastTotalMoney=0;//완전토탈
      System.out.println("이거 뭐야"+request.getParameterValues("remove"));
      if(request.getSession().getAttribute("id") == null) {//세션아이디가 null일때
    	  Cookie[] cookies = request.getCookies();//쿠키정보가져와서
    	  String uuid = null;
    	  if (cookies != null) {
    	      for (Cookie cookie : cookies) {
    	          if (cookie.getName().equals("uuid")) {//비회원아이디가져온담에
    	              uuid = cookie.getValue();//값지정하고
    	              break;
    	          }
    	      }
    	  }
    	  id=uuid;
    	  System.out.println("uuid알려줘 : "+id);
    	  if(request.getParameterValues("remove") == null){//장바구니널일때,
        	  p_num = request.getParameterValues("p_num");
        	  if(p_num.length == 1) {//p_num이 한개일때
        		  id = uuid;//파라미터처리해주고 //아이디
                  p_num = request.getParameterValues("p_num");//상품번호
                  buy_qty = request.getParameterValues("buy_qty");//구매수량
                  p_price = request.getParameterValues("p_price");//상품금액
                  
                  lastTotalMoney=0;
                  
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
                  ArrayList<Delivery> deliveryList = deliveryListService.getDeliveryList(id);//배송리스트가져오기
                  request.setAttribute("deliveryList", deliveryList); //배송지리스트
                  request.setAttribute("lastTotalMoney", lastTotalMoney); //찐 전체금액
                  request.setAttribute("buyList", buyList); //구매목록들
                  request.setAttribute("pagefile","/buy/buyForm.jsp");
                  forward = new ActionForward("index.jsp",false);
        	  }else {
        		  response.setContentType("text/html;charset=UTF-8"); 
        		  PrintWriter out = response.getWriter(); out.println("<script>");
        		  out.println("alert('구매하실 상품을 선택해주세요')"); 
        		  out.println("history.back()");
        		  out.println("</script>");
        	  }
        	  
          }else {//장바구니가 널아닐때,
        	  String[] cartList=request.getParameterValues("remove");//장바구니없애기용 cart번호
        	  System.out.println("카트넘버"+Arrays.toString(cartList));
        	  //카트넘버랑 아이디 받아와서,카트에 있는 상품번호구매수량, 상품금액 가져오기
              id = uuid;//파라미터처리해주고 //아이디
              ArrayList<Cart> cartSet = new ArrayList<Cart>();
              CartListService cartListService = new CartListService();
              cartSet = cartListService.selectNonCartList(cartList);//
              
              
//              p_num = request.getParameterValues("p_num");//상품번호
//              buy_qty = request.getParameterValues("buy_qty");//구매수량
//              p_price = request.getParameterValues("p_price");//상품금액
              
              lastTotalMoney=0;
              
              ArrayList<Buy> buyList = new ArrayList<Buy>();
              for(int i=0; i<cartSet.size();i++) {
                 Buy buy = new Buy();
                 buy.setId(id); //아이디 set
                 buy.setP_num(cartSet.get(i).getP_num()); //상품번호 set
                 buy.setBuy_qty(cartSet.get(i).getCart_qty()); //수량 set
                 buy.setBuy_totalmoney(cartSet.get(i).getCart_qty()*cartSet.get(i).getP_price()); //총금액set
                 buyList.add(buy);
                 lastTotalMoney+=cartSet.get(i).getCart_qty()*cartSet.get(i).getP_price();
                 
              }
              
              
              DeliveryListService deliveryListService = new DeliveryListService();
              ArrayList<Delivery> deliveryList = deliveryListService.getDeliveryList(id);
              request.setAttribute("cartList", cartList);//cart번호
              request.setAttribute("deliveryList", deliveryList); //배송지리스트
              request.setAttribute("lastTotalMoney", lastTotalMoney); //찐 전체금액
              request.setAttribute("buyList", buyList); //구매목록들
              request.setAttribute("pagefile","/buy/buyForm.jsp");
              forward = new ActionForward("index.jsp",false);
        	 
          }
    	  /***************************여기까지가 비회원구매일때*************************************/
      }else {//세션아이디가 null아닐때
    	  id=(String)request.getSession().getAttribute("id");
    	  if(request.getParameterValues("remove") == null){//장바구니널일때,
        	  p_num = request.getParameterValues("p_num");
        	  if(p_num.length == 1) {//p_num이 한개일때
        		  id = request.getParameter("id");//파라미터처리해주고 //아이디
                  p_num = request.getParameterValues("p_num");//상품번호
                  buy_qty = request.getParameterValues("buy_qty");//구매수량
                  p_price = request.getParameterValues("p_price");//상품금액
                  
                  lastTotalMoney=0;
                  
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
                  request.setAttribute("deliveryList", deliveryList); //배송지리스트
                  request.setAttribute("lastTotalMoney", lastTotalMoney); //찐 전체금액
                  request.setAttribute("buyList", buyList); //구매목록들
                  request.setAttribute("pagefile","/buy/buyForm.jsp");
                  forward = new ActionForward("index.jsp",false);
        	  }else {
        		  response.setContentType("text/html;charset=UTF-8"); 
        		  PrintWriter out = response.getWriter(); out.println("<script>");
        		  out.println("alert('구매하실 상품을 선택해주세요')"); 
        		  out.println("history.back()");
        		  out.println("</script>");
        	  }
        	  
          }else {//장바구니가 널아닐때,
        	  String[] cartList=request.getParameterValues("remove");//장바구니없애기용 cart번호
        	  System.out.println("카트넘버"+Arrays.toString(cartList));
        	  //카트넘버랑 아이디 받아와서,카트에 있는 상품번호구매수량, 상품금액 가져오기
              id = request.getParameter("id");//파라미터처리해주고 //아이디
              ArrayList<Cart> cartSet = new ArrayList<Cart>();
              CartListService cartListService = new CartListService();
              cartSet = cartListService.selectCartList(cartList);//
              
              lastTotalMoney=0;
              
              ArrayList<Buy> buyList = new ArrayList<Buy>();
              for(int i=0; i<cartSet.size();i++) {
                 Buy buy = new Buy();
                 buy.setId(id); //아이디 set
                 buy.setP_num(cartSet.get(i).getP_num()); //상품번호 set
                 buy.setBuy_qty(cartSet.get(i).getCart_qty()); //수량 set
                 buy.setBuy_totalmoney(cartSet.get(i).getCart_qty()*cartSet.get(i).getP_price()); //총금액set
                 buyList.add(buy);
                 lastTotalMoney+=cartSet.get(i).getCart_qty()*cartSet.get(i).getP_price();
                 
              }
              
              
              DeliveryListService deliveryListService = new DeliveryListService();
              ArrayList<Delivery> deliveryList = deliveryListService.getDeliveryList(id);
              request.setAttribute("cartList", cartList);//cart번호
              request.setAttribute("deliveryList", deliveryList); //배송지리스트
              request.setAttribute("lastTotalMoney", lastTotalMoney); //찐 전체금액
              request.setAttribute("buyList", buyList); //구매목록들
              request.setAttribute("pagefile","/buy/buyForm.jsp");
              forward = new ActionForward("index.jsp",false);
        	 
          }
      }
      
      
      return forward;
   }

}