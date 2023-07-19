package com.nohpe.pimet.fronted.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nohpe.pimet.fronted.entity.PageObject;
import com.nohpe.pimet.utils.entity.Alarm;

public interface IFrontedAlarmService {

    public PageObject<Alarm> getAlarmsPageObjectByConditions(Long pageSize, Long pageIndex, QueryWrapper<Alarm> queryWrapper);
}
