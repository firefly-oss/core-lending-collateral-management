package com.firefly.core.lending.collateral.models.entities.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("guarantee_party")
public class GuaranteeParty {

    @Id
    @Column("guarantee_party_id")
    private UUID guaranteePartyId;

    @Column("guarantee_record_id")
    private UUID guaranteeRecordId;

    @Column("party_id")
    private UUID partyId;

    @Column("guarantee_role")
    private GuaranteeRoleEnum guaranteeRole;

    @Column("additional_details")
    private String additionalDetails;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}