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
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralPartyDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
    Mono<PaginationResponse<CollateralPartyDTO>> findAll(UUID collateralCaseId, UUID collateralAssetId,
                                                         FilterRequest<CollateralPartyDTO> filterRequest);

    /**
     * Creates a new collateral party within the specified collateral case and collateral asset.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the collateral party belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the collateral party is associated
     * @param dto the CollateralPartyDTO object containing the details of the collateral party to be created
     * @return a Mono emitting the created CollateralPartyDTO object
     */
    Mono<CollateralPartyDTO> create(UUID collateralCaseId, UUID collateralAssetId, CollateralPartyDTO dto);

    /**
     * Retrieves a specific collateral party associated with the given collateral case, asset, and party IDs.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the party belongs
     * @param collateralAssetId the unique identifier of the collateral asset to which the party belongs
     * @param collateralPartyId the unique identifier of the collateral party to be retrieved
     * @return a Mono emitting the CollateralPartyDTO containing the details of the collateral party,
     *         or an empty Mono if the party is not found
     */
    Mono<CollateralPartyDTO> getById(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId);

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
    Mono<CollateralPartyDTO> update(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId,
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
    Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId);
}
