<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>代理商管理系统</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
</head>

<body>
<!-- 页头 -->
<div class="head">
    <h1>
        AgentSyste|代理商管理系统 <span>v1.0</span>
    </h1>
</div>
<!-- content -->
<div class="container">
    <form action="${pageContext.request.contextPath}/user/login" method="post"
    <%--onsubmit="return validateLoginUserFunc();"--%>>
        <h1>登录系统|Sign in</h1>
        <ul class="loginul">
            <li>登录账号：<input type="text" id="usercode" name="userCode">
            <li>登录密码：<input type="password" id="userpassword"
                            name="userPassword">
        </ul>
        <input type="submit" value="登录">
    </form>
    ${result == null?"null":result.msg }
<%--    <c:if test="${result  2000}">
        <c:out value="${result.msg}"></c:out>
    </c:if>--%>
</div>
<!-- 页尾 -->
<jsp:include page="/inc/foot.jsp"></jsp:include>
<div></div>
</body>
</html>
