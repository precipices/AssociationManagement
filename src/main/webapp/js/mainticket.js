laydate.render({
	elem : '#beginTime_text' // 指定元素
});
// 时间选择器
//laydate.render({
//	elem : '#beginTime_text2',
//	type : 'datetime'
//});
var flights;//查询到的所有航班
var flightInfo;	//点击购票的航班
var num;		//选择的航班的机票余量
var customers;	//当前客户保存的所有的乘客ID
var selectcustomers=new Array();//所有选择的要购票的乘客
$(function() {
	// 查询航班
	function getFlights(releaseButton) {
		var beginTime = $('#beginTime_text').val();
		var fromWhere = $('#fromWhere_text').val();
		var toWhere = $('#toWhere_text').val();
		if (beginTime == "") {
			alert("出发日期不能为空!");
			releaseButton();
			return;
		}
		if (fromWhere == "") {
			alert("出发地不能为空!");
			releaseButton();
			return;
		}
		if (toWhere == "") {
			alert("目的地不能为空!");
			releaseButton();
			return;
		}
		$.post("FlightServlet", {
			"method" : "getFlights",
			"beginTime" : beginTime,
			"fromWhere" : fromWhere,
			"toWhere" : toWhere
		}, function(data, status) {
			if (status == "success") {
				if(data=="时间类型不正确!"){
					alert("时间类型不正确!");
					releaseButton();
					return;
				}
				$("#select_flight_table tr:not(:first)").remove();
				flights= JSON.parse(data);
				for (i = 0; i < flights.length; i++) {
					var flight = flights[i];
					$('#select_flight_table').append(
									'<tr><td>'
									+ flight.flight
									+ '</td><td>'
									+ new Date(flight.beginTime)
											.toLocaleString() + '</td><td>'
									+ flight.fromWhere + '</td><td>'
									+ flight.toWhere + '</td><td>'
									+ flight.price + '</td><td><input type="button" class="buttonstyle2" value="购票"></td></tr>');
				}
			} else {
				alert("失败!status=" + status);
			}
			releaseButton();
			return;
		});
	}
	//点击查询航班按扭
	$('#getFlights_button').click(function() {
		$('#getFlights_button').css("cursor","wait");
		$("#getFlights_button").attr('disabled',true);
		getFlights(function(){
			$('#getFlights_button').css("cursor","pointer");
			$("#getFlights_button").attr('disabled',false);
		});
	});
	//定义两个城市选择器
	var cityPicker1 = new HzwCityPicker({
	    data: data,
	    target: 'fromWhere_text',
	    valType: 'k-v',
	    hideCityInput: {
	        name: 'city',
	        id: 'city'
	    },
	    hideProvinceInput: {
	        name: 'province',
	        id: 'province'
	    },
	    callback: function(){
	    }
	});
	var cityPicker2 = new HzwCityPicker({
	    data: data,
	    target: 'toWhere_text',
	    valType: 'k-v',
	    hideCityInput: {
	        name: 'city2',
	        id: 'city2'
	    },
	    hideProvinceInput: {
	        name: 'province2',
	        id: 'province2'
	    },
	    callback: function(){
	    }
	});
	cityPicker1.init();
	cityPicker2.init();
	
	//初始时选项卡不允许点击
	$("#select_flight_tab").attr('disabled',true);
	$("#check_ticket_tab1").attr('disabled',true);
	$("#select_customer_tab").attr('disabled',true);
	$("#online_pay_tab").attr('disabled',true);
	$("#check_ticket_tab2").attr('disabled',true);
	//点击购票按扭,得到机票数量并跳转到确认机票1的div
	$("#select_flight_table").on('click', 'input[type="button"][value="购票"]', function() {
		//设置选项卡可点击性
		$("#select_flight_tab").attr('disabled',false);
		$("#check_ticket_tab1").attr('disabled',false);
		$("#select_customer_tab").attr('disabled',true);
		$("#online_pay_tab").attr('disabled',true);
		$("#check_ticket_tab2").attr('disabled',true);
		//得到选择行的机票的数据
		var rowNum=$(this).parent().parent().index()-1;
		flightInfo=flights[rowNum];
		//post从服务器获取机票余量
		$.post("FlightServlet", {
			"method" : "getFlightLeftTickets",
			"beginTime" : flightInfo.beginTime,
			"flight" : flightInfo.flight
		}, function(data, status) {
			if (status == "success") {
				if(data=="时间类型不正确!"){
					alert("时间类型不正确!");
					return;
				}
				//移除确认机票1的表格内容
				$("#check_ticket_table1 tr:not(:first)").remove();
				//得到机票余量
				num=parseInt(data);
				$('#check_ticket_table1').append(
								'<tr><td>'
								+ flightInfo.flight	+ '</td><td>'
								+ new Date(flightInfo.beginTime).toLocaleString() + '</td><td>'
								+ flightInfo.fromWhere + '</td><td>'
								+ flightInfo.toWhere + '</td><td>'
								+ flightInfo.price + '</td><td>'+num+'</td></tr>');
				if(num>0){
					$('#message_div').html("机票余量充足!<br/>请确认机票信息并选择乘客!");
				}else{
					$('#message_div').html("机票余量不足!<br/>请选择其它航班!");
				}
				//触发check_ticket_tab1的点击事件
				$("#check_ticket_tab1").trigger("click");
			} else {
				alert("失败!status=" + status);
			}
			return;
		});
	});
	//从服务器得到当前用户保存的所有乘客 并修改表格数据
	function getIdcards(){
		$.post("IdcardServlet", {
			"method" : "getIdcards"
		}, function(data, status) {
			if (status == "success") {
				//移除 选择乘客表格 的内容
				$("#select_customer_table tr:not(:first)").remove();
				//添加数据到 选择乘客表格
				customers= JSON.parse(data);
				for (i = 0; i < customers.length; i++) {
					var customer = customers[i];
					$('#select_customer_table').append(
							'<tr><td>'
							+ customer.realname	+ '</td><td>'
							+ customer.idcard + '</td><td><input type="checkbox"/></td></tr>');
				}
				//触发select_customer_tab的点击事件
				$("#select_customer_tab").trigger("click");
			} else {
				alert("失败!status=" + status);
			}
			return;
		});
	}
	//点击选择乘客按扭
	$('#selectCustomers_button').click(function(){
		//设置选项卡可点击性
		$("#select_flight_tab").attr('disabled',false);
		$("#check_ticket_tab1").attr('disabled',false);
		$("#select_customer_tab").attr('disabled',false);
		$("#online_pay_tab").attr('disabled',true);
		$("#check_ticket_tab2").attr('disabled',true);
		//执行功能
		getIdcards();
	});
	//占击立即支付按扭
	$('#online_pay_button').click(function(){
		//设置选项卡可点击性
		$("#select_flight_tab").attr('disabled',false);
		$("#check_ticket_tab1").attr('disabled',false);
		$("#select_customer_tab").attr('disabled',false);
		$("#online_pay_tab").attr('disabled',false);
		$("#check_ticket_tab2").attr('disabled',true);
		var count=0;
		selectcustomers=new Array();
		//得到所有勾选的乘客
		$("#select_customer_table").find("input[type='checkbox']").each(function(i){
			if($(this).is(":checked")){
				selectcustomers[count]=customers[i];
				count++;
			}
		});
		//所选乘客数量大于剩余机票数量
		if(count>num){
			alert("所选乘客数量大于剩余机票数量,机票数量不足,请重新选择!");
			return;
		}
		//将数据添加到表格
		$("#online_pay_table tr:not(:first)").remove();
		for (var j = 0; j < selectcustomers.length; j++) {
			$("#online_pay_table").append(
					'<tr><td>'
					+ flightInfo.flight + '</td><td>'
					+ new Date(flightInfo.beginTime).toLocaleString() + '</td><td>'
					+ flightInfo.fromWhere + '</td><td>'
					+ flightInfo.toWhere + '</td><td>'
					+ flightInfo.price + '</td><td>'
					+ selectcustomers[j].realname	+ '</td><td>'
					+ selectcustomers[j].idcard + '</td></tr>');
		}
		$("#total_price").text("	"+count*flightInfo.price+"元");
		$("#charge_form input[name='cost']").val(count*flightInfo.price);
		//触发select_customer_tab的点击事件
		$("#online_pay_tab").trigger("click");
	});
	//在线支付
	function charge(callback){
		var PayCostIdcard=$("#charge_form input[name='idcard']").val();
		var password=$("#charge_form input[name='password']").val();
		var cost=$("#charge_form input[name='cost']").val();
		//提交[在线支付]表单
		$.post("BankServlet",{
			method:"charge",
			idcard:PayCostIdcard,
			password:password,
			flightInfo:JSON.stringify(flightInfo),
			cost:cost,
			selectcustomers:JSON.stringify(selectcustomers),
			fromBank:"XX银行",
			fromAgent:"XX代理商"
		} , function(data, status) {
			if (status == "success") {
				//得到回复数据的json
				var res=JSON.parse(data);
				//得到回复数据的状态信息
				var responseMes=res.responseMes;
				if(responseMes=="付款成功!"){
					alert(responseMes);
					//得到购买成功的机票
					var localTickets=JSON.parse(res.localTickets);
					//设置选项卡可点击性
					$("#select_flight_tab").attr('disabled',false);
					$("#check_ticket_tab1").attr('disabled',false);
					$("#select_customer_tab").attr('disabled',false);
					$("#online_pay_tab").attr('disabled',false);
					$("#check_ticket_tab2").attr('disabled',false);
					//将购买成功的机票信息写入表格
					$("#check_ticket_table2 tr:not(:first)").remove();
					for (i = 0; i < localTickets.length; i++) {
						var ticket = localTickets[i];
						$('#check_ticket_table2').append(
										'<tr><td>'
										+ ticket.flight + '</td><td>'
										+ new Date(ticket.beginTime).toLocaleString() + '</td><td>'
										+ ticket.fromWhere + '</td><td>'
										+ ticket.toWhere + '</td><td>'
										+ ticket.realname + '</td><td>'
										+ ticket.idcard + '</td><td>'
										+ ticket.seatNumber + '</td></tr>');
					}
					//触发select_customer_tab的点击事件
					$("#check_ticket_tab2").trigger("click");
				}else{
					alert(responseMes);
				}
			} else {
				alert("失败! status=" + status);
			}
			callback();
		});	
	}
	//点击确认支付按扭
	$('#charge_button').click(function(){
		$('#charge_button').css("cursor","wait");
		$("#charge_button").attr('disabled',true);
		charge(function(){
			$('#charge_button').css("cursor","pointer");
			$("#charge_button").attr('disabled',false);
		});
	});
	var flightflag=new Array();
	function sortByFlight(a,b){return a.flight>b.flight;}
	function sortByFlightBack(a,b){return a.flight<b.flight;}
	function sortByTime(a,b){return a.beginTime>b.beginTime;}
	function sortByTimeBack(a,b){return a.beginTime<b.beginTime;}
	function sortByFrom(a,b){return a.fromWhere>b.fromWhere;}
	function sortByFromBack(a,b){return a.fromWhere<b.fromWhere;}
	function sortByTo(a,b){return a.toWhere>b.toWhere;}
	function sortByToBack(a,b){return a.toWhere<b.toWhere;}
	function sortByPrice(a,b){return a.price>b.price;}
	function sortByPriceBack(a,b){return a.price<b.price;}
	
	function resetFlightTable(){
		$("#select_flight_table tr:not(:first)").remove();
		for (i = 0; i < flights.length; i++) {
			var flight = flights[i];
			$('#select_flight_table').append(
							'<tr><td>'
							+ flight.flight	+ '</td><td>'
							+ new Date(flight.beginTime).toLocaleString() + '</td><td>'
							+ flight.fromWhere + '</td><td>'
							+ flight.toWhere + '</td><td>'
							+ flight.price + '</td><td><input type="button" class="buttonstyle2" value="购票"></td></tr>');
		}
	}
	//点击行首进行排序
	$('#select_flight_table').on('click','th',function(){
		var type=$(this).text();
		if(type=="航班号"){
			if(!flightflag[0]){
				flightflag[0]=1;
				flights.sort(sortByFlight);
			}else{
				flightflag[0]=0;
				flights.sort(sortByFlightBack);
			}
			resetFlightTable();
		}else if(type=="登机时间"){
			if(!flightflag[1]){
				flightflag[1]=1;
				flights.sort(sortByTime);
			}else{
				flightflag[1]=0;
				flights.sort(sortByTimeBack);
			}
			resetFlightTable();
		}else if(type=="价格"){
			if(!flightflag[2]){
				flightflag[2]=1;
				flights.sort(sortByPrice);
			}else{
				flightflag[2]=0;
				flights.sort(sortByPriceBack);
			}
			resetFlightTable();
		}
	});
});
//$.ajax({
//	  url:url,
//	  type:"POST",
//	  data:{name:name},
//	  timeout:30000,
//	  dataType:"json",
//	  success:function(data){
//	    //var msgJson = eval(data); dataType为json，就不用转了
//	    if (msgJson.status == "0") {
//	        $("#infoRefund").attr("style", 'display:block')
//	    } else {
//	        alert("查询错误，请稍后再试")
//	    }
//	  },
//	  error:function(){}
//	});