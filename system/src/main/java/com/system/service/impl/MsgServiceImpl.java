package com.system.service.impl;


import com.api.domain.dto.LedgerDTO;
import com.api.domain.dto.MsgDTO;
import com.api.domain.dto.ProductDTO;
import com.api.domain.po.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.exception.BadRequestException;
import com.system.mapper.MsgMapper;
import com.system.mapper.StoreMapper;
import com.system.service.IMsgService;
import com.system.service.IStoreService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements IMsgService {

    public Page<Msg> find(MsgDTO msgDTO, Integer pageNum, Integer pageSize) {
        Page<Msg> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Msg> qw = new QueryWrapper<>();

        if (msgDTO.getBeforeTime() != null){
            qw.ge("outbound_time", msgDTO.getBeforeTime());
        }
        if (msgDTO.getAfterTime() != null){
            qw.le("outbound_time", msgDTO.getAfterTime());
        }
        return page(page, qw);
    }

    public void checkAndNotifyUnreadMessages() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));

        ZonedDateTime threeHoursAgo = zonedDateTime.minusHours(3);

        LocalDateTime localDateTime = threeHoursAgo.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        QueryWrapper<Msg> qw = new QueryWrapper<>();
        qw.eq("status", 0);
        qw.le("outbound_time", formattedDateTime);
        long unreadMessages = count(qw);
        System.out.println("Admin用户，您有" + unreadMessages + "条信息超过3个小时未读");
    }

}
