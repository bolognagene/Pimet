package com.nohpe.nsp.adapter.convert;

import com.nohpe.nsp.adapter.controller.dto.req.CreateNSPAlarmReqDTO;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.utils.enums.AlarmType;
import com.nohpe.pimet.utils.enums.PerceivedSeverity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlarmConvert {

    AlarmConvert INSTANCE = Mappers.getMapper(AlarmConvert.class);

    //DTO -> Entity
    @Mapping(target = "managedObject", ignore = true)
    @Mapping(source = "alarmId", target = "alarmAgentId")
    @Mapping(source = "perceivedSeverity", target = "perceivedSeverity", qualifiedByName = "setPerceivedSeverity")
    @Mapping(source = "alarmType", target = "alarmType", qualifiedByName = "setAlarmType")
    Alarm convertAlarm(CreateNSPAlarmReqDTO createNSPAlarmReqDTO);

    @Named("setPerceivedSeverity")
    default PerceivedSeverity setPerceivedSeverity(String ps) {
        return PerceivedSeverity.valueOf(ps.toUpperCase());
    }

    @Named("setAlarmType")
    default AlarmType setAlarmType(String at) {

        AlarmType alarmType = null;

        switch (at.toUpperCase()) {
            case "COMMUNICATION_ALARM":
            case "COMMUNICATIONALARM":
                alarmType = AlarmType.COMMUNICATION_ALARM;
                break;
            case "ENVIRONMENTAL_ALARM":
            case "ENVIRONMENTALALARM":
                alarmType = AlarmType.ENVIRONMENTAL_ALARM;
                break;
            case "EQUIPMENT_ALARM":
            case "EQUIPMENTALARM":
                alarmType = AlarmType.EQUIPMENT_ALARM;
                break;
            case "PROCESSING_ERROR_ALARM":
            case "PROCESSINGERRORALARM":
                alarmType = AlarmType.PROCESSING_ERROR_ALARM;
                break;
            case "QUALITY_OF_SERVICE_ALARM":
            case "QUALITYOFSERVICEALARM":
                alarmType = AlarmType.QUALITY_OF_SERVICE_ALARM;
                break;
            case "SECURITY_ALARM":
            case "SECURITYALARM":
                alarmType = AlarmType.SECURITY_ALARM;
                break;
            default:
                throw new IllegalArgumentException(
                        "Invalid value: " + at.toUpperCase() + " for enum  AlarmType" );
        }

        return alarmType;
    }
}
