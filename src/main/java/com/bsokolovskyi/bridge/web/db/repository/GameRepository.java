package com.bsokolovskyi.bridge.web.db.repository;

import com.bsokolovskyi.bridge.core.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

}
