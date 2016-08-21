package wusc.edu.pay.facade.notify.entity;

import java.util.Date;

import wusc.edu.pay.common.entity.BaseEntity;


/**
 * 
 * @描述: 通知记录日志实体.
 * @作者: WuShuicheng.
 * @创建: 2014-7-18,下午4:58:00
 * @版本: V1.0
 *
 */
public class NotifyRecordLog extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 459406550725396000L;

    /** 通知记录ID **/
    private Long notifyId;

    /** 请求信息 **/
    private String request;

    /** 返回信息 **/
    private String response;

    /** 商户编号 **/
    private String merchantNo;

    /** 商户订单号 **/
    private String merchantOrderNo;

    /** HTTP状态 **/
    private Integer httpStatus;


    public NotifyRecordLog() {
		super();
	}

	public NotifyRecordLog(Date createTime, Long notifyId, String request, String response, String merchantNo, String merchantOrderNo, Integer httpStatus) {
		super();
		this.createTime = createTime;
		this.notifyId = notifyId;
		this.request = request;
		this.response = response;
		this.merchantNo = merchantNo;
		this.merchantOrderNo = merchantOrderNo;
		this.httpStatus = httpStatus;
	}

	

    /** 通知记录ID **/
    public Long getNotifyId() {
        return notifyId;
    }

    /** 通知记录ID **/
    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    /** 请求信息 **/
    public String getRequest() {
        return request;
    }

    /** 请求信息 **/
    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }

    /** 返回信息 **/
    public String getResponse() {
        return response;
    }

    /** 返回信息 **/
    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }

    /** 商户编号 **/
    public String getMerchantNo() {
        return merchantNo;
    }

    /** 商户编号 **/
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /** 商户订单号 **/
    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    /** 商户订单号 **/
    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
    }

    /** HTTP状态 **/
    public Integer getHttpStatus() {
        return httpStatus;
    }

    /** HTTP状态 **/
    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
