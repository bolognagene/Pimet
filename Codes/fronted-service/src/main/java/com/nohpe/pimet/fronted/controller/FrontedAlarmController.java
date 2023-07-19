package com.nohpe.pimet.fronted.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nohpe.pimet.fronted.client.IAlarmActionClient;
import com.nohpe.pimet.fronted.constant.Constant;
import com.nohpe.pimet.fronted.entity.PageObject;
import com.nohpe.pimet.fronted.service.IFrontedAlarmService;
import com.nohpe.pimet.fronted.utils.ParseQueryCondition;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.utils.entity.dto.ActionDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="Endpoint for Fronted Alarm Service")
@RestController
@Slf4j
@RequestMapping(Constant.ALARM_FRONTED_PREFIX)
public class FrontedAlarmController {

    @Autowired
    IFrontedAlarmService frontedAlarmService;

    @Autowired
    IAlarmActionClient alarmActionClient;

    @ApiOperation(value="To get alarms by condition", notes="To get alarms by condition")
    @ApiParam(name = "nodeName",value = "Node Name")
    @RequestMapping(value="/page/{pageSize}/{pageIndex}/", method = RequestMethod.GET)
    public PageObject<Alarm> getAlarms(@PathVariable("pageSize") Long pageSize, @PathVariable("pageIndex") Long pageIndex, @RequestParam List<String> conditions) {

        QueryWrapper<Alarm> queryWrapper = new QueryWrapper<>();

        for(String condition : conditions) {
            queryWrapper = ParseQueryCondition.addQueryCondition(condition, queryWrapper);
        }

        return frontedAlarmService.getAlarmsPageObjectByConditions(pageSize, pageIndex, queryWrapper);
    }

    @ApiOperation(value="To acknowledge alarm", notes="To acknowledge alarm")
    @ApiParam(name = "nodeName",value = "Node Name")
    @RequestMapping(value="/acknowledge", method = RequestMethod.POST)
    public Result acknowledgeAlarm(@RequestBody ActionDTO actionDTO) {

        return alarmActionClient.acknowledgeAlarm(actionDTO);
    }
    @ApiOperation(value="To terminate alarm", notes="To terminate alarm")
    @ApiParam(name = "nodeName",value = "Node Name")
    @RequestMapping(value="/terminate", method = RequestMethod.POST)
    public Result terminateAlarm(@RequestBody ActionDTO actionDTO) {

        return alarmActionClient.terminateAlarm(actionDTO);
    }

}
