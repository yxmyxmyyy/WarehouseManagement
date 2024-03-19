package com.system.mapper;

import com.api.domain.po.Ledger;
import com.api.domain.po.Msg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MsgMapper extends BaseMapper<Msg> {
}
