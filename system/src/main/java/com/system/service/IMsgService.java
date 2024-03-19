package com.system.service;

import com.api.domain.dto.MsgDTO;
import com.api.domain.po.Msg;
import com.api.domain.po.Store;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IMsgService extends IService<Msg> {

    void checkAndNotifyUnreadMessages();

    Page<Msg> find(MsgDTO msgDTO, Integer pageNum, Integer pageSize);

}
