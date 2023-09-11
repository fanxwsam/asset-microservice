package com.asset.fraud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudEntityRepository extends JpaRepository<FraudEntity, Integer> {

    List<FraudEntity> findFraudEntitiesByEmail(String email);

}
