package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import product.svc.ProductModifyService;
import vo.ActionForward;
import vo.Product;

public class ProductModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String saveFolder = "/images";//저장될 폴더경로
		String encoding = "utf-8";//인코딩
		int maxSize = 10*1024*1024;//이미지 최대사이즈 10d메가임
		String realFolder = 
				request.getServletContext().getRealPath(saveFolder); //실제 받아오는 경로+폴더경로
		
		MultipartRequest multi = new MultipartRequest(//파일업로드할때 멀티파트리퀘스트 사용
				request,//첫번째 리퀘스트받기
				realFolder,//두번째 실제경로+폴더경로
				maxSize,//세번째 사이즈
				encoding,//네번째 인코딩방식
				new DefaultFileRenamePolicy());//다섯번째 이건무조건!걍 쓰기
		
		//프로덕트객체 생성해서 안에 값을넣어 저장하기(첨부파일이 있기때문에 멀티파트리퀘스트 꼭만들어야함)
				Product product = new Product(Integer.parseInt(multi.getParameter("p_num")),
						multi.getParameter("p_name"),
						Integer.parseInt(multi.getParameter("p_price")),
						multi.getParameter("p_detail"),
						multi.getFilesystemName("p_image"),
						multi.getFilesystemName("p_image2"),
						multi.getParameter("category_name"),
						Integer.parseInt(multi.getParameter("p_readcount")),
						Boolean.parseBoolean(multi.getParameter("p_hide")),
						0//재고수량임의로넣음..
						);
				
				ProductModifyService productModifyService = new ProductModifyService();
				boolean modifySuccess = productModifyService.productModify(product);
				
				if(modifySuccess) {//수정이 됬으면
					forward = new ActionForward("adminProductList.ad",true);
				}else {//수정이 안됬으면
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('상품수정 실패')");
					out.println("history.back()");
					out.println("</script>");
				}
				
		return forward;
	}

}
