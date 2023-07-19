package com.nohpe.pimet.enrichment.test;

import com.nohpe.pimet.enrichment.repository.NERepository;
import com.nohpe.pimet.utils.entity.NE;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Neo4jQueryTest {

    @Autowired
    private NERepository neRepository;

    @Test
    void queryNodeTest() {
        String source = "nsp";
        String node_name = "ie680viwf01-lab";

        List<NE> nes = neRepository.findNEByNodeName(source, node_name);

        nes.forEach(ne -> {
            System.out.println(ne.toString());
        });

        assertThat(nes.size()==1);
        assertThat(nes.get(0).getNeType()!=null);
        assertThat(nes.get(0).getNeType().equals("nfmp"));

    }
}
