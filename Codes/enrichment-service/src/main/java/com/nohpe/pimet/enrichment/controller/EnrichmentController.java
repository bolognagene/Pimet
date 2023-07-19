package com.nohpe.pimet.enrichment.controller;

import com.nohpe.pimet.enrichment.constant.Constant;
import com.nohpe.pimet.enrichment.repository.NERepository;
import com.nohpe.pimet.enrichment.service.INEService;
import com.nohpe.pimet.enrichment.service.impl.NEServiceImpl;
import com.nohpe.pimet.utils.convert.NEConvert;
import com.nohpe.pimet.utils.entity.NE;
import com.nohpe.pimet.utils.entity.dto.NEDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value="Endpoint for Alarm enrichment")
@RestController
@Slf4j
@RequestMapping(Constant.PREFIX)
public class EnrichmentController {

    @Value("${spring.application.integration}")
    private String integration;

    @Autowired
    private INEService neService;  //Mysql

    @Autowired
    private NERepository neRepository;  //Neo4j

    @ApiOperation(value="To Enrich Alarm", notes="To get detailed NE information")
    @ApiParam(name = "nodeName",value = "Node Name")
    @RequestMapping(value="/", method = RequestMethod.GET)
    public Result<NEDTO> enrichAlarm(@RequestParam("source") String source, @RequestParam("nodeName") String nodeName) {

        System.out.println("source is: " + source + " node_name is: " + nodeName);

        if(!nodeName.isEmpty()
            && !source.isEmpty()) {

            List<NE> nes = new ArrayList<NE>();

            if(integration.toLowerCase().equals("neo4j")) {
                nes = neRepository.findNEByNodeName(source, nodeName);
            } else {
                nes = neService.getNEByNodeName(source, nodeName);
            }

            if(nes.size() > 0) {
                NEDTO nedto = NEConvert.INSTANCE.convertNEDTO(nes.get(0));
                return Result.success(nedto);
            }
        }

        NEDTO nedtoNull = new NEDTO();
        return Result.error("Can't find NE for NodeName: " + nodeName, nedtoNull);
    }

}
