package com.nohpe.pimet.core.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nohpe.pimet.core.client.IAlarmEnrichmentClient;
import com.nohpe.pimet.core.entity.AMConfigs;
import com.nohpe.pimet.core.service.IAlarmService;
import com.nohpe.pimet.core.utils.PimetCoreUtils;
import com.nohpe.pimet.utils.convert.NEConvert;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.utils.entity.Event;
import com.nohpe.pimet.utils.entity.NE;
import com.nohpe.pimet.utils.entity.dto.NEDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import com.nohpe.pimet.utils.enums.AlarmState;
import com.nohpe.pimet.utils.enums.EventType;
import com.nohpe.pimet.utils.enums.ProblemStatus;
import com.nohpe.pimet.utils.exception.MessageCode;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AlarmReceiveController {

    @Autowired
    IAlarmService alarmService;

    @Autowired
    IAlarmEnrichmentClient alarmEnrichmentClient;

    @Autowired
    AMConfigs amConfigs;

    @Autowired
    PimetCoreUtils pimetCoreUtils;

    @KafkaListener(topics = "${spring.kafka.realtime-alarm-topic}")
    public void consumeRealTimeAlarm(@Payload Event event) {

        if(event.getSource().isEmpty()) {
            throw new IllegalArgumentException();
        }

        System.out.println("Receive Event: " + event.getEventType().toString()
                + " source: " +  event.getSource()
                + " from Kafka topic: " + "${spring.kafka.topic}");

        if(event.getEventType().equals(EventType.CREATE)) {
            if (event.getAlarm() != null) {

                if(!event.getAlarm().getNodeName().isEmpty()) {
                    Result<NEDTO> result = alarmEnrichmentClient.enrichAlarm(event.getSource()+"_node", event.getAlarm().getNodeName());

                    if(result.getStatus() == MessageCode.SUCCESS.getStatus()) {
                        NEDTO nedto = result.getData();
                        NE ne = NEConvert.INSTANCE.convertNE(nedto);
                        event.getAlarm().setCountry(ne.getCountry());
                        event.getAlarm().setProvince(ne.getProvince());
                        event.getAlarm().setCity(ne.getCity());
                        event.getAlarm().setDistrict(ne.getDistrict());
                        event.getAlarm().setNeStatus(ne.getNeStatus());
                        event.getAlarm().setNeLevel(ne.getNeLevel());
                        event.getAlarm().setNeType(ne.getNeType());
                        event.getAlarm().setTechnology(ne.getTechnology());
                        event.getAlarm().setSubTech(ne.getSubTech());
                    }
                }

                String similarAlarmPolicy = amConfigs.getSources()
                        .get(event.getSource()).getSimilarAlarmPolicy();

                String techDomain = amConfigs.getSources()
                        .get(event.getSource()).getTechDomain();

                if(!techDomain.isEmpty()) {
                    event.getAlarm().setTechDomain(techDomain);
                }

                synchronized(this) {
                    Long alarmId = pimetCoreUtils.calculateAlarmId(event.getAlarm().getSource());
                    event.getAlarm().setAlarmId(alarmId);

                    alarmService.addAlarm(event.getAlarm());
                }


            }
        }  else if (event.getEventType().equals(EventType.TERMINATE)
                || event.getEventType().equals(EventType.ACK)) {

            if (event.getChangeAlarmStatus() != null) {
                LambdaUpdateWrapper<Alarm> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.eq(Alarm::getSource, event.getSource());

                //Auto-clear policy
                String autoClearPolicy = amConfigs.getSources()
                        .get(event.getSource()).getAutoClearPolicy();

                lambdaUpdateWrapper = pimetCoreUtils.AutoClearAction(autoClearPolicy,
                        event, lambdaUpdateWrapper);

                Alarm alarm = new Alarm();
                if(event.getEventType().equals(EventType.TERMINATE)) {
                    alarm.setState(AlarmState.valueOf(event.getChangeAlarmStatus().getNewValue()));
                } else {
                    alarm.setProblemStatus(ProblemStatus.valueOf(event.getChangeAlarmStatus().getNewValue()));
                }
                alarm.setLastUpdateTime(event.getChangeAlarmStatus().getUpdate_time());

                alarmService.updateAlarm(alarm, lambdaUpdateWrapper);
            } else {
                throw new IllegalArgumentException();
            }
        }

    }
}
