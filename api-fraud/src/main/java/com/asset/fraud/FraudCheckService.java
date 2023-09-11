package com.asset.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    private final FraudEntityRepository fraudEntityRepository;

    public boolean isFraudulentCustomer(String email){
        boolean isFraud = false;

        // Query to decide if it is
        List<FraudEntity> fraudEntities = fraudEntityRepository.findFraudEntitiesByEmail(email);
        if (fraudEntities.isEmpty() == false && fraudEntities.size() >=1){
            isFraud = true;
        }

        // Save fraud check query to history table
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
                .email(email)
                .isFraudster(isFraud)
                .createAt(LocalDateTime.now()).build());

        return isFraud;
    }

    public FraudEntity createFraudRecord(String email){
        FraudEntity fraudEntity = fraudEntityRepository.save(FraudEntity.builder()
                .isFraudster(true)
                .email(email)
                .createAt(LocalDateTime.now())
                .build());
        return fraudEntity;
    }

    public List<FraudEntity> getFraudsters(String email) {
        // Query to decide if it is
        return fraudEntityRepository.findFraudEntitiesByEmail(email);
    }

}
