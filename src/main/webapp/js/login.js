function validateLoginUserFunc() {

	var flag = false;

	var usercode = $.trim($("#usercode").val());
	var userpassword = $.trim($("#userpassword").val());

	if (usercode == "" || usercode == null) {

		$("#usercode").focus();
		alert("用户名不能为空");
	} else if (userpassword == "" || userpassword == null) {

		$("#userpassword").focus();
		alert("不能为空");
	} else {
		$.ajax({
			url : '/validateLoginUser.action',
			type : 'post',
			async : false,
			data : {
				'user.userCode' : usercode,
				'user.userPassword' : userpassword
			},
			dataType:'html',
			success : function(data) {
				if ("noexitusercode" == data) {
					alert("用户不存在");
				} else if ("errorpwd" == data) {
					alert("密码错误 ");
				} else if ("failed" == data) {
					alert("系统错误");
				} else if ("success" == data) {
					flag = true;
				}
			}

		});		
	}

	return flag;
}