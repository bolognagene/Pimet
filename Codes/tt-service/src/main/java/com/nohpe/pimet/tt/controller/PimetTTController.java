package com.nohpe.pimet.tt.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nohpe.pimet.tt.service.ITTService;
import com.nohpe.pimet.utils.convert.NEConvert;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.utils.entity.Event;
import com.nohpe.pimet.utils.entity.NE;
import com.nohpe.pimet.utils.entity.TT;
import com.nohpe.pimet.utils.entity.dto.NEDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import com.nohpe.pimet.utils.enums.AlarmState;
import com.nohpe.pimet.utils.enums.EventType;
import com.nohpe.pimet.utils.enums.ProblemStatus;
import com.nohpe.pimet.utils.exception.MessageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class PimetTTController {

    @Autowired
    ITTService ttService;

    @Value("${spring.kafka.tt-resp-topic}")
    private String tt_resp_topic;

    private final KafkaTemplate<String, TT> kafkaTemplate;

    public PimetTTController(KafkaTemplate<String, TT> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${spring.kafka.tt-req-topic}")
    public void consumeTTReq(@Payload TT tt) throws ExecutionException, InterruptedException {

        System.out.println("Receive TT: " + tt.getAlarmId()
                + " from Kafka topic: " + "${spring.kafka.tt-req-topic}");

        //If TT stored to DB successfully, then feedback the TT id
        if(ttService.addTT(tt)) {
            TT storedTT = ttService.queryTTByAlarmId(tt.getAlarmId());

            if(storedTT != null) {
                //Send storedTT to Kafka topic: tt-resp
                ListenableFuture<SendResult<String, TT>> listenableFuture
                        = kafkaTemplate.send(this.tt_resp_topic, storedTT);
                String res = listenableFuture.get().toString();
                System.out.println("Producer send result:" + res + " to Kafka topic "
                        +  this.tt_resp_topic);
            }
        }
    }
}
