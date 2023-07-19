package com.nohpe.pimet.core.client;

import com.nohpe.pimet.utils.entity.NE;
import com.nohpe.pimet.utils.entity.dto.NEDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class EnrichmentServiceFallback implements FallbackFactory<IAlarmEnrichmentClient> {

    @Override
    public IAlarmEnrichmentClient create(Throwable cause) {

        return new IAlarmEnrichmentClient() {
            @Override
            public Result<NEDTO> enrichAlarm(@RequestParam("source") String source, @RequestParam("nodeName") String nodeName) {
                System.out.println("enrichAlarm is called, but fallback!");

                NEDTO nedtoNull = new NEDTO();
                return Result.error("enrichAlarm is called, but fallback!", nedtoNull);
            }

        };
    }
}
