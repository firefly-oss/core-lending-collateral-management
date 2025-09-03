package com.firefly.core.lending.collateral.core.services.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralLienDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollateralLienService {

    /**
     * Retrieves a paginated list of collateral liens associated with a specific collateral case and collateral asset,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the liens are associated.
     * @param collateralAssetId the unique identifier of the collateral asset to which the liens are related.
     * @param filterRequest the filtering criteria and pagination information used to query the collateral liens.
     * @return a Mono emitting a paginated response containing a list of CollateralLienDTO objects
     *         that match the provided filtering criteria.
     */
    Mono<PaginationResponse<CollateralLienDTO>> findAll(UUID collateralCaseId, UUID collateralAssetId,
                                                        FilterRequest<CollateralLienDTO> filterRequest);

    /**
     * Creates a new collateral lien for the specified collateral case and collateral asset.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the lien belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the lien is associated
     * @param dto the CollateralLienDTO object containing the details of the collateral lien to be created
     * @return a Mono emitting the created CollateralLienDTO object
     */
    Mono<CollateralLienDTO> create(UUID collateralCaseId, UUID collateralAssetId, CollateralLienDTO dto);

    /**
     * Retrieves a specific collateral lien associated with a given collateral case ID,
     * collateral asset ID, and collateral lien ID.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the lien belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the lien belongs
     * @param collateralLienId the unique identifier of the collateral lien to be retrieved
     * @return a Mono emitting the CollateralLienDTO containing the details of the collateral lien,
     *         or an empty Mono if the lien is not found
     */
    Mono<CollateralLienDTO> getById(UUID collateralCaseId, UUID collateralAssetId, UUID collateralLienId);

    /**
     * Updates an existing collateral lien within a specific collateral case and collateral asset
     * using the provided details.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the lien belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the lien belongs
     * @param collateralLienId the unique identifier of the collateral lien to be updated
     * @param dto the data transfer object containing the updated details of the collateral lien
     * @return a Mono emitting the updated CollateralLienDTO object
     */
    Mono<CollateralLienDTO> update(UUID collateralCaseId, UUID collateralAssetId, UUID collateralLienId,
                                   CollateralLienDTO dto);

    /**
     * Deletes a collateral lien identified by its unique identifiers within a specified collateral case and asset.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the lien belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the lien belongs
     * @param collateralLienId the unique identifier of the collateral lien to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId, UUID collateralLienId);
}
