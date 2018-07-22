$(function(){
	//显示登陆框,隐藏注册框
	$('#switch_qlogin').click(function(){
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('#qlogin').css('display','none');
		$('#web_qr_login').css('display','block');
		});
	//显示注册框,隐藏登陆框
	$('#switch_login').click(function(){
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'154px',width:'70px'});
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		});

	var pwdmin = 3;
	function login(callback){
		if ($('#username').val() == "") {
			$('#username').focus();
			alert("用户名不能为空");
			callback();
			return false;
		}
		if ($('#password').val() == "") {
			$('#password').focus();
			alert("密码不能为空");
			callback();
			return false;
		}
		//提交[登陆]表单
		$.post("login.do", $("#login_form").serialize(), function(data, status) {
			if (status == "success") {
				alert(data);
				if(data=="登陆成功！")
					window.location.href="index.jsp";
			} else {
				alert("失败! status=" + status);
			}
			callback();
		});
//		$('#login_form').submit();
	}
	//登陆按扭
	$('#login_button').click(function(){
		$('#login_button').css("cursor","wait");
		$("#login_button").attr('disabled',true);
		login(function(){
			$('#login_button').css("cursor","pointer");
			$("#login_button").attr('disabled',false);
		});
	});
	function register(callback){
		if ($('#user').val() == "") {
			$('#user').focus();
			$('#userCue').html("<font color='red'><b>×用户名不能为空</b></font>");
			callback();
			return false;
		}
		if ($('#passwd').val() == "") {
			$('#passwd').focus();
			$('#userCue').html("<font color='red'><b>×密码不能为空</b></font>");
			callback();
			return false;
		}
		if ($('#passwd').val().length < pwdmin) {
			$('#passwd').focus();
			$('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
			callback();
			return false;
		}
		if ($('#passwd2').val() != $('#passwd').val()) {
			$('#passwd2').focus();
			$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
			callback();
			return false;
		}
		if ($('#id').val() == "") {
			$('#id').focus();
			$('#userCue').html("<font color='red'><b>×学号不能为空</b></font>");
			callback();
			return false;
		}
		var sid = /^1[0-9]{9}$/;
		if (!sid.test($('#id').val())) {
			$('#id').focus();
			$('#userCue').html("<font color='red'><b>×学号格式不正确</b></font>");
			callback();
			return false;
		}
		if ($('#realname').val() == "") {
			$('#realname').focus();
			$('#userCue').html("<font color='red'><b>×真实姓名不能为空</b></font>");
			callback();
			return false;
		}
		
		$.post("register.do", $("#regUser").serialize(), function(data, status) {
			if (status == "success") {
				$('#userCue').html("<font color='red'><b>"+data+"</b></font>");
			} else {
				alert("失败!status=" + status);
			}
			callback();
		});
//		$('#regUser').submit();
	}
	//注册按扭
	$('#reg').click(function() {
		$('#reg').css("cursor","wait");
		$("#reg").attr('disabled',true); 
		register(function(){
			$('#reg').css("cursor","pointer");
			$("#reg").attr('disabled',false);
		});
	});
});


//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) { 
    var params = location.search.substr(1); // 获取参数 平且去掉？ 
    var ArrParam = params.split('&'); 
    if (ArrParam.length == 1) { 
        //只有一个参数的情况 
        return params.split('=')[1]; 
    } 
    else { 
         //多个参数参数的情况 
        for (var i = 0; i < ArrParam.length; i++) { 
            if (ArrParam[i].split('=')[0] == pname) { 
                return ArrParam[i].split('=')[1]; 
            } 
        } 
    } 
}