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
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteeClaimDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GuaranteeClaimService {

    /**
     * Retrieves a paginated list of guarantee claims associated with a specific guarantee record,
     * based on the provided filtering and pagination criteria.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record.
     * @param filterRequest the filtering criteria and pagination information used to query the guarantee claims.
     * @return a Mono emitting a paginated response containing a list of GuaranteeClaimDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<GuaranteeClaimDTO>> findAll(UUID guaranteeRecordId, FilterRequest<GuaranteeClaimDTO> filterRequest);

    /**
     * Creates a new guarantee claim for the specified guarantee record.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the claim belongs
     * @param dto the GuaranteeClaimDTO object containing the details of the guarantee claim to be created
     * @return a Mono emitting the created GuaranteeClaimDTO object
     */
    Mono<GuaranteeClaimDTO> create(UUID guaranteeRecordId, GuaranteeClaimDTO dto);

    /**
     * Retrieves a guarantee claim's details by its unique identifier within a specified guarantee record.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the claim belongs
     * @param guaranteeClaimId the unique identifier of the guarantee claim to be retrieved
     * @return a Mono emitting the GuaranteeClaimDTO containing the details of the guarantee claim,
     *         or an empty Mono if the claim is not found
     */
    Mono<GuaranteeClaimDTO> getById(UUID guaranteeRecordId, UUID guaranteeClaimId);

    /**
     * Updates an existing guarantee claim identified by its guarantee record ID and guarantee claim ID
     * with the provided updated details.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the claim belongs
     * @param guaranteeClaimId the unique identifier of the guarantee claim to be updated
     * @param dto the data transfer object containing the updated details of the guarantee claim
     * @return a Mono emitting the updated GuaranteeClaimDTO object
     */
    Mono<GuaranteeClaimDTO> update(UUID guaranteeRecordId, UUID guaranteeClaimId, GuaranteeClaimDTO dto);

    /**
     * Deletes a guarantee claim associated with a specific guarantee record by their respective identifiers.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the claim belongs
     * @param guaranteeClaimId the unique identifier of the guarantee claim to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID guaranteeRecordId, UUID guaranteeClaimId);
}

