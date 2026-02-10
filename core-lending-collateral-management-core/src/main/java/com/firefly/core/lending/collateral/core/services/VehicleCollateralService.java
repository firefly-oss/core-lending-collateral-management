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
import com.firefly.core.lending.collateral.interfaces.dtos.VehicleCollateralDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface VehicleCollateralService {

    /**
     * Retrieves a paginated list of vehicle collateral records associated with a specific collateral asset,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param filterRequest the filtering criteria and pagination information used to query the vehicle collateral records.
     * @return a Mono emitting a paginated response containing a list of VehicleCollateralDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<VehicleCollateralDTO>> findAll(UUID collateralAssetId, FilterRequest<VehicleCollateralDTO> filterRequest);

    /**
     * Creates a new vehicle collateral record for the specified collateral asset.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the vehicle collateral belongs
     * @param dto the VehicleCollateralDTO object containing the details of the vehicle collateral to be created
     * @return a Mono emitting the created VehicleCollateralDTO object
     */
    Mono<VehicleCollateralDTO> create(UUID collateralAssetId, VehicleCollateralDTO dto);

    /**
     * Retrieves a vehicle collateral record's details by its unique identifier within a specified collateral asset.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the vehicle collateral belongs
     * @param vehicleCollateralId the unique identifier of the vehicle collateral to be retrieved
     * @return a Mono emitting the VehicleCollateralDTO containing the details of the vehicle collateral,
     *         or an empty Mono if the vehicle collateral is not found
     */
    Mono<VehicleCollateralDTO> getById(UUID collateralAssetId, UUID vehicleCollateralId);

    /**
     * Updates an existing vehicle collateral record identified by its collateral asset ID and vehicle collateral ID
     * with the provided updated details.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the vehicle collateral belongs
     * @param vehicleCollateralId the unique identifier of the vehicle collateral to be updated
     * @param dto the data transfer object containing the updated details of the vehicle collateral
     * @return a Mono emitting the updated VehicleCollateralDTO object
     */
    Mono<VehicleCollateralDTO> update(UUID collateralAssetId, UUID vehicleCollateralId, VehicleCollateralDTO dto);

    /**
     * Deletes a vehicle collateral record associated with a specific collateral asset by their respective identifiers.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the vehicle collateral belongs
     * @param vehicleCollateralId the unique identifier of the vehicle collateral to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID collateralAssetId, UUID vehicleCollateralId);
}

