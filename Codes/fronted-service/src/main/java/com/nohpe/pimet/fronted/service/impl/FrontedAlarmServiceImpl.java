package com.nohpe.pimet.fronted.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nohpe.pimet.fronted.entity.PageObject;
import com.nohpe.pimet.fronted.mapper.FrontedAlarmMapper;
import com.nohpe.pimet.fronted.service.IFrontedAlarmService;
import com.nohpe.pimet.utils.entity.Alarm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FrontedAlarmServiceImpl extends ServiceImpl<FrontedAlarmMapper, Alarm> implements IFrontedAlarmService {


    public PageObject<Alarm> getAlarmsPageObjectByConditions(Long pageSize,
            Long pageIndex, QueryWrapper<Alarm> queryWrapper) {

        if(queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }

        IPage<Alarm> page = new Page<>(pageIndex, pageSize);
        IPage<Alarm> pagedResult = baseMapper.selectPage(page, queryWrapper);

        PageObject<Alarm> pagedObject = new PageObject<Alarm>();
        pagedObject.buildPage(pagedResult.getRecords(), pagedResult.getTotal(), pagedResult.getCurrent(), pagedResult.getSize());

        return pagedObject;

    }

}
