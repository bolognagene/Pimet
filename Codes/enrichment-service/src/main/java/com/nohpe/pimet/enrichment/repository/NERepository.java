package com.nohpe.pimet.enrichment.repository;

import com.nohpe.pimet.utils.entity.NE;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NERepository extends Neo4jRepository<NE, Long> {

    @Query("MATCH (n:Node {source: $0 ,node_name: $1}) RETURN n")
    public List<NE> findNEByNodeName(String source, String node_name);
}
