package com.nohpe.pimet.enrichment.service;

import com.nohpe.pimet.utils.entity.NE;

import java.util.List;

public interface INEService {

    public List<NE> getNEByNodeName(String source, String nodeName);
}
