package com.nohpe.pimet.core.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.utils.entity.dto.ActionDTO;

public interface IAlarmService {

    public boolean addAlarm(Alarm alarm);

    public boolean updateAlarm(Alarm alarm, LambdaUpdateWrapper<Alarm> lambdaUpdateWrapper);

    public boolean deleteTerminatedAlarm();

    public Long getLatestAlarmIdBySource(String source);

    public boolean acknowledgeAlarm(ActionDTO actionDTO);

    public boolean terminateAlarm(ActionDTO actionDTO);
}
