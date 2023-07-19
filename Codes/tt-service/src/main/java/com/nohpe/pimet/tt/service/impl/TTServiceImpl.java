package com.nohpe.pimet.tt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nohpe.pimet.tt.mapper.TTMapper;
import com.nohpe.pimet.tt.service.ITTService;
import com.nohpe.pimet.utils.entity.TT;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TTServiceImpl extends ServiceImpl<TTMapper, TT> implements ITTService {

    public boolean addTT(TT tt) {
        return this.save(tt);
    }

    public TT queryTTByAlarmId(String alarmId) {
        LambdaQueryWrapper<TT> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TT::getAlarmId, alarmId);

        List<TT> TTs = this.baseMapper.selectList(lambdaQueryWrapper);

        if(TTs.size() > 0) {
            return TTs.get(0);
        }

        return null;
    }
}
