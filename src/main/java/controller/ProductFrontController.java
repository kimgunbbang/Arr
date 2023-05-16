package controller;

import java.io.IOException;   

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.action.MainAction;
import product.action.ProductAddAction;
import product.action.ProductAllListAction;
import product.action.ProductBestListAction;
import product.action.ProductDeleteAction;
import product.action.ProductDetailViewAction;
import product.action.ProductModifyAction;
import product.action.ProductModifyFormAction;
import product.action.ProductSelectListAction;
import vo.ActionForward;

/**
 * Servlet implementation class ProductFrontController
 */
@WebServlet("*.p")
public class ProductFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command = uri.substring(contextPath.length());
		
		
		System.out.println(command);
		
		Action action=null;//액션초기화
		ActionForward forward=null;//액션포워드초기화
		
		if(command.equals("/productAddForm.p")) {//폼만 열때는 액션필요없이
			request.setAttribute("pagefile", "/product/productAddForm.jsp");//보일경로만적어주고
			forward=new ActionForward("/index.jsp",false);//포워드는 요로케하기
		}else if(command.equals("/productAddAction.p")) {//등록버튼눌렀을 때
			action = new ProductAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/productAllList.p")) {
			action = new ProductAllListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/productSelectList.p")) {
			action = new ProductSelectListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/productDetailView.p")) {
			action = new ProductDetailViewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/productModifyForm.p")) {
			action = new ProductModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/productModifyAction.p")) {
			action = new ProductModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/main.p")) {
			action = new MainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/productRecent.p")) {
			request.setAttribute("pagefile", "/product/productRecentForm.jsp");//보일경로만적어주고
			forward=new ActionForward("/index.jsp",false);//포워드는 요로케하기
		}else if(command.equals("/productBestList.p")) {
			action = new ProductBestListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/deleteProduct.p")) {
			action = new ProductDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//널처리!!!!
		if(forward != null) {//포워드 널처리 무조건 해줘야함 null이 아닐때,
	         if(forward.isRedirect()) {//널아니고 다이렉트사용할때
	            response.sendRedirect(forward.getPath());
	         }else {//널아니고 디스패쳐사용할때
	            RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());//여기서 여기서 F2 눌러서 ServletException 처리 해줘야함
	            dispatcher.forward(request, response);//루트경로를 찾기위해서 디스패쳐사용함
	         }
	     }
	}//doProcess끝

}//클래스끝
