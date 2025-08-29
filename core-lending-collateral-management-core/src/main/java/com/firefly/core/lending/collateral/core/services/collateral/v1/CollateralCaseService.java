package com.firefly.core.lending.collateral.core.services.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralCaseDTO;
import reactor.core.publisher.Mono;

public interface CollateralCaseService {

    /**
     * Retrieves all collateral cases based on the provided filtering criteria.
     *
     * @param filterRequest the filtering criteria and pagination information used to query collateral cases.
     * @return a Mono emitting a paginated response containing a list of CollateralCaseDTO objects that match the filtering criteria.
     */
    Mono<PaginationResponse<CollateralCaseDTO>> findAll(FilterRequest<CollateralCaseDTO> filterRequest);

    /**
     * Creates a new collateral case using the provided details.
     *
     * @param dto the CollateralCaseDTO object containing the details of the collateral case to create
     * @return a Mono emitting the created CollateralCaseDTO object
     */
    Mono<CollateralCaseDTO> create(CollateralCaseDTO dto);

    /**
     * Retrieves a collateral case by its unique identifier.
     *
     * @param collateralCaseId the unique identifier of the collateral case to be retrieved
     * @return a {@code Mono} emitting the {@code CollateralCaseDTO} corresponding to the provided ID, or an empty Mono if no collateral case is found
     */
    Mono<CollateralCaseDTO> getById(Long collateralCaseId);

    /**
     * Updates an existing collateral case with the given details.
     *
     * @param collateralCaseId the unique identifier of the collateral case to be updated
     * @param dto the data transfer object containing the updated details of the collateral case
     * @return a Mono emitting the updated collateral case details encapsulated in {@code CollateralCaseDTO}
     */
    Mono<CollateralCaseDTO> update(Long collateralCaseId, CollateralCaseDTO dto);

    /**
     * Deletes a collateral case identified by its ID.
     *
     * @param collateralCaseId the unique identifier of the collateral case to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(Long collateralCaseId);
}