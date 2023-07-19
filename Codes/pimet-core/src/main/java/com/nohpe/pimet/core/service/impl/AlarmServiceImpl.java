package com.nohpe.pimet.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nohpe.pimet.core.service.IAlarmService;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.core.mapper.AlarmMapper;
import com.nohpe.pimet.utils.entity.dto.ActionDTO;
import com.nohpe.pimet.utils.enums.AlarmState;
import com.nohpe.pimet.utils.enums.ProblemStatus;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, Alarm> implements IAlarmService {

    public boolean addAlarm(Alarm alarm) {
        return this.save(alarm);
    }

    public boolean updateAlarm(Alarm alarm, LambdaUpdateWrapper<Alarm> lambdaUpdateWrapper) {
        return this.update(alarm, lambdaUpdateWrapper);
    }

    public boolean deleteTerminatedAlarm() {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Alarm::getState, "TERMINATED");

        return this.remove(lambdaQueryWrapper);
    }

    public Long getLatestAlarmIdBySource(String source) {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Alarm::getSource, source);
        lambdaQueryWrapper.orderByDesc(Alarm::getAlarmId);

        Alarm alarm = this.baseMapper.selectOne(lambdaQueryWrapper);

        if(alarm != null && alarm.getAlarmId() != null) {
            return alarm.getAlarmId();
        } else {
            return null;
        }
    }

    public boolean acknowledgeAlarm(ActionDTO actionDTO){

        LambdaUpdateWrapper<Alarm> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        lambdaUpdateWrapper.eq(Alarm::getAlarmId, actionDTO.getEntityId())
                .eq(Alarm::getSource, actionDTO.getSource());

        Alarm alarm = new Alarm();
        alarm.setState(AlarmState.ACKNOWLEDGED);
        alarm.setProblemStatus(ProblemStatus.HANDLED);
        alarm.setAcknowledgedUser(actionDTO.getActionUser());
        alarm.setAcknowledgedTime(actionDTO.getActionTimeStamp());

        return updateAlarm(alarm, lambdaUpdateWrapper);
    }


    public boolean terminateAlarm(ActionDTO actionDTO){

        LambdaUpdateWrapper<Alarm> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        lambdaUpdateWrapper.eq(Alarm::getAlarmId, actionDTO.getEntityId())
                .eq(Alarm::getSource, actionDTO.getSource());

        Alarm alarm = new Alarm();
        alarm.setState(AlarmState.TERMINATED);
        alarm.setTerminationUser(actionDTO.getActionUser());
        alarm.setTerminationTime(actionDTO.getActionTimeStamp());

        return updateAlarm(alarm, lambdaUpdateWrapper);
    }

}
