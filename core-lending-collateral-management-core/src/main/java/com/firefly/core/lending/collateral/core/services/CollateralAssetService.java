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
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralAssetDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollateralAssetService {

    /**
     * Retrieves a paginated list of collateral assets associated with a specific collateral case,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralCaseId the unique identifier of the collateral case.
     * @param filterRequest the filtering criteria and pagination information used to query the collateral assets.
     * @return a Mono emitting a paginated response containing a list of CollateralAssetDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<CollateralAssetDTO>> findAll(UUID collateralCaseId, FilterRequest<CollateralAssetDTO> filterRequest);

    /**
     * Creates a new collateral asset for the specified collateral case.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the asset belongs
     * @param dto the CollateralAssetDTO object containing the details of the collateral asset to be created
     * @return a Mono emitting the created CollateralAssetDTO object
     */
    Mono<CollateralAssetDTO> create(UUID collateralCaseId, CollateralAssetDTO dto);

    /**
     * Retrieves a collateral asset's details by its unique identifier within a specified collateral case.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the asset belongs
     * @param collateralAssetId the unique identifier of the collateral asset to be retrieved
     * @return a Mono emitting the CollateralAssetDTO containing the details of the collateral asset,
     *         or an empty Mono if the asset is not found
     */
    Mono<CollateralAssetDTO> getById(UUID collateralCaseId, UUID collateralAssetId);

    /**
     * Updates an existing collateral asset identified by its collateral case ID and collateral asset ID
     * with the provided updated details.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the asset belongs
     * @param collateralAssetId the unique identifier of the collateral asset to be updated
     * @param dto the data transfer object containing the updated details of the collateral asset
     * @return a Mono emitting the updated CollateralAssetDTO object
     */
    Mono<CollateralAssetDTO> update(UUID collateralCaseId, UUID collateralAssetId, CollateralAssetDTO dto);

    /**
     * Deletes a collateral asset associated with a specific collateral case by their respective identifiers.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the asset belongs
     * @param collateralAssetId the unique identifier of the collateral asset to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId);
}
