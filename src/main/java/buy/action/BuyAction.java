package buy.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import action.Action;
import buy.svc.BuyCartDeleteService;
import buy.svc.BuyInfoService;
import buy.svc.BuyService;
import inventory.svc.InventoryInOutService;
import vo.ActionForward;
import vo.Buy;
import vo.BuyInfo;

public class BuyAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = null;//포워드 널처리하고
      //리퀘스트값 다 불러와서 저장 한 후,
      HttpSession session = request.getSession();
      String id = null;
      if(request.getSession().getAttribute(id) == null) {//리퀘스트 세션아이디가 널일때(비회원일때)
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
    	  id=uuid;//다시저장해주고,,
    	  String[] p_num=request.getParameterValues("p_num");   //p_num들 가져오고
          String[] buy_qty=request.getParameterValues("buy_qty");//주문수량들
          String[] buy_totalmoney=request.getParameterValues("buy_totalmoney");//상품별 총금액들
          
          String deli_username=request.getParameter("deli_username");
          String deli_phone=request.getParameter("deli_phone");
          String deli_zipcode=request.getParameter("deli_zipcode");
          String deli_addr=request.getParameter("deli_addr");
          String deli_addr2=request.getParameter("deli_addr2");
          String deli_memo=request.getParameter("deli_memo");   //주문상세요청
          
          ArrayList<Buy> inventoryCheck = new ArrayList<Buy>();
          for(int i=0;i<p_num.length;i++) {
             Buy buy=new Buy();
             buy.setId(id);
             buy.setP_num(Integer.parseInt(p_num[i]));
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
             
             int buy_num = buyService.getBuyNum(id);//아이디로 구매번호가져오기
             boolean insertCheck2=false;
             if(buy_num>0) {
                //구매상세테이블 insert하는 service만들기
                BuyInfoService buyInfoService = new BuyInfoService();
                BuyInfo buyInfo = new BuyInfo();
                buyInfo.setBuy_num(buy_num);
                buyInfo.setBuy_name(deli_username);
                buyInfo.setBuy_phone(deli_phone);
                buyInfo.setBuy_zipcode(deli_zipcode);
                buyInfo.setBuy_addr(deli_addr);
                buyInfo.setBuy_addr2(deli_addr2);
                buyInfo.setDeli_memo(deli_memo);
                
                insertCheck2 = buyInfoService.insertBuy(buyInfo);
             }
             if(insertCheck && insertCheck2) {//둘다 insert 됬으면
                //cart목록부터 없애보자
                String[] cartList=null;
    	            if(request.getParameterValues("cart_num") != null) {
    	               cartList = request.getParameterValues("cart_num");//1, 2
    	               BuyCartDeleteService buyCartDeleteService = new BuyCartDeleteService();//방금산거 cart목록 없애기
    	               buyCartDeleteService.nonCartDelete(cartList);
    	            }
                //구매완료창보여주기
                request.setAttribute("pagefile", "/buy/buySuccess.jsp");
                forward = new ActionForward("index.jsp",false);
             }
          }else {//재고부터안되면 '구매수량을 다시 확인해주세요.'
             response.setContentType("text/html; charset=utf-8");
             PrintWriter out = response.getWriter();
             out.println("<script>");
             out.println("alert('구매수량을 다시 확인해주세요.')");
             out.println("history.back()");//뒤로가기
             out.println("</script>");
             
          }
      }else {//세션아이디 있을때(회원일때)
    	  id=(String)session.getAttribute("id");         //아이디 가져오고
          String[] p_num=request.getParameterValues("p_num");   //p_num들 가져오고
          String[] buy_qty=request.getParameterValues("buy_qty");//주문수량들
          String[] buy_totalmoney=request.getParameterValues("buy_totalmoney");//상품별 총금액들
          
          String deli_username=request.getParameter("deli_username");
          String deli_phone=request.getParameter("deli_phone");
          String deli_zipcode=request.getParameter("deli_zipcode");
          String deli_addr=request.getParameter("deli_addr");
          String deli_addr2=request.getParameter("deli_addr2");
          String deli_memo=request.getParameter("deli_memo");   //주문상세요청
          
          
          ArrayList<Buy> inventoryCheck = new ArrayList<Buy>();
          for(int i=0;i<p_num.length;i++) {
             Buy buy=new Buy();
             buy.setId(id);
             buy.setP_num(Integer.parseInt(p_num[i]));
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
             
             int buy_num = buyService.getBuyNum(id);//아이디로 구매번호가져오기
             boolean insertCheck2=false;
             if(buy_num>0) {
                //구매상세테이블 insert하는 service만들기
                BuyInfoService buyInfoService = new BuyInfoService();
                BuyInfo buyInfo = new BuyInfo();
                buyInfo.setBuy_num(buy_num);
                buyInfo.setBuy_name(deli_username);
                buyInfo.setBuy_phone(deli_phone);
                buyInfo.setBuy_zipcode(deli_zipcode);
                buyInfo.setBuy_addr(deli_addr);
                buyInfo.setBuy_addr2(deli_addr2);
                buyInfo.setDeli_memo(deli_memo);
                
                insertCheck2 = buyInfoService.insertBuy(buyInfo);
             }
             if(insertCheck && insertCheck2) {//둘다 insert 됬으면
                //cart목록부터 없애보자
                String[] cartList=null;
    	            if(request.getParameterValues("cart_num") != null) {
    	               cartList = request.getParameterValues("cart_num");//1, 2
    	               BuyCartDeleteService buyCartDeleteService = new BuyCartDeleteService();//방금산거 cart목록 없애기
    	               buyCartDeleteService.cartDelete(cartList);
    	            }
                //구매완료창보여주기
                request.setAttribute("pagefile", "/buy/buySuccess.jsp");
                forward = new ActionForward("index.jsp",false);
             }
          }else {//재고부터안되면 '구매수량을 다시 확인해주세요.'
             response.setContentType("text/html; charset=utf-8");
             PrintWriter out = response.getWriter();
             out.println("<script>");
             out.println("alert('구매수량을 다시 확인해주세요.')");
             out.println("history.back()");//뒤로가기
             out.println("</script>");
             
          }
      }
      
      
      return forward;
   }

}