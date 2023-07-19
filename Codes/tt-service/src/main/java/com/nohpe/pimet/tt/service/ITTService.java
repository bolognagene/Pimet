package com.nohpe.pimet.tt.service;

import com.nohpe.pimet.utils.entity.TT;

public interface ITTService {

    public boolean addTT(TT tt);

    public TT queryTTByAlarmId(String alarmId);
}
