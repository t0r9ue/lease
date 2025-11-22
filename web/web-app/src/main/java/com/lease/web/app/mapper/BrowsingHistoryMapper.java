package com.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lease.model.entity.BrowsingHistory;
import com.lease.web.app.vo.history.HistoryItemVo;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/11/13
 */
    
public interface BrowsingHistoryMapper extends BaseMapper<BrowsingHistory> {
	IPage<HistoryItemVo> selectPageByUserId(IPage<HistoryItemVo> historyItemVoPage, Long userId);
}