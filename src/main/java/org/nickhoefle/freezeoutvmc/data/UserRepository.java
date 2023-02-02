package org.nickhoefle.freezeoutvmc.data;

import org.nickhoefle.freezeoutvmc.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}