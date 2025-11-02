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
import com.firefly.core.lending.collateral.interfaces.dtos.RealEstatePropertyDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RealEstatePropertyService {

    /**
     * Retrieves a paginated list of real estate property details associated with a specific collateral asset,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param filterRequest the filtering criteria and pagination information used to query the property details.
     * @return a Mono emitting a paginated response containing a list of RealEstatePropertyDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<RealEstatePropertyDTO>> findAll(UUID collateralAssetId, FilterRequest<RealEstatePropertyDTO> filterRequest);

    /**
     * Creates new real estate property details for the specified collateral asset.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param dto the RealEstatePropertyDTO object containing the details of the property to create.
     * @return a Mono emitting the created RealEstatePropertyDTO object.
     */
    Mono<RealEstatePropertyDTO> create(UUID collateralAssetId, RealEstatePropertyDTO dto);

    /**
     * Retrieves specific real estate property details by its unique identifier.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param propertyId the unique identifier of the property.
     * @return a Mono emitting the RealEstatePropertyDTO object if found.
     */
    Mono<RealEstatePropertyDTO> getById(UUID collateralAssetId, UUID propertyId);

    /**
     * Updates existing real estate property details.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param propertyId the unique identifier of the property to update.
     * @param dto the RealEstatePropertyDTO object containing the updated details.
     * @return a Mono emitting the updated RealEstatePropertyDTO object.
     */
    Mono<RealEstatePropertyDTO> update(UUID collateralAssetId, UUID propertyId, RealEstatePropertyDTO dto);

    /**
     * Deletes real estate property details by its unique identifier.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param propertyId the unique identifier of the property to delete.
     * @return a Mono signaling completion.
     */
    Mono<Void> delete(UUID collateralAssetId, UUID propertyId);
}

