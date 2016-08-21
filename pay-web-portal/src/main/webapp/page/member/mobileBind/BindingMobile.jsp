<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>绑定手机</title>
	<%@include file="/page/include/headScript.jsp"%>
	<!--  <link rel="stylesheet" href="<%=path%>/statics/themes/default/css/style.css" />-->
</head>
<script type="text/javascript" src="<%=path%>/statics/cfca/js/SignCertManager.js"></script>
<script type="text/javascript">
/*页面分类*/
$(document).ready(function() { setPageType('.men-security', '.men-security-info '); })

function submitForm(){
  var text = "";
  document.getElementById("errorMsg").innerHTML=text;
  var textWarning= document.getElementById("phoneCodeMsg");
  if (textWarning!=null){
    textWarning .innerHTML=text
  }
  subForm();
}

$(function(){
	validatorFrom();
	//发送短信验证码
	$('#buttonid').bind('click',function(){
		var bindMobileNo = document.getElementById("bindingPhone").value;//绑定手机
	  var tempBindMobile = document.getElementById("tempBindMobileNo").value;//原来手机
	    if(bindMobileNo != tempBindMobile){
	    	  $("#messageMobile").attr('value',bindMobileNo);
	    	    // 手机号，登录名，用户类型，是否绑定，短信模板类型，发送短信ID，显示信息DIV的ID
	    	      sendSms(bindMobileNo, '${currentUser.loginName}', 'member', 'unbinding', '', 'buttonid','phoneCodeMsg');
	    }
	    else{
	        alert("不能与原绑定手机一致");
	      }
	});
})

//------------------------表单验证------------------------
function validatorFrom(){
	$.formValidator.initConfig({formID:"form1",submitOnce:"true",errorFocus:false,errorFocus:false,theme:"ArrowSolidBox",mode:"AutoTip",onError:function(msg){
		//alert(msg)
		},inIframe:true});
	$("#bindingPhone").formValidator({onShow:"",onFocus:"必填项，请输入11位的手机号码"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"手机号码格式不正确"}).compareValidator({desID:"tempBindMobileNo",operateor:"!=",onError:"不能与原绑定手机一致"});
	 // 手机验证码
	$("#phoneCode").formValidator({onShow:"",onFocus:"必填项，请输入6位的手机验证码"}).inputValidator({min:6,max:6,onError:"请输入6位的手机验证码"})
	.regexValidator({regExp:"num",dataType:"enum",onError:"手机验证码必须为数字"})
	.ajaxValidator({
		dataType : "json",
		async : true,
		url : "ajaxvalidate_phoneCodeValidate.action",
		success : function(data){
			if( data.STATE!='FAIL') {
	            return true;}
				return data.MSG;
		},
		onError : "校验未通过，请重新输入",
		onWait : "正在校验，请稍候..."
	});
	$.formValidator.reloadAutoTip();
}
//--------------------发送短信----------------------------end

function messageOut() {
  var phoneCode = document.getElementById('phoneCode').value;//用户名
     if(phoneCode != ""){
       $("#phoneCodeMsg").html("");
     }
}
</script>
<body>
<div id="FakeCryptoAgent"></div>
	<jsp:include page="/page/include/TopMenuMember.jsp"></jsp:include>
	<div class="container">
		<div class="bd-container">
			<form id="form1" action="membermobilebind_bindingMobile.action"
				method="post">
				<div class="frm-comon mtop10">
					<div class="headline">
						<div class="title">绑定手机</div>
					</div>
	        <p class="clearfix">
	          <label> &nbsp; </label>
						<input type="hidden" value="${currentUser.bindMobileNo}"
							id="tempBindMobileNo" />
					  <input type="hidden" name="messageMobile" id="messageMobile" value="${messageMobile}"/>
					</p>
					<p class="clearfix">
						<label>绑定手机号：</label> <input type="text" class="ada-input"
							value="${bindingPhone}" id="bindingPhone" name="bindingPhone"
							maxlength="11">
					</p>
					<p class="clearfix">
						<label>短信验证码：</label>
						<input style="width:150px;" type="text" class="ada-input fl" value="${phoneCode}" name="phoneCode" id="phoneCode" maxlength="6" onblur="messageOut()" />
						<input type="button" class="btn btn-secondary" id="buttonid"
								value="获取验证码" style="margin-left: 5px;"/>
					</p>
					<jsp:include page="/page/include/memberTradePwd.jsp"></jsp:include>
         <c:if test="${!empty  PAGE_ERROR_MSG }">
					<p class="clearfix">
						<label> &nbsp; </label>
						<span id="phoneCodeMsg" class='clearfix markRed'>${PAGE_ERROR_MSG}</span>
	     	  </p>
	     	  </c:if>
					<p class="clearfix">
						<label> &nbsp; </label>  <input type="button" value="下一步"
								class="btn btn-primary" onclick="submitForm();return false;">
					</p>
					<div class="clear"></div>
					<div class="ht"></div>
				</div>
			</form>
			<div class="clear"></div>
		</div>
	</div>
	<jsp:include page="../../foot.jsp" />
</body>
</html>
