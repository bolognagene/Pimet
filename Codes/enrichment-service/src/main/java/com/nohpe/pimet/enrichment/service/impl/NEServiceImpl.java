package com.nohpe.pimet.enrichment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nohpe.pimet.enrichment.mapper.NEMapper;
import com.nohpe.pimet.enrichment.service.INEService;
import com.nohpe.pimet.utils.entity.NE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NEServiceImpl extends ServiceImpl<NEMapper, NE> implements INEService {

    public List<NE> getNEByNodeName(String source, String nodeName) {
        LambdaQueryWrapper<NE> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(NE::getNodeName, nodeName);
        lambdaQueryWrapper.eq(NE::getSource, source);
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
