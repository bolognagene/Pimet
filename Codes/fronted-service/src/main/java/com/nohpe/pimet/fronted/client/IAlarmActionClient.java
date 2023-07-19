package com.nohpe.pimet.fronted.client;

import com.nohpe.pimet.fronted.constant.Constant;
import com.nohpe.pimet.utils.entity.dto.ActionDTO;
import com.nohpe.pimet.utils.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = Constant.PIMET_CORE_SERVICE_NAME, path = Constant.PIMET_CORE_ALARM_ACTION_PREFIX,
        fallbackFactory = PimetCoreAlarmActionServiceFallback.class)
public interface IAlarmActionClient {

    @RequestMapping(value="/acknowledge", method = RequestMethod.POST)
    public Result acknowledgeAlarm(@RequestBody ActionDTO actionDTO);

    @RequestMapping(value="/terminate", method = RequestMethod.POST)
    public Result terminateAlarm(@RequestBody ActionDTO actionDTO);
}
