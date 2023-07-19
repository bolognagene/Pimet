package com.nohpe.pimet.core.client;

import com.nohpe.pimet.core.constant.Constant;
import com.nohpe.pimet.utils.entity.dto.NEDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = Constant.ENRICHMENT_SERVICE_NAME, path = Constant.ENRICHMENT_PREFIX,
        fallbackFactory = EnrichmentServiceFallback.class)
public interface IAlarmEnrichmentClient {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public Result<NEDTO> enrichAlarm(@RequestParam("source") String source, @RequestParam("nodeName") String nodeName);
}
