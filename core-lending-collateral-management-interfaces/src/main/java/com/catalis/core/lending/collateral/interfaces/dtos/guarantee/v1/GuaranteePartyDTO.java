package com.catalis.core.lending.collateral.interfaces.dtos.guarantee.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteePartyDTO {
    private Long guaranteePartyId;

    @FilterableId
    private Long guaranteeRecordId;

    @FilterableId
    private Long partyId;

    private GuaranteeRoleEnum guaranteeRole;
    private String additionalDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}