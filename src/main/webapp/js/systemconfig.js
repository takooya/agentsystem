$().ready(function() {
	mover(5);
	// 显示添加面板
	$("#addsystemconfig").click(function() {
		$("#addSystemdiv").slideDown();// 显示
	});
	// 点击保存事件
	$("#addSystemConfigBtn").click(function() {
		// 需要configType,configTypeName,isStart验证
		var configType = $("#addConfigType").val();
		var configTypeName = $("#addConfigName").val();
		var isStart = $("#addIsStartSelect").val();

		var configValue;
	    //根据情况判断是否需要configValue
		if($("#addConfigValue").val()!=null)
			configValue=$("#addConfigValue").val();
		
		if (configType == '')
			humane.error("配置类型不能为空");
		else if (configTypeName == '')
			humane.error("配置类型名称不能为空");
		else {

			// 提交到action处理
			$.post("/addconfig.action", {
				"systemConfig.configType" : configType,
				"systemConfig.configTypeName" : configTypeName,
				"systemConfig.configValue":configValue,
				"systemConfig.isStart" : isStart
			}, function(result) {// peat 重复；nopeat：未重复;success成功
				if (result == "peat") {
					humane.error("对不起，该 类型名称已存在");
				} else {

					humane.success("已添加成功 ");
					$("#addSystemdiv").slideUp();// 隐藏
					window.location.reload(true);
				}
			}, "html");
		}

	});
	// 点击取消保存事件
	$("#cancleAddSystemConfigBtn").click(function() {

		$("#addSystemdiv").slideUp();// 隐藏
	});

	// 显示修改面板
	$(".modifySystemBtn").click(function() {
		// 初始化数据
		var obj = $(this);// 获取当前的点击的span标签
		$("#sid").val(obj.attr("sid"));
		$("#modifyConfigName").val(obj.attr("sname"));
		$("#modifyIsStartSelect").val(obj.attr("isstart"));
		$("#modifyConfigValue").val(obj.attr("svalue"));
		$("#modifySystemdiv").slideDown();// 显示
	});
	// 点击取消修改保存事件
	$("#cancleModifySystemConfigBtn").click(function() {

		$("#modifySystemdiv").slideUp();// 隐藏
	});

	// 点击修改保存事件
	$("#modifySystemConfigBtn").click(function() {
		var id = $("#sid").val();
		var configType = $("#modifyConfigType").val();
		var configTypeName = $("#modifyConfigName").val();
		var isStart = $("#modifyIsStartSelect").val();

		var configValue;
	    //根据情况判断是否需要configValue
		if($("#modifyConfigValue").val()!=null)
			configValue=$("#modifyConfigValue").val();
		
		
		if (configType == '')
			humane.error("配置类型不能为空");
		else if (configTypeName == '')
			humane.error("配置类型名称不能为空");
		else {

			// 提交到action处理
			$.post("/modifyconfig.action", {
				"systemConfig.id" : id,
				"systemConfig.configType" : configType,
				"systemConfig.configTypeName" : configTypeName,
				"systemConfig.configValue" : configValue,
				"systemConfig.isStart" : isStart
			}, function(result) {// peat 重复；nopeat：未重复;success成功

				humane.success("已修改成功 ");
				$("#modifySystemdiv").slideUp();// 隐藏
				window.location.reload(true);

			}, "html");
		}
	});

	// 删除配置项
	$(".deleteSystemBtn").click(function() {
		var obj = $(this);
		var id = obj.attr("sid");
		$.post("/deleteconfig.action", {
			"systemConfig.id" : id
		}, function(result) {
			if (result == "success") {
				humane.success("已删除成功 ");
				window.location.reload(true);
			}
			else
				humane.success("删除失败");
		}, "html");

	});
	
	//服务年限配置修改
	$("#simpleBtn").click(function() {
		var configType=$("#configType").val();
		var simpleConfigId=$("#simpleConfigId").val();
		var simpleTypeName=$("#simpleTypeName").val();
		var simpleConfigValue=$("#simpleConfigValue").val();
		if(simpleTypeName==''){
			humane.error("配置名称不能为空");
		}else if(simpleConfigValue=='')
		{
			humane.error("配置数值不能为空！");
		}else{
			
			$.post("/modifyconfig.action", {
				"systemConfig.id" : simpleConfigId,
				"systemConfig.configType" : configType,
				"systemConfig.configTypeName" : simpleTypeName,
				"systemConfig.configValue" : simpleConfigValue				
			}, function(result) {// peat 重复；nopeat：未重复;success成功

				humane.success("已修改成功 ");
				$("#modifySystemdiv").slideUp();// 隐藏
				window.location.reload(true);

			}, "html");
		}
		
			
		
		
	});
});
