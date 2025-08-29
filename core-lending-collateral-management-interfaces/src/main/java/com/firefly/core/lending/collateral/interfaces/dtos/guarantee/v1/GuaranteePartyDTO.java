package com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeRoleEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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