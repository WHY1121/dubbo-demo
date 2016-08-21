package wusc.edu.common.web.struts;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import wusc.edu.common.page.PageBean;
import wusc.edu.common.page.PageParam;
import wusc.edu.common.web.dwz.DwzParam;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @描述:  .
 * @作者: WuShuicheng .
 * @创建时间: 2015-1-25,下午7:00:41 .
 * @版本号: V1.0 .
 */
public class Struts2ActionSupport extends ActionSupport {

	private static final long serialVersionUID = 1L;


	public PageBean pageBean;

	public Integer pageNum;

	/**
	 * pageBean.
	 * 
	 * @return the pageBean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * @param pageBean
	 *            the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * 取得当前request
	 * 
	 * @return
	 */
	public HttpServletRequest getHttpRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得会话ID(sessionId).
	 * 
	 * @return sessionId .
	 */
	public String getSessionId() {
		return ServletActionContext.getRequest().getSession().getId();
	}

	/**
	 * 取得当前response
	 * 
	 * @return
	 */
	public HttpServletResponse getHttpResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获取session里面的属性
	 * 
	 * @return
	 */
	public Map<String, Object> getSessionMap() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * 获取request中的application对象.
	 */
	public Map<String, Object> getApplicationMap() {
		return ActionContext.getContext().getApplication();
	}

