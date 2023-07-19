package com.nohpe.pimet.core.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nohpe.pimet.core.service.IAlarmService;
import com.nohpe.pimet.utils.entity.Alarm;
import com.nohpe.pimet.utils.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.PlatformManagedObject;

@Component
public class PimetCoreUtils {

    @Autowired
    IAlarmService alarmService;

    public LambdaUpdateWrapper<Alarm> AutoClearAction(String autoClearPolicy,
            Event event, LambdaUpdateWrapper<Alarm> lambdaUpdateWrapper) {

        if(lambdaUpdateWrapper == null) {
            lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        }

        if(autoClearPolicy.isEmpty()) {
            return lambdaUpdateWrapper;
        }

        if (autoClearPolicy.toUpperCase().equals("FIELDALARMAGENTID")) {
            // If the policy is FieldAlarmAgentId, Pimet will use field: AlarmAgentId as key to
            // do the auto-clear
            lambdaUpdateWrapper.eq(Alarm::getAlarmAgentId, event.getChangeAlarmStatus().getKeyField());
        } else if (autoClearPolicy.toUpperCase().equals("FIELDNOTIFICATIONID")) {
            // If the policy is FieldNotificationId, Pimet will use field: NotificationId as key to
            // do the auto-clear
            lambdaUpdateWrapper.eq(Alarm::getNotificationId, event.getChangeAlarmStatus().getKeyField());
        } else if (autoClearPolicy.toUpperCase().equals("SAMEPROBLEMTYPEANDSEVERITY")) {
            // If the policy is SameProblemTypeAndSeverity Pimet will use:
            // If notificationId is present, then use notificationId, ManagedObject and Severity
            // otherwise, use ManagedObject, ProbableCause, AlarmType, SpecificProblem and Severity
            if (!event.getAlarm().getNotificationId().isEmpty()) {
                lambdaUpdateWrapper.eq(Alarm::getNotificationId, event.getAlarm().getNotificationId())
                        .eq(Alarm::getManagedObject, event.getAlarm().getManagedObject())
                        .eq(Alarm::getPerceivedSeverity, event.getAlarm().getPerceivedSeverity());
            } else {
                lambdaUpdateWrapper.eq(Alarm::getManagedObject, event.getAlarm().getManagedObject())
                        .eq(Alarm::getProbableCause, event.getAlarm().getProbableCause())
                        .eq(Alarm::getAlarmType, event.getAlarm().getAlarmType())
                        .eq(Alarm::getSpecificProblem, event.getAlarm().getSpecificProblem())
                        .eq(Alarm::getPerceivedSeverity, event.getAlarm().getPerceivedSeverity());
            }
        } else if (autoClearPolicy.toUpperCase().equals("SAMEPROBLEMTYPENONOTIFID")) {
            // If the policy is SameProblemTypeNoNotifID, Pimet will use:
            // ManagedObject, ProbableCause, AlarmType and SpecificProblem
            lambdaUpdateWrapper.eq(Alarm::getManagedObject, event.getAlarm().getManagedObject())
                    .eq(Alarm::getProbableCause, event.getAlarm().getProbableCause())
                    .eq(Alarm::getAlarmType, event.getAlarm().getAlarmType())
                    .eq(Alarm::getSpecificProblem, event.getAlarm().getSpecificProblem());
        } else if (autoClearPolicy.toUpperCase().equals("SAMEPROBLEMTYPEANDSEVERITYNONOTIFID")) {
            // If the policy is SameProblemTypeAndSeverityNoNotifID, Pimet will use:
            // ManagedObject, ProbableCause, AlarmType, SpecificProblem and Severity
            lambdaUpdateWrapper.eq(Alarm::getManagedObject, event.getAlarm().getManagedObject())
                    .eq(Alarm::getProbableCause, event.getAlarm().getProbableCause())
                    .eq(Alarm::getAlarmType, event.getAlarm().getAlarmType())
                    .eq(Alarm::getSpecificProblem, event.getAlarm().getSpecificProblem())
                    .eq(Alarm::getPerceivedSeverity, event.getAlarm().getPerceivedSeverity());
        } else {
            // If the policy is SameProblemType or not set, Pimet will use:
            // If notificationId is present, then use notificationId and ManagedObject
            // otherwise, use ManagedObject, ProbableCause, AlarmType and SpecificProblem
            if (!event.getAlarm().getNotificationId().isEmpty()) {
                lambdaUpdateWrapper.eq(Alarm::getNotificationId, event.getAlarm().getNotificationId())
                        .eq(Alarm::getManagedObject, event.getAlarm().getManagedObject());
            } else {
                lambdaUpdateWrapper.eq(Alarm::getManagedObject, event.getAlarm().getManagedObject())
                        .eq(Alarm::getProbableCause, event.getAlarm().getProbableCause())
                        .eq(Alarm::getAlarmType, event.getAlarm().getAlarmType())
                        .eq(Alarm::getSpecificProblem, event.getAlarm().getSpecificProblem());
            }
        }

        return lambdaUpdateWrapper;
    }

    public LambdaUpdateWrapper<Alarm> SimilarAlarmAction(String similarAlarmPolicy,
            Event event, LambdaUpdateWrapper<Alarm> lambdaUpdateWrapper) {

        if(lambdaUpdateWrapper == null) {
            lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        }

        if(similarAlarmPolicy.isEmpty()) {
            return lambdaUpdateWrapper;
        }

        return lambdaUpdateWrapper;
    }

    // Get the latest alarmId with the same source, then +1 for the new inserted alarm
    public Long calculateAlarmId(String source) {

        Long alarmId = alarmService.getLatestAlarmIdBySource(source);

        if(alarmId != null) {
            alarmId = alarmId +1;
        }

        return alarmId;

    }
}
