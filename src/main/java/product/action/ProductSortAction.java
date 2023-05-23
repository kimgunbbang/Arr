package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Product;

public class ProductSortAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String category_name=request.getParameter("category_name");
		String sort = request.getParameter("sort");
		System.out.println(sort);
		System.out.println(category_name);
		
		if(category_name.equals("") || category_name == null) {//카테고리명이 없을때는
			ProductListService productListService = new ProductListService();
			ArrayList<Product> productList = productListService.getProductAllSortList(sort);
			
			request.setAttribute("productList", productList);
		    request.setAttribute("pagefile", "/product/productList.jsp");
			forward = new ActionForward("/index.jsp",false);//보여주는경로
			return forward;
		}else {//카테고리명이 있을때는 
			ProductListService productListService = new ProductListService();
			ArrayList<Product> productList = productListService.getProductSelectList(category_name,sort);
			
			request.setAttribute("category_name", category_name);
			request.setAttribute("productList", productList);
			request.setAttribute("pagefile", "/product/productList.jsp");
			
			forward = new ActionForward("/index.jsp",false);//보여주는경로
			return forward;
		}
		
		
	}

}
