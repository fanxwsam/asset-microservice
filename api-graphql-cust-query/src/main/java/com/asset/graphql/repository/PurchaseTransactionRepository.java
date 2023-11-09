package com.asset.graphql.repository;

import com.asset.graphql.entity.PurchaseTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransactionEntity, String> {

}
