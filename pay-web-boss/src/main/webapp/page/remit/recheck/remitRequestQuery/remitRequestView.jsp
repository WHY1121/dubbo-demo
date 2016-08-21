<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/inc/taglib.jsp"%>
<div class="pageContent">
	<form id="form" method="post"
		action="remitChannel_remitChannelUpdate.action"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="navTabId" value="remitProcessList">
		<input type="hidden" name="callbackType" value="closeCurrent">
		<input type="hidden" name="forwardUrl" value=""> <input
			type="hidden" name="id" value="${id}" />
		<div class="pageFormContent" layoutH="60">
			<p>
				<label>打款请求号：</label> <input type='text' id='requestNo'
					value='${requestNo}' size='30' name='requestNo' readonly="readonly"/>
			</p>
			<p>
				<label>业务发起方：</label>
				<c:forEach items="${TradeSourcesEnums }" var="v"> 
				<c:if test="${v.value== tradeInitiator}">
				<input type='text' id='tradeInitiator'
					value='${v.desc}' size='30' name='tradeInitiator' readonly="readonly"/>
				</c:if>
				</c:forEach>
			</p>
			<p>
				<label>帐户类型：</label> 
				<c:forEach items="${AccountTypeEnums}" var="v">
				<c:if test="${v.value==accountType }">
				<input type='text' id='accountType'
					value='${v.desc}' size='30' name='accountType' readonly="readonly"/>
					</c:if>
				</c:forEach>
			</p>
			<%-- <p>
				<label>是否加急：</label> 
				<c:forEach items="${PublicStatusEnums }" var="v">
				<c:if test="${v.value==isUrgent }">
				<input type='text' id='isUrgent' value='${v.desc}' size='30' name='isUrgent' />
				</c:if></c:forEach>
			</p> --%>
			<p>
				<label>开户名：</label> <input type='text' id='accountName'
					value='${accountName}' size='30' name='accountName' readonly="readonly"/>
			</p>
			<p>
				<label>收款帐户编号：</label> <input type='text' id='accountNo'
					value='${accountNo}' size='30' name='accountNo' readonly="readonly" />
			</p>
			<p>
				<label>银行行号：</label> <input type='text' id='bankChannelNo'
					value='${bankChannelNo}' size='30' name='bankChannelNo' readonly="readonly"/>
			</p>
			<p>
				<label>开户银行名称：</label> <input type='text' id='bankName'
					value='${bankName}' size='30' name='bankName' readonly="readonly"/>
			</p>
			<p>
				<label>省份：</label> <input type='text' id='province'
					value='${province}' size='30' name='province' readonly="readonly"/>
			</p>
			<p>
				<label>城市：</label> <input type='text' id='city' value='${city}'
					size='30' name='city' readonly="readonly"/>
			</p>
			<%-- <p>
				<label>币种：</label> <input type='text' id='currency'
					value='${currency}' size='30' name='currency' readonly="readonly"/>
			</p>
			<p>
				<label>结果通知地址：</label> <input type='text' id='notifyAddress'
					value='${notifyAddress}' size='30' name='notifyAddress' readonly="readonly"/>
			</p> --%>
			<p>
				<label>状态：</label> 
				<c:forEach items="${RemitProcessStatusEnums }" var="v">
				<c:if test="${v.value==status }">
				<input type='text' id='status' value='${v.desc}' size='30' name='status' readonly="readonly" />
				</c:if>
					</c:forEach>
			</p>
			<p>
				<label>请求金额：</label> <input type='text' id='amount'
					value='${amount}' size='30' name='amount' readonly="readonly" />
			</p>
			<p>
				<label>是否自动处理：</label>
				<c:forEach items="${PublicStatusEnums }" var="v">
				<c:if test="${v.value==isAutoProcess }">
				<input type='text' id='isAutoProcess'
					value='${v.desc}' size='30' name='isAutoProcess' readonly="readonly"/>
				</c:if>
				</c:forEach>
			</p>
			<%-- <p>
				<label>用户编号：</label> <input type='text' id='userNo'
					value='${userNo}' size='30' name='userNo' readonly="readonly"/>
			</p> --%>
			<p>
				<label>业务类型：</label> 
				<c:forEach items="${TradeTypeEnums }" var="v">
				<c:if test="${v.value==businessType }">
				<input type='text' id='businessType'
					value='${v.desc}' size='30' name='businessType' readonly="readonly"/>
				</c:if></c:forEach>
			</p>
			<p>
				<label>创建人：</label> <input type='text' id='creator'
					value='${creator}' size='30' name='creator' readonly="readonly"/>
			</p>
			<p>
				<label>创建时间：</label>
				<fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</p>
			<p>
				<label>审核人：</label> <input type='text' id='confirm'
					value='${confirm}' size='30' name='confirm' readonly="readonly"/>
			</p>
			<p>
				<label>审核时间：</label>
				<fmt:formatDate value="${confirmDate }" pattern="yyyy-MM-dd HH:mm:ss" />
			</p>
			<%-- <p>
				<label>撤销原因：</label> <input type='text' id='cancelReason'
					value='${cancelReason}' size='30' name='cancelReason' readonly="readonly"/>
			</p>
			<p>
				<label>银行备注信息：</label> 
				<textarea rows="2" cols="30" readonly="readonly">${bankRemark}</textarea>
			</p> --%>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
