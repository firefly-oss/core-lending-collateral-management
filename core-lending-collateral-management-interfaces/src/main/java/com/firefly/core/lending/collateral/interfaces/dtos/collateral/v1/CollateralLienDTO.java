package com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.LienTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralLienDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long collateralLienId;

    @FilterableId
    private Long collateralAssetId;

    private LienTypeEnum lienType;
    private String registrationDetails;
    private Boolean isReleased;
    private LocalDate lienDate;
    private LocalDate releaseDate;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}