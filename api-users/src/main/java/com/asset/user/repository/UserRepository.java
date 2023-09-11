package com.asset.user.repository;

import com.asset.user.entity.User;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CosmosRepository<User,String>, CrudRepository<User,String> {
}
