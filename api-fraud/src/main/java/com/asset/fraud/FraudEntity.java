package com.asset.fraud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FraudEntity {
    @Id
    @SequenceGenerator(
            name = "fraud_entity_id_sequence",
            sequenceName = "fraud_entity_id_sequence"

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud__entity_id_sequence"
    )
    private Integer id;
    private String email;
    private LocalDateTime createAt;
    private Boolean isFraudster;
}
