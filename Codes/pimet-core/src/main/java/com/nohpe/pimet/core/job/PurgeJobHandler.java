package com.nohpe.pimet.core.job;

import com.nohpe.pimet.core.service.IAlarmService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PurgeJobHandler {

    private static Logger logger = LoggerFactory.getLogger(PurgeJobHandler.class);
    @Autowired
    IAlarmService alarmService;

    @XxlJob("PurgeJobHandler")
    public ReturnT<String> purgeTerminatedAlarm() throws Exception {

        logger.info(new Date() + "：Start to purge terminated alarm.");

        alarmService.deleteTerminatedAlarm();

        return ReturnT.SUCCESS;

    }

}
