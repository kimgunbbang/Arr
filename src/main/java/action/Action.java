package action;//한프로젝트에서 공통적으로 쓸 액션 인터페이스 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public interface Action{
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
