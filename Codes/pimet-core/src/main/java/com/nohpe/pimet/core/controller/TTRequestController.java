package com.nohpe.pimet.core.controller;

import com.nohpe.pimet.utils.convert.TTConvert;
import com.nohpe.pimet.utils.entity.TT;
import com.nohpe.pimet.utils.entity.dto.TTDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Api(value="Endpoint for tt-service in pimet core")
@RestController
@RequestMapping("/core/tt")
public class TTRequestController {

    @Value("${spring.kafka.tt-req-topic}")
    private String tt_req_topic;

    private final KafkaTemplate<String, TT> kafkaTemplate;

    public TTRequestController(KafkaTemplate<String, TT> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @ApiOperation(value="To Create new TT", notes="To get new TT attributes, then send to kafka topic")
    @ApiParam(name = "TTDTO",value = "New TT attributes")
    @PostMapping("/new")
    public Result<Boolean> sendCreate(@RequestBody TTDTO ttDTO) throws ExecutionException, InterruptedException {

        TT tt = TTConvert.INSTANCE.convertTT(ttDTO);

        ListenableFuture<SendResult<String, TT>> listenableFuture
                = kafkaTemplate.send(this.tt_req_topic, tt);
        String res = listenableFuture.get().toString();
        System.out.println("Producer send result:" + res + " to Kafka topic "
                +  this.tt_req_topic);

        return Result.success(true);
    }

}
