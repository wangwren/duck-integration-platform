package com.duck.integration.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
@DS("nirvana")
public interface NirvanaCleanMapper {

    @Delete("""
        DELETE FROM t_loan_market_loan
        WHERE user_id = #{userId} AND channel_id = #{channelId}
        """)
    int deleteLoan(@Param("userId") String userId,
                   @Param("channelId") Integer channelId);

    @Delete("""
        DELETE FROM t_loan_market_apply
        WHERE user_id = #{userId} AND channel_id = #{channelId}
        """)
    int deleteApply(@Param("userId") String userId,
                    @Param("channelId") Integer channelId);

    @Delete("""
        DELETE FROM t_loan_market_user
        WHERE user_id = #{userId} AND channel_id = #{channelId}
        """)
    int deleteUser(@Param("userId") String userId,
                   @Param("channelId") Integer channelId);
}
