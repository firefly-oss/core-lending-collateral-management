/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.collateral.core.services;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralValuationDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollateralValuationService {

    /**
     * Retrieves a paginated list of collateral valuations associated with a specified collateral case
     * and collateral asset, based on the provided filtering criteria.
     *
     * @param collateralCaseId the unique identifier of the collateral case
     * @param collateralAssetId the unique identifier of the collateral asset within the collateral case
     * @param filterRequest the filtering criteria and pagination information used to query the collateral valuations
     * @return a Mono emitting a paginated response containing a list of CollateralValuationDTO objects
     *         that match the filtering criteria
     */
    Mono<PaginationResponse<CollateralValuationDTO>> findAll(UUID collateralCaseId, UUID collateralAssetId,
                                                             FilterRequest<CollateralValuationDTO> filterRequest);

    /**
     * Creates a new collateral valuation for a specified collateral case and collateral asset.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the valuation is linked
     * @param collateralAssetId the unique identifier of the collateral asset to which the valuation is linked
     * @param dto the CollateralValuationDTO object containing the details of the valuation to be created
     * @return a Mono emitting the created CollateralValuationDTO object
     */
    Mono<CollateralValuationDTO> create(UUID collateralCaseId, UUID collateralAssetId, CollateralValuationDTO dto);

    /**
     * Retrieves a specific collateral valuation based on the provided identifiers.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the valuation belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the valuation belongs
     * @param collateralValuationId the unique identifier of the collateral valuation to be retrieved
     * @return a Mono emitting the CollateralValuationDTO containing the details of the specified collateral valuation,
     *         or an empty Mono if the valuation is not found
     */
    Mono<CollateralValuationDTO> getById(UUID collateralCaseId, UUID collateralAssetId, UUID collateralValuationId);

    /**
     * Updates an existing collateral valuation identified by its collateral case ID, collateral asset ID,
     * and collateral valuation ID with the provided updated details.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the valuation is associated
     * @param collateralAssetId the unique identifier of the collateral asset to which the valuation is associated
     * @param collateralValuationId the unique identifier of the collateral valuation to be updated
     * @param dto the data transfer object containing the updated details of the collateral valuation
     * @return a Mono emitting the updated CollateralValuationDTO object
     */
    Mono<CollateralValuationDTO> update(UUID collateralCaseId, UUID collateralAssetId, UUID collateralValuationId,
                                        CollateralValuationDTO dto);

    /**
     * Deletes a collateral valuation identified by the specified collateral case ID, collateral asset ID,
     * and collateral valuation ID.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the valuation belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the valuation is associated
     * @param collateralValuationId the unique identifier of the collateral valuation to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId, UUID collateralValuationId);
}
