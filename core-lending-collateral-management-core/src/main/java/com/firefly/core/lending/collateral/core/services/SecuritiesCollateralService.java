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
import com.firefly.core.lending.collateral.interfaces.dtos.SecuritiesCollateralDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SecuritiesCollateralService {

    /**
     * Retrieves a paginated list of securities collateral records associated with a specific collateral asset,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralAssetId the unique identifier of the collateral asset.
     * @param filterRequest the filtering criteria and pagination information used to query the securities collateral records.
     * @return a Mono emitting a paginated response containing a list of SecuritiesCollateralDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<SecuritiesCollateralDTO>> findAll(UUID collateralAssetId, FilterRequest<SecuritiesCollateralDTO> filterRequest);

    /**
     * Creates a new securities collateral record for the specified collateral asset.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the securities collateral belongs
     * @param dto the SecuritiesCollateralDTO object containing the details of the securities collateral to be created
     * @return a Mono emitting the created SecuritiesCollateralDTO object
     */
    Mono<SecuritiesCollateralDTO> create(UUID collateralAssetId, SecuritiesCollateralDTO dto);

    /**
     * Retrieves a securities collateral record's details by its unique identifier within a specified collateral asset.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the securities collateral belongs
     * @param securitiesCollateralId the unique identifier of the securities collateral to be retrieved
     * @return a Mono emitting the SecuritiesCollateralDTO containing the details of the securities collateral,
     *         or an empty Mono if the securities collateral is not found
     */
    Mono<SecuritiesCollateralDTO> getById(UUID collateralAssetId, UUID securitiesCollateralId);

    /**
     * Updates an existing securities collateral record identified by its collateral asset ID and securities collateral ID
     * with the provided updated details.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the securities collateral belongs
     * @param securitiesCollateralId the unique identifier of the securities collateral to be updated
     * @param dto the data transfer object containing the updated details of the securities collateral
     * @return a Mono emitting the updated SecuritiesCollateralDTO object
     */
    Mono<SecuritiesCollateralDTO> update(UUID collateralAssetId, UUID securitiesCollateralId, SecuritiesCollateralDTO dto);

    /**
     * Deletes a securities collateral record associated with a specific collateral asset by their respective identifiers.
     *
     * @param collateralAssetId the unique identifier of the collateral asset to which the securities collateral belongs
     * @param securitiesCollateralId the unique identifier of the securities collateral to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID collateralAssetId, UUID securitiesCollateralId);
}

