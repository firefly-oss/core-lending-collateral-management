package com.catalis.core.lending.collateral.core.services.collateral.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralPartyDTO;
import reactor.core.publisher.Mono;

public interface CollateralPartyService {

    /**
     * Retrieves a paginated list of collateral parties associated with a specific collateral case and collateral asset,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the parties are associated.
     * @param collateralAssetId the unique identifier of the collateral asset to which the parties are related.
     * @param filterRequest the filtering criteria and pagination information used to query the collateral parties.
     * @return a Mono emitting a paginated response containing a list of CollateralPartyDTO objects
     *         that match the provided filtering criteria.
     */
    Mono<PaginationResponse<CollateralPartyDTO>> findAll(Long collateralCaseId, Long collateralAssetId,
                                                         FilterRequest<CollateralPartyDTO> filterRequest);

    /**
     * Creates a new collateral party within the specified collateral case and collateral asset.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the collateral party belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the collateral party is associated
     * @param dto the CollateralPartyDTO object containing the details of the collateral party to be created
     * @return a Mono emitting the created CollateralPartyDTO object
     */
    Mono<CollateralPartyDTO> create(Long collateralCaseId, Long collateralAssetId, CollateralPartyDTO dto);

    /**
     * Retrieves a specific collateral party associated with the given collateral case, asset, and party IDs.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the party belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the party belongs
     * @param collateralPartyId the unique identifier of the collateral party to be retrieved
     * @return a Mono emitting the CollateralPartyDTO containing the details of the collateral party,
     *         or an empty Mono if the party is not found
     */
    Mono<CollateralPartyDTO> getById(Long collateralCaseId, Long collateralAssetId, Long collateralPartyId);

    /**
     * Updates an existing collateral party associated with the specified collateral case, asset, and party identifiers
     * using the provided details.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the party belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the party is associated
     * @param collateralPartyId the unique identifier of the collateral party to be updated
     * @param dto the data transfer object containing the updated details of the collateral party
     * @return a Mono emitting the updated CollateralPartyDTO object
     */
    Mono<CollateralPartyDTO> update(Long collateralCaseId, Long collateralAssetId, Long collateralPartyId,
                                    CollateralPartyDTO dto);

    /**
     * Deletes a collateral party identified by the specified collateral case ID,
     * collateral asset ID, and collateral party ID.
     *
     * @param collateralCaseId the unique identifier of the collateral case
     * @param collateralAssetId the unique identifier of the collateral asset
     * @param collateralPartyId the unique identifier of the collateral party to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(Long collateralCaseId, Long collateralAssetId, Long collateralPartyId);
}
