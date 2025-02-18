package com.catalis.core.lending.collateral.models.entities.collateral.v1;

import com.catalis.core.lending.collateral.interfaces.enums.collateral.v1.LienTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_lien")
public class CollateralLien {

    @Id
    @Column("collateral_lien_id")
    private Long collateralLienId;

    @Column("collateral_asset_id")
    private Long collateralAssetId;

    @Column("lien_type")
    private LienTypeEnum lienType;

    @Column("registration_details")
    private String registrationDetails;

    @Column("is_released")
    private Boolean isReleased;

    @Column("lien_date")
    private LocalDate lienDate;

    @Column("release_date")
    private LocalDate releaseDate;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}