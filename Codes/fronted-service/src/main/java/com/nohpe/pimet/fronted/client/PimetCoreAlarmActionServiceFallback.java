package com.nohpe.pimet.fronted.client;

import com.nohpe.pimet.utils.entity.dto.ActionDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class PimetCoreAlarmActionServiceFallback implements FallbackFactory<IAlarmActionClient> {

    @Override
    public IAlarmActionClient create(Throwable cause) {
        return new IAlarmActionClient() {
            @Override
            public Result acknowledgeAlarm(ActionDTO actionDTO) {
                System.out.println("acknowledgeAlarm is called, but fallback!");
                return Result.error("acknowledgeAlarm is called, but fallback!");
            }

            @Override
            public Result terminateAlarm(ActionDTO actionDTO) {
                System.out.println("terminateAlarm is called, but fallback!");
                return Result.error("terminateAlarm is called, but fallback!");
            }
        };
    }
}
