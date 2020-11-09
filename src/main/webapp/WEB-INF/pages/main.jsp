<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/inc/head.jsp"></jsp:include>
<div class="mbxnav">
代理商管理\当前账户信息
</div>
<div class="container">
	<div class="userInfo">
		<ul>
			<li><b>您好！${sessionScope.result.data.userName}</b>
				您上次登录时间 <fmt:formatDate value="${sessionScope.result.data.lastLoginTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" />
			</li>
		</ul>
	</div>
</div>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>
