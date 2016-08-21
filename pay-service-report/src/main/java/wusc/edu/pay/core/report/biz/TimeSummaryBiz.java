package wusc.edu.pay.core.report.biz;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wusc.edu.pay.common.page.PageBean;
import wusc.edu.pay.common.page.PageParam;
import wusc.edu.pay.core.report.dao.TimeSummaryDao;


@Component("timeSummaryBiz")
public class TimeSummaryBiz {

	@Autowired
	private TimeSummaryDao timeSummaryDao;

	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return timeSummaryDao.listPage(pageParam, paramMap);
	}

	/**
	 * 创建时间段统计数据
	 * @param dealDate
	 * @return
	 */
	public int createAreaSummary(Date dealDate){
		return timeSummaryDao.createTimeSummary(dealDate);
	}
}
