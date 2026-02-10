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
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralInsuranceDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollateralInsuranceService {

    /**
     * Retrieves a paginated list of collateral insurance policies associated with a specific collateral asset,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param filterRequest the filtering criteria and pagination information used to query the insurance policies.
     * @return a Mono emitting a paginated response containing a list of CollateralInsuranceDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<CollateralInsuranceDTO>> findAll(UUID collateralAssetId, FilterRequest<CollateralInsuranceDTO> filterRequest);

    /**
     * Creates a new collateral insurance policy for the specified collateral asset.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param dto the CollateralInsuranceDTO object containing the details of the insurance policy to create.
     * @return a Mono emitting the created CollateralInsuranceDTO object.
     */
    Mono<CollateralInsuranceDTO> create(UUID collateralAssetId, CollateralInsuranceDTO dto);

    /**
     * Retrieves a specific collateral insurance policy by its unique identifier.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param insuranceId the unique identifier of the insurance policy.
     * @return a Mono emitting the CollateralInsuranceDTO object if found.
     */
    Mono<CollateralInsuranceDTO> getById(UUID collateralAssetId, UUID insuranceId);

    /**
     * Updates an existing collateral insurance policy.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param insuranceId the unique identifier of the insurance policy to update.
     * @param dto the CollateralInsuranceDTO object containing the updated details.
     * @return a Mono emitting the updated CollateralInsuranceDTO object.
     */
    Mono<CollateralInsuranceDTO> update(UUID collateralAssetId, UUID insuranceId, CollateralInsuranceDTO dto);

    /**
     * Deletes a collateral insurance policy by its unique identifier.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param insuranceId the unique identifier of the insurance policy to delete.
     * @return a Mono signaling completion.
     */
    Mono<Void> delete(UUID collateralAssetId, UUID insuranceId);
}

