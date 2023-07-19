package com.nohpe.nsp.adapter.convert;

import com.nohpe.nsp.adapter.controller.dto.req.ChangeNSPAlarmReqDTO;
import com.nohpe.pimet.utils.entity.ChangeAlarmStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChangeAlarmStatusConvert {

    ChangeAlarmStatusConvert INSTANCE = Mappers.getMapper(ChangeAlarmStatusConvert.class);

    // VO -> Entity
    @Mapping(source = "alarmId", target = "keyField")
    ChangeAlarmStatus convertChangeAlarmStatus(ChangeNSPAlarmReqDTO changeNSPAlarmReqDTO);
}
