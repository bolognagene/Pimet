package com.nohpe.pimet.core.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nohpe.pimet.core.service.IAlarmService;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.utils.entity.TT;
import com.nohpe.pimet.utils.enums.AlarmState;
import com.nohpe.pimet.utils.enums.ProblemStatus;
import com.nohpe.pimet.utils.enums.TTStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class TTResponseController {

    @Autowired
    IAlarmService alarmService;

    @KafkaListener(topics = "${spring.kafka.tt-resp-topic}")
    public void consumeTTResp(@Payload TT tt) throws ExecutionException, InterruptedException {

        System.out.println("Receive TT: " + tt.getAlarmId()
                + " from Kafka topic: " + "${spring.kafka.tt-req-topic}");

        // Update Alarm information according to TT status
        if(tt.getTtStatus() == TTStatus.ACKNOWLEDGED
            || tt.getTtStatus() == TTStatus.CLOSED) {
            // If acknowledged, firstly set Alarm's ProblemStatus as HANDLED, and
            // then update the TT id in Alarm's 'Handled by' field
            String ttAlarmId = tt.getAlarmId();
            if (ttAlarmId.contains("###")) {
                String alarmSource = Arrays.stream(ttAlarmId.split("###")).toList().get(0);
                String alarmId = Arrays.stream(ttAlarmId.split("###")).toList().get(1);

                LambdaUpdateWrapper<Alarm> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.eq(Alarm::getSource, alarmSource);
                lambdaUpdateWrapper.eq(Alarm::getId, alarmId);

                Alarm alarm = new Alarm();
                if (tt.getTtStatus() == TTStatus.ACKNOWLEDGED) {
                    // If ACK, then:
                    // 1. Set Alarm's Handled by with TT id
                    // 2. Set Alarm's State as Acknowledged
                    // 3. Set Alarm's Problem Status as Handled
                    alarm.setHandledBy("TT###" + tt.getId());
                    alarm.setProblemStatus(ProblemStatus.HANDLED);
                    alarm.setState(AlarmState.ACKNOWLEDGED);
                } else {
                    // If Closed, then
                    // 1. Set Alarm's Problem Status as Closed
                    alarm.setProblemStatus(ProblemStatus.CLOSED);
                }
                alarm.setLastUpdateTime(tt.getUpdate_time());


                alarmService.updateAlarm(alarm, lambdaUpdateWrapper);
            } else {
                throw new IllegalArgumentException();
            }


        }

    }
}
