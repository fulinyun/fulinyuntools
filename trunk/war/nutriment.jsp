<%@ page contentType="text/html;charset=gb2312" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="health.Nutriment" %>
<%@ page import="health.PMF" %>
<%
request.setCharacterEncoding("gb2312");
%>
<html>
  <head>
	<meta http-equiv="Content-Type"	content="text/html; charset=gb2312">
<!-- <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" /> -->
  </head>

  <body>

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
%>
<p>���ã�<%= user.getNickname() %>����������
<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">�ǳ�</a>����</p>
<%
    } else {
%>
<p>���ã�
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">����</a>
�Ա��������׵���Ŀ�а�������������</p>
<%
    }
%>

<%
    PersistenceManager pm = PMF.get().getPersistenceManager();
    String query = "select from " + Nutriment.class.getName() + " order by date desc range 0,5";
    List<Nutriment> nutriments = (List<Nutriment>) pm.newQuery(query).execute();
    if (nutriments.isEmpty()) {
%>
<p>Ӫ���ر��ǿյġ�</p>
<%
    } else {
        for (Nutriment n : nutriments) {
%>
<p><%= n.getDate().toString()+" " %>
<%
            if (n.getAuthor() == null) {
%>
�����û�����ˣ�&nbsp
<%
            } else {
%>
<b><%= n.getAuthor().getNickname() %></b> ����ˣ�&nbsp
<%
            }
%>
<%= n.getID()+","+n.getType()+","+n.getName()+","+n.getEnergyContent()+","+n.getIntroduction() %></p>
<%
        }
    }
    pm.close();
%>

	<form action="/nutriment" method="post">
    	<table border=0>
    	<tr><td>Ӫ�������</td><td><input type="text" name="type"></td></tr>
    	<tr><td>Ӫ��������</td><td><input type="text" name="name"></td></tr>
    	<tr><td>��������(KJ/g)��</td><td><input type="text" name="energyContent"></td></tr>
    	<tr><td>���ܣ�</td><td><input type="text" name="introduction"></td></tr>
      	</table>
		<div><input type="submit" value="���Ӫ����" /></div>
    </form>

  </body>
</html>
