package vo;//한프로젝트에 공동적으로 쓸 액션포워드

public class ActionForward {
	private String path;
	private boolean redirect;
	
	public ActionForward() {};
	public ActionForward(String path, boolean redirect) {
		super();
		this.path = path;
		this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}
