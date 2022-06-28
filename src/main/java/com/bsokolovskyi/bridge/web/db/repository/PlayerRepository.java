package com.bsokolovskyi.bridge.web.db.repository;

import com.bsokolovskyi.bridge.web.db.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

}
