<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员充值</title>
<%@include file="/page/include/headScript.jsp"%>
</head>
<script type="text/javascript">

/*页面分类*/
 $(document).ready(function() { setPageType('.men-account', '.men-account-info '); })


	/** 表单数据校验 */
	$(document).ready(function(){
	//充值金额只能输入少于2位的数字。
	var readOnly='';
	$('.chargeM').bind('keydown ',function(){	
	var value=$(this).val();
	if(value.indexOf('.')!==-1){
	var l=value.indexOf('.');
	var str =value.substr(l);
	if(str.length==2){
	readOnly=$(this).val();
	}
	if(str.length>=2){
	$(this).val(readOnly);
	}
	}
	});
	$('.ada-input').bind('focus',function(){
	     $(this).parent().next().css('display','block');
	    });
		$.formValidator.initConfig({formID:"rechargeForm",submitOnce:"true",errorFocus:false,theme:"ArrowSolidBox",mode:"AutoTip",onSuccess:function(){
			//1.获取DIV元素，并且将其显示。
			$("#rechargeFormDiv").hide();
			$("#rechargeFormInfo").show();
			//2.提交表单
			$("#rechargeForm").submit();
		},onError:function(msg){alert(msg)},inIframe:true});
        // 登录密码
		$("#addAmount").formValidator({onShow:"",onFocus:"充值金额须为整数或小数,小数点后不超过2位"}).regexValidator({regExp:"^(([0-9]+\.[0-9]{1,2})|([0-9]*[1-9][0-9]*))$",onError:"充值金额必须为整数或小数,小数点后不超过2位"})
		.ajaxValidator({
		dataType : "json",
		async : true,
		url : "ajaxvalidate_rechargeMemberAmountValidate.action",
		success : function(data){
			if( data.STATE!='FAIL') {
	            return true;}
				return data.MSG;
		},
		onError : "输入金额有误",
		onWait : "正在校验，请稍候..."
		});
		
		$.formValidator.reloadAutoTip();
		$("#Button1").removeAttr("disabled");
	});
</script>
<body>
	<jsp:include page="/page/include/TopMenuMember.jsp"></jsp:include>
	  <div class="bd-container ">
	   <div class="men-recharge">
	    <div class="panel-trade">
	    <div class="panel-head">
                    <div class="menu">
                        <span class="fixed"></span>
                        <h4>
                            会员充值</h4>
                    </div>
                    <a class="link" href="memberReceiveRecharge_listReceiveRechargeRecord.action"><span class="fixed"></span>
                        <h4 class="fl">
                            充值记录查询</h4>
                    </a>
                    <div style="clear: both;">
                    </div>
                </div>
	    <div class="clear">
                </div>
	    
	     <div class="panel-body">
	<div id="rechargeFormInfo" style="display: none;">
	     <div class="tipsBox"> 	
			<div class="tipsTitle">
				<ul>
					<li class="TipsImg SuccTipsImg"></li>
					<li class="tipTxt markGreen"> 充值申请已提交,请在打开的页面中完成充值 </li>
	
				</ul>
			</div>
			<div class="tipsCont"> 
			 您可以<a href="memberReceiveRecharge_rechargePage.action">继续充值</a>  、
		     <a href="memberReceiveRecharge_listReceiveRechargeRecord.action">查看充值记录</a>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	
		<div class="frm-comon" id="rechargeFormDiv">
	<form id="rechargeForm" action="memberReceiveRecharge_checkAddAmount.action" method="post" target="_blank">
				<c:if test="${!empty  PAGE_ERROR_MSG }">
				<p class="clearfix">
				<label> &nbsp; </label>
				<span class="text-warning">${PAGE_ERROR_MSG}</span>
				</p>
				</c:if>
				<p class="clearfix">
					<label >账户名：</label>
					${currentUser.loginName}
				</p>
				<p class="clearfix">
					<label >账户余额(元)：</label>
					<span class="fl text-warning num"><fmt:formatNumber pattern="#0.00" value="${availableBalance}"></fmt:formatNumber></span>
					</p>
				<p class="clearfix">
					<label >充值金额(元)：</label>
					 <input type="text" name="addAmount" id="addAmount" value="${addAmount}" class='chargeM'/>
				</p>
				<p style="height:10px;"></p>
				<p class="clearfix">
					<label> &nbsp; </label>
					 <input type="submit" value="下一步" id="Button1"
						class=" btn btn-primary">
						
					</p>
	</form>
	</div>
	
	<div class="clear"></div>
	</div>
	</div>
	 <div class="recharge-foot">
                <h3>
                    新手帮助：</h3>
                <div class="help-bg png">
                </div>
            </div>
	</div>
	</div>
	 <jsp:include page="../../foot.jsp" />
</body>
</html>

