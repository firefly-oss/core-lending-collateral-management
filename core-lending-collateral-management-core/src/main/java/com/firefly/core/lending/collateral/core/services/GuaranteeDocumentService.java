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
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteeDocumentDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GuaranteeDocumentService {

    /**
     * Retrieves a paginated list of guarantee documents associated with a specific guarantee record,
     * based on the provided filtering and pagination criteria.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record.
     * @param filterRequest the filtering criteria and pagination information used to query the guarantee documents.
     * @return a Mono emitting a paginated response containing a list of GuaranteeDocumentDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<GuaranteeDocumentDTO>> findAll(UUID guaranteeRecordId, FilterRequest<GuaranteeDocumentDTO> filterRequest);

    /**
     * Creates a new guarantee document for the specified guarantee record.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the document belongs
     * @param dto the GuaranteeDocumentDTO object containing the details of the guarantee document to be created
     * @return a Mono emitting the created GuaranteeDocumentDTO object
     */
    Mono<GuaranteeDocumentDTO> create(UUID guaranteeRecordId, GuaranteeDocumentDTO dto);

    /**
     * Retrieves a guarantee document's details by its unique identifier within a specified guarantee record.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the document belongs
     * @param guaranteeDocumentId the unique identifier of the guarantee document to be retrieved
     * @return a Mono emitting the GuaranteeDocumentDTO containing the details of the guarantee document,
     *         or an empty Mono if the document is not found
     */
    Mono<GuaranteeDocumentDTO> getById(UUID guaranteeRecordId, UUID guaranteeDocumentId);

    /**
     * Updates an existing guarantee document identified by its guarantee record ID and guarantee document ID
     * with the provided updated details.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the document belongs
     * @param guaranteeDocumentId the unique identifier of the guarantee document to be updated
     * @param dto the data transfer object containing the updated details of the guarantee document
     * @return a Mono emitting the updated GuaranteeDocumentDTO object
     */
    Mono<GuaranteeDocumentDTO> update(UUID guaranteeRecordId, UUID guaranteeDocumentId, GuaranteeDocumentDTO dto);

    /**
     * Deletes a guarantee document associated with a specific guarantee record by their respective identifiers.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the document belongs
     * @param guaranteeDocumentId the unique identifier of the guarantee document to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID guaranteeRecordId, UUID guaranteeDocumentId);
}

