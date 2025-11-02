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

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralMonitoringDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollateralMonitoringService {

    /**
     * Retrieves a paginated list of collateral monitoring records associated with a specific collateral asset,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param filterRequest the filtering criteria and pagination information used to query the collateral monitoring records.
     * @return a Mono emitting a paginated response containing a list of CollateralMonitoringDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<CollateralMonitoringDTO>> findAll(UUID collateralAssetId, FilterRequest<CollateralMonitoringDTO> filterRequest);

    /**
     * Creates a new collateral monitoring record for the specified collateral asset.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the monitoring record belongs
     * @param dto the CollateralMonitoringDTO object containing the details of the collateral monitoring record to be created
     * @return a Mono emitting the created CollateralMonitoringDTO object
     */
    Mono<CollateralMonitoringDTO> create(UUID collateralAssetId, CollateralMonitoringDTO dto);

    /**
     * Retrieves a collateral monitoring record's details by its unique identifier within a specified collateral asset.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the monitoring record belongs
     * @param collateralMonitoringId the unique identifier of the collateral monitoring record to be retrieved
     * @return a Mono emitting the CollateralMonitoringDTO containing the details of the collateral monitoring record,
     *         or an empty Mono if the monitoring record is not found
     */
    Mono<CollateralMonitoringDTO> getById(UUID collateralAssetId, UUID collateralMonitoringId);

    /**
     * Updates an existing collateral monitoring record identified by its collateral asset ID and collateral monitoring ID
     * with the provided updated details.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the monitoring record belongs
     * @param collateralMonitoringId the unique identifier of the collateral monitoring record to be updated
     * @param dto the data transfer object containing the updated details of the collateral monitoring record
     * @return a Mono emitting the updated CollateralMonitoringDTO object
     */
    Mono<CollateralMonitoringDTO> update(UUID collateralAssetId, UUID collateralMonitoringId, CollateralMonitoringDTO dto);

    /**
     * Deletes a collateral monitoring record associated with a specific collateral asset by their respective identifiers.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the monitoring record belongs
     * @param collateralMonitoringId the unique identifier of the collateral monitoring record to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID collateralAssetId, UUID collateralMonitoringId);
}

