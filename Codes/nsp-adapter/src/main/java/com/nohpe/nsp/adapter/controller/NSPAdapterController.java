package com.nohpe.nsp.adapter.controller;

import com.nohpe.nsp.adapter.constant.Constant;
import com.nohpe.nsp.adapter.controller.dto.req.ChangeNSPAlarmReqDTO;
import com.nohpe.nsp.adapter.controller.dto.req.CreateNSPAlarmReqDTO;
import com.nohpe.nsp.adapter.convert.AlarmConvert;
import com.nohpe.nsp.adapter.convert.ChangeAlarmStatusConvert;
import com.nohpe.pimet.utils.entity.Event;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.utils.entity.ChangeAlarmStatus;
import com.nohpe.pimet.utils.entity.vo.Result;
import com.nohpe.pimet.utils.enums.EventType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Api(value="Endpoint for Nsp-adapter")
@RestController
@RequestMapping("/nsp")
public class NSPAdapterController {

    @Autowired
    private Environment environment;

    @Value("${spring.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public NSPAdapterController(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @ApiOperation(value="To Create new Alarm", notes="To get new alarm attributes, then send to kafka topic")
    @ApiParam(name = "createAlarmReqDTO",value = "New Alarm attributes")
    @PostMapping("/new")
    public Result<Boolean> sendCreate(@RequestBody CreateNSPAlarmReqDTO createNSPAlarmReqDTO) throws ExecutionException, InterruptedException {

        Alarm alarm = AlarmConvert.INSTANCE.convertAlarm(createNSPAlarmReqDTO);
        alarm.setSource(Constant.SOURCE_NAME);

        // Insert NSP specific logic //
        String mo = Constant.NSP_GLOBAL_CLASS + " " + createNSPAlarmReqDTO.getAgentEntity()
                + " NodeName \"" + createNSPAlarmReqDTO.getNodeName() + "\"";
        System.out.println("Alarm MO is: " + mo);
        alarm.setManagedObject(mo);
        // Insert finish //

        Event event = Event.builder()
                .eventType(EventType.CREATE)
                .source(Constant.SOURCE_NAME)
                .alarm(alarm).build();

        ListenableFuture<SendResult<String, Event>> listenableFuture
                = kafkaTemplate.send(this.topic, event);
        String res = listenableFuture.get().toString();
        System.out.println("Producer send result:" + res + " to Kafka topic "
                +  this.topic);

        return Result.success(true);
    }

    @ApiOperation(value="To terminate Alarm", notes="To terminate alarm, then send to kafka topic")
    @ApiParam(name = "changeAlarmReqDTO",value = "Change Alarm Info")
    @PostMapping("/terminate")
    public Result<Boolean> sendTerminate(@RequestBody ChangeNSPAlarmReqDTO changeAlarmReqDTO) throws ExecutionException, InterruptedException {

        ChangeAlarmStatus changeAlarmStatus = ChangeAlarmStatusConvert.INSTANCE.convertChangeAlarmStatus(changeAlarmReqDTO);

        Event event = Event.builder()
                .eventType(EventType.TERMINATE)
                .source(Constant.SOURCE_NAME)
                .changeAlarmStatus(changeAlarmStatus)
                .build();

        ListenableFuture<SendResult<String, Event>> listenableFuture
                = kafkaTemplate.send(this.topic, event);
        String res = listenableFuture.get().toString();
        System.out.println("Producer send result:" + res + " to Kafka topic "
                + this.topic);

        return Result.success(true);
    }


    @ApiOperation(value="To acknowledge Alarm", notes="To acknowledge alarm, then send to kafka topic")
    @ApiParam(name = "changeAlarmReqDTO",value = "Change Alarm Info")
    @PostMapping("/ack")
    public Result<Boolean> sendAck(@RequestBody ChangeNSPAlarmReqDTO changeNSPAlarmReqDTO) throws ExecutionException, InterruptedException {

        ChangeAlarmStatus changeAlarmStatus
                = ChangeAlarmStatusConvert.INSTANCE.convertChangeAlarmStatus(changeNSPAlarmReqDTO);

        Event event = Event.builder()
                .eventType(EventType.ACK)
                .source(Constant.SOURCE_NAME)
                .changeAlarmStatus(changeAlarmStatus)
                .build();

        ListenableFuture<SendResult<String, Event>> listenableFuture
                = kafkaTemplate.send(this.topic, event);
        String res = listenableFuture.get().toString();
        System.out.println("Producer send result:" + res + " to Kafka topic "
                + this.topic);

        return Result.success(true);
    }


}
