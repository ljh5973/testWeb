<%@page import="com.testweb.user.model.UserVO"%>
<%@page import="com.testweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	request.setCharacterEncoding("utf-8");

	String id=request.getParameter("id");
	String pw=request.getParameter("pw");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	String email2=request.getParameter("email2");
	String phone1=request.getParameter("phone1");
	String phone2=request.getParameter("phone2");
	String phone3=request.getParameter("phone3");
	String addb=request.getParameter("addressbasic");
	String addd=request.getParameter("addressdetail");
	
	
	System.out.println(id+"id");
	System.out.println(pw+"pw");
	System.out.println(name+"name");
	System.out.println(email+"email");
	System.out.println(email2+"email2");
	System.out.println(phone1+"phone1");
	System.out.println(phone2+"phone2");
	System.out.println(phone3+"phone3");
	System.out.println(addb+"addressb");
	System.out.println(addd+"addressd");
	
	
	int a=Integer.parseInt(phone1);
	int b=Integer.parseInt(phone2);
	int c=Integer.parseInt(phone3);
	
	UserDAO dao=UserDAO.getInstance();
	int result=dao.checkId(id);
	if(result==1){%>
<script>
	alert("이미 가입된 아이디입니다.");
	history.go(-1);
</script>

	<%}else{
		
		UserVO vo= new UserVO(id,pw,name,a,b,c,email,email2,addb,addd);
		int result2=dao.join(vo);
		if(result2==1){	//1이면 성공 0 이면 실패 %>
			
<script>
	alert("회원가입을 축하합니다.");
	location.href="user_login.jsp";
</script>
		<%}else{%>
<script>
	alert("네트워크 오류입니다. 관리자에게 문의하세요");
	location.href="user_join.jsp";
</script>
		
		<%	
		}
		
	}
	
%>