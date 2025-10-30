package com.duck.integration.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.duck.integration.mapper.HarborCleanMapper;
import com.duck.integration.mapper.NirvanaCleanMapper;
import com.duck.integration.model.DataCleanRequest;
import com.duck.integration.service.DataCleanService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DataCleanServiceImpl implements DataCleanService {

    @Resource
    private NirvanaCleanMapper nirvanaCleanMapper;
    @Resource
    private HarborCleanMapper harborCleanMapper;

    @Override
    public void clean(DataCleanRequest request) {
        this.cleanHarbor(request.getUserGid(), request.getApiChannel());
        this.cleanNirvana(request.getUserGid(), request.getChannelId());
    }

    @Transactional
    public void cleanNirvana(String userId, Integer channelId) {

        nirvanaCleanMapper.deleteUser(userId, channelId);
        nirvanaCleanMapper.deleteLoan(userId, channelId);
        nirvanaCleanMapper.deleteApply(userId, channelId);

        log.info("[标渠清理完成] userId={}, channelId={}", userId, channelId);
    }

    @Transactional
    public void cleanHarbor(String userId, Integer apiChannel) {

        // 先删不分渠道的
        harborCleanMapper.deleteSubLoan(userId);
        harborCleanMapper.deleteCreditItem(userId);

        // 再删分渠道的
        harborCleanMapper.deleteContractLog(userId, apiChannel);
        harborCleanMapper.deleteUserChannelExt(userId, apiChannel);
        harborCleanMapper.deleteBankCard(userId, apiChannel);
        harborCleanMapper.deleteRecordLoan(userId, apiChannel);
        harborCleanMapper.deleteUserCredit(userId, apiChannel);

        log.info("[Harbor清理完成] userId={}, apiChannel={}", userId, apiChannel);
    }
}
