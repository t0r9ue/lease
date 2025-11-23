package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.common.login.LoginUserHolder;
import com.lease.model.entity.BrowsingHistory;
import com.lease.web.app.mapper.BrowsingHistoryMapper;
import com.lease.web.app.service.BrowsingHistoryService;
import com.lease.web.app.vo.history.HistoryItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liubo
 * @description 针对表【browsing_history(浏览历史)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@RequiredArgsConstructor
public class BrowsingHistoryServiceImpl extends ServiceImpl<BrowsingHistoryMapper, BrowsingHistory>
        implements BrowsingHistoryService {

	private final BrowsingHistoryMapper browsingHistoryMapper;

	@Override
	public IPage<HistoryItemVo> findBrowsingHistoryByPage(IPage<HistoryItemVo> historyItemVoPage) {
		Long userId = LoginUserHolder.getLoginUser().getUserId();
		return browsingHistoryMapper.selectPageByUserId(historyItemVoPage, userId);
	}

	@Override
	@Async("browseHistoryExecutor")
	public void saveHistoryByRoomId(Long id, Long userId) {
		// 是否存在该房间历史
		LambdaQueryWrapper<BrowsingHistory> queryWrapper = new LambdaQueryWrapper<BrowsingHistory>()
				.eq(BrowsingHistory::getRoomId, id)
				.eq(BrowsingHistory::getUserId, userId);
		BrowsingHistory browsingHistory = super.getOne(queryWrapper);
		if(browsingHistory != null) {
			// 存在则修改浏览时间
			browsingHistory.setBrowseTime(new Date());
			super.updateById(browsingHistory);
		} else {
			browsingHistory = new BrowsingHistory();
			browsingHistory.setUserId(userId);
			browsingHistory.setRoomId(id);
			browsingHistory.setBrowseTime(new Date());
			super.save(browsingHistory);
		}
	}
}