	/**
	 * 取得当前web应用的根路径
	 * 
	 * @return
	 */
	public String getWebRootPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}


	// ////////////////////////////////////////////////////////////////////////
	// /////////////// 添加结合DWZ-UI框架的Action层可共用方法 //////////////////////
	// /////////////// WuShuicheng 2013-07-08 /////////////////////////////////
	/**
	 * 响应DWZ-UI的Ajax成功请求（statusCode="200"）,<br/>
	 * 跳转到operateSuccess视图并提示“操作成功”.
	 * 
	 * @author WuShuicheng.
	 * @param message
	 *            提示消息.
	 * @return operateSuccess .
	 */
	public String operateSuccess() {
		ajaxDone("200", "操作成功");
		return "operateSuccess";
	}

	/**
	 * 响应DWZ的Ajax成功请求（statusCode="200"）,<br/>
	 * 跳转到operateSuccess视图，提示设置的消息内容.
	 * 
	 * @author WuShuicheng.
	 * @param message
	 *            提示消息.
	 * @return operateSuccess .
	 */
	public String operateSuccess(String message) {
		ajaxDone("200", message);
		return "operateSuccess";
	}

	/**
	 * 响应DWZ的ajax失败请求（statusCode="300"）,跳转到ajaxDone视图.
	 * 
	 * @author WuShuicheng.
	 * @param message
	 *            提示消息.
	 * @return ajaxDone .
	 */
	public String operateError(String message) {
		ajaxDone("300", message);
		return "operateError";
	}

	/**
	 * 响应DWZ的Ajax请求.
	 * 
	 * @author WuShuicheng.
	 * @param statusCode
	 *            statusCode:{ok:200, error:300, timeout:301}.
	 * @param message
	 *            提示消息.
	 */
	private void ajaxDone(String statusCode, String message) {
		DwzParam param = getDwzParam(statusCode, message);
		ActionContext.getContext().getValueStack().push(param);
	}

	/**
	 * 根据request对象，获取页面提交过来的DWZ框架的AjaxDone响应参数值.
	 * 
	 * @author WuShuicheng.
	 * @param statusCode
	 *            状态码.
	 * @param message
	 *            操作结果提示消息.
	 * @return DwzParam :封装好的DwzParam对象 .
	 */
	public DwzParam getDwzParam(String statusCode, String message) {
		// 获取DWZ Ajax响应参数值,并构造成参数对象
		HttpServletRequest req = ServletActionContext.getRequest();
		String navTabId = req.getParameter("navTabId");
		String dialogId = req.getParameter("dialogId");
		String callbackType = req.getParameter("callbackType");
		String forwardUrl = req.getParameter("forwardUrl");
		String rel = req.getParameter("rel");
		return new DwzParam(statusCode, message, navTabId, dialogId, callbackType, forwardUrl, rel, null);
	}

	// ///////////////////////////////////////////////////////////////
	// ///////////////// 结合DWZ-UI的分页参数获取方法 ///////////////////////////
	/**
	 * 获取当前页（DWZ-UI分页查询参数）.<br/>
	 * 如果没有值则默认返回1.
	 * 
	 * @author WuShuicheng.
	 */
	private int getPageNum() {
		// 当前页数
		String pageNumStr = getHttpRequest().getParameter("pageNum");
		int pageNum = 1;
		if (StringUtils.isNotBlank(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		return pageNum;
	}

	/**
	 * 获取每页记录数（DWZ-UI分页查询参数）.<br/>
	 * 如果没有值则默认返回15.
	 * 
	 * @author WuShuicheng.
	 */
	private int getNumPerPage() {
		String numPerPageStr = getHttpRequest().getParameter("numPerPage");
		int numPerPage = 15;
		if (StringUtils.isNotBlank(numPerPageStr)) {
			numPerPage = Integer.parseInt(numPerPageStr);
		}
		return numPerPage;
	}

	/**
	 * 获取分页参数，包含当前页、每页记录数.
	 * 
	 * @return PageParam .
	 */
	public PageParam getPageParam() {
		return new PageParam(getPageNum(), getNumPerPage());
	}

	// //////////////////////// 存值方法 /////////////////////////////////
	/**
	 * 将数据放入Struts2上下文的值栈中.<br/>
	 * ActionContext.getContext().getValueStack().push(obj);
	 */
	public void pushData(Object obj) {
		ActionContext.getContext().getValueStack().push(obj);
	}

	/**
	 * 将数据放入Struts2上下文中.<br/>
	 * ActionContext.getContext().put(key, value);
	 */
	public void putData(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	// ///////////////////////getHttpRequest()方法扩展//////////////////////////
	/**
	 * 根据参数名从HttpRequest中获取Double类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return DoubleValue or null .
	 */
	public Double getDouble(String key) {
		String value = getHttpRequest().getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取Integer类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return IntegerValue or null .
	 */
	public Integer getInteger(String key) {
		String value = getHttpRequest().getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取Long类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return LongValue or null .
	 */
	public Long getLong(String key) {
		String value = getHttpRequest().getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Long.parseLong(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return String or null .
	 */
	public String getString(String key) {
		return getHttpRequest().getParameter(key);
	}

	/**
	 * 与DWZ框架结合的表单属性长度校验方法.
	 * 
	 * @param propertyName
	 *            要校验的属性中文名称，如“登录名”.
	 * @param property
	 *            要校验的属性值，如“gzzyzz”.
	 * @param isRequire
	 *            是否必填:true or false.
	 * @param minLength
	 *            最少长度:大于或等于0，如果不限制则可请设为0.
	 * @param maxLength
	 *            最大长度:对应数据库字段的最大长度，如不限制则可设为0.
	 * @return 校验结果消息，校验通过则返回空字符串 .
	 */
	protected String lengthValidate(String propertyName, String property, boolean isRequire, int minLength, int maxLength) {
		
		int propertyLenght = strLengthCn(property);
		if (isRequire && propertyLenght == 0) {
			return propertyName + "不能为空，"; // 校验不能为空
		} else if (isRequire && minLength != 0 && propertyLenght < minLength) {
			return propertyName + "不能少于" + minLength + "个字符，"; // 必填情况下校验最少长度
		} else if (maxLength != 0 && propertyLenght > maxLength) {
			return propertyName + "不能多于" + maxLength + "个字符，"; // 校验最大长度
		} else {
			return ""; // 校验通过则返回空字符串 .
		}
	}
	
	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为3位 ，当字符串为空时返回0.
	 * 
	 * @param str 字符串 .
	 * @return 字符串的长度 .
	 */
	private int strLengthCn(String str)
	{
		if (StringUtils.isBlank(str)){
			return 0;
		}
		int valueLength = 0;
		final String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int num = 0; num < str.length(); num++){
			/* 获取一个字符 */
			final String temp = str.substring(num, num + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)){
				/* 中文字符长度为3 */
				valueLength += 3;
			} else{
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}


}
