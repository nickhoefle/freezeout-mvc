package org.nickhoefle.freezeoutmvc.data;

import org.nickhoefle.freezeoutmvc.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}