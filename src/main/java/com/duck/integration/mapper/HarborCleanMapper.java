package com.duck.integration.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
@DS("harbor")
public interface HarborCleanMapper {
    // 不分渠道的
    @Delete("""
        DELETE FROM partner_record_sub_loan
        WHERE user_gid = #{userId}
        """)
    int deleteSubLoan(@Param("userId") String userId);

    @Delete("""
        DELETE FROM partner_user_credit_item
        WHERE user_gid = #{userId}
        """)
    int deleteCreditItem(@Param("userId") String userId);

    // 分渠道的
    @Delete("""
        DELETE FROM partner_log_contract_status
        WHERE user_gid = #{userId} AND api_channel = #{apiChannel}
        """)
    int deleteContractLog(@Param("userId") String userId,
                          @Param("apiChannel") Integer apiChannel);

    @Delete("""
        DELETE FROM user_account_channel_extension_info
        WHERE user_gid = #{userId} AND channel_id = #{apiChannel}
        """)
    int deleteUserChannelExt(@Param("userId") String userId,
                             @Param("apiChannel") Integer apiChannel);

    @Delete("""
        DELETE FROM partner_user_bank_card
        WHERE user_gid = #{userId} AND api_channel = #{apiChannel}
        """)
    int deleteBankCard(@Param("userId") String userId,
                       @Param("apiChannel") Integer apiChannel);

    @Delete("""
        DELETE FROM partner_record_loan
        WHERE user_gid = #{userId} AND api_channel = #{apiChannel}
        """)
    int deleteRecordLoan(@Param("userId") String userId,
                         @Param("apiChannel") Integer apiChannel);

    @Delete("""
        DELETE FROM partner_user_credit
        WHERE user_gid = #{userId} AND api_channel = #{apiChannel}
        """)
    int deleteUserCredit(@Param("userId") String userId,
                         @Param("apiChannel") Integer apiChannel);
}
