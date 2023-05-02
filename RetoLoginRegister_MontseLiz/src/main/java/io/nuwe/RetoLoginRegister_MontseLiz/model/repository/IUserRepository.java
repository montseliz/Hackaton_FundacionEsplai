package io.nuwe.RetoLoginRegister_MontseLiz.model.repository;

import io.nuwe.RetoLoginRegister_MontseLiz.model.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    Optional<User> findByRole(String roleName);

}
