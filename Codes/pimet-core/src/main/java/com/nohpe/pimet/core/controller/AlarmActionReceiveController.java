package com.nohpe.pimet.core.controller;

import com.nohpe.pimet.core.constant.Constant;
import com.nohpe.pimet.core.service.IAlarmService;
import com.nohpe.pimet.utils.entity.dto.ActionDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="Endpoint for Alarm Action Receive")
@RestController
@RequestMapping(Constant.ALARM_ACTION_PREFIX)
public class AlarmActionReceiveController {

    @Autowired
    IAlarmService alarmService;

    @ApiOperation(value="To Acknowledge Alarm", notes="To acknowledge alarm")
    @ApiParam(name = "actionDTO",value = "actionDTO")
    @RequestMapping(value="/acknowledge", method = RequestMethod.POST)
    public Result acknowledgeAlarm(@RequestBody ActionDTO actionDTO) {

        if(actionDTO.getSource().isEmpty()
            || actionDTO.getEntityId() == null) {
            return Result.error("Alarm Id or Source is null");
        }

        boolean ret = alarmService.acknowledgeAlarm(actionDTO);

        if(ret) {
            return Result.success();
        } else {
            return Result.error("Acknowledge alarm " + actionDTO.getSource() + "###"
                + actionDTO.getEntityId() + " failed");
        }
    }


    @ApiOperation(value="To Terminate Alarm", notes="To terminate alarm")
    @ApiParam(name = "actionDTO",value = "actionDTO")
    @RequestMapping(value="/terminate", method = RequestMethod.POST)
    public Result terminateAlarm(@RequestBody ActionDTO actionDTO) {

        if(actionDTO.getSource().isEmpty()
                || actionDTO.getEntityId() == null) {
            return Result.error("Alarm Id or Source is null");
        }

        boolean ret = alarmService.terminateAlarm(actionDTO);

        if(ret) {
            return Result.success();
        } else {
            return Result.error("Terminate alarm " + actionDTO.getSource() + "###"
                    + actionDTO.getEntityId() + " failed");
        }
    }
}
