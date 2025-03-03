<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%
    Class<?> userClass = Class.forName("User");
    Object user = userClass.getDeclaredConstructor().newInstance();
    java.lang.reflect.Method getName = userClass.getMethod("getName");
    out.println("User class: " + userClass.getName());
    out.println("<br>Default name: " + getName.invoke(user));
%>
