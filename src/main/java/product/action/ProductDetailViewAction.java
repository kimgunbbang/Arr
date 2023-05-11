package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import inventory.action.InventoryListAction;
import inventory.svc.InventoryListService;
import product.svc.ProductDetailViewService;
import vo.ActionForward;
import vo.Inventory;
import vo.Product;

public class ProductDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		
		ProductDetailViewService productDetailViewService = new ProductDetailViewService();
		Product product = productDetailViewService.getProductView(p_num);//Product객체 하나가져올때
		
		HttpSession session = request.getSession();
		ArrayList<Product> recentViewProduct = (ArrayList<Product>) session.getAttribute("recentViewProduct");
		
		if (recentViewProduct == null) {
            recentViewProduct = new ArrayList<>();
        }
		
		  boolean isDuplicate = false;
		    int duplicateIndex = -1;
		    for (int i = 0; i < recentViewProduct.size(); i++) {
		        Product recentProduct = recentViewProduct.get(i);
		        if (recentProduct.getP_num() == product.getP_num()) {
		            isDuplicate = true;
		            duplicateIndex = i;
		            break;
		        }
		    }
	
		    if (isDuplicate) {
		        // 이미 최근 본 상품 목록에 있는 경우, 해당 상품을 리스트에서 제거하고 맨 앞에 추가
		        recentViewProduct.remove(duplicateIndex);
		    }
		    
        recentViewProduct.add(0, product);
        
        if (recentViewProduct.size() > 10) {
            recentViewProduct.subList(10, recentViewProduct.size()).clear();
        }

        
        
        session.setAttribute("recentViewProduct", recentViewProduct);
		
		
		
		request.setAttribute("product", product);
		request.setAttribute("pagefile", "/product/productDetailView.jsp");
		forward=new ActionForward("/index.jsp",false);
		return forward;
	}

}
