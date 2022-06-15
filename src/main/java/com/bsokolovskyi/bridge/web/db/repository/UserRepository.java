package com.bsokolovskyi.bridge.web.db.repository;

import com.bsokolovskyi.bridge.web.db.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
