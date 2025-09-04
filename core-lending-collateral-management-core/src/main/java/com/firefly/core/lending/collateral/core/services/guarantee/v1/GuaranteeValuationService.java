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


package com.firefly.core.lending.collateral.core.services.guarantee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeValuationDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GuaranteeValuationService {

    /**
     * Retrieves all guarantee valuations associated with a specific guarantee record,
     * based on the provided filtering and pagination criteria.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record for which valuations are being retrieved
     * @param filterRequest the filtering criteria and pagination information used to query the guarantee valuations
     * @return a Mono emitting a paginated response containing a list of GuaranteeValuationDTO objects
     *         that match the provided filtering and pagination criteria
     */
    Mono<PaginationResponse<GuaranteeValuationDTO>> findAll(UUID guaranteeRecordId, FilterRequest<GuaranteeValuationDTO> filterRequest);

    /**
     * Creates a new guarantee valuation for the specified guarantee record.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the valuation belongs
     * @param dto the GuaranteeValuationDTO object containing the details of the guarantee valuation to be created
     * @return a Mono emitting the created GuaranteeValuationDTO object
     */
    Mono<GuaranteeValuationDTO> create(UUID guaranteeRecordId, GuaranteeValuationDTO dto);

    /**
     * Retrieves a guarantee valuation identified by its unique identifiers.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the valuation is associated
     * @param guaranteeValuationId the unique identifier of the guarantee valuation to be retrieved
     * @return a Mono emitting the GuaranteeValuationDTO containing the details of the guarantee valuation,
     *         or an empty Mono if the valuation is not found
     */
    Mono<GuaranteeValuationDTO> getById(UUID guaranteeRecordId, UUID guaranteeValuationId);

    /**
     * Updates an existing guarantee valuation record with the specified details.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the valuation belongs
     * @param guaranteeValuationId the unique identifier of the guarantee valuation to be updated
     * @param dto the data transfer object containing the updated details of the guarantee valuation
     * @return a Mono emitting the updated GuaranteeValuationDTO object
     */
    Mono<GuaranteeValuationDTO> update(UUID guaranteeRecordId, UUID guaranteeValuationId, GuaranteeValuationDTO dto);

    /**
     * Deletes a guarantee valuation associated with the specified guarantee record and valuation IDs.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the valuation belongs
     * @param guaranteeValuationId the unique identifier of the guarantee valuation to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID guaranteeRecordId, UUID guaranteeValuationId);
}
