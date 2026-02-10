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
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralDocumentDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CollateralDocumentService {

    /**
     * Retrieves a paginated list of collateral documents associated with a specific collateral case,
     * based on the provided filtering and pagination criteria.
     *
     * @param collateralCaseId the unique identifier of the collateral case.
     * @param filterRequest the filtering criteria and pagination information used to query the collateral documents.
     * @return a Mono emitting a paginated response containing a list of CollateralDocumentDTO objects
     *         that match the filtering criteria.
     */
    Mono<PaginationResponse<CollateralDocumentDTO>> findAll(UUID collateralCaseId, FilterRequest<CollateralDocumentDTO> filterRequest);

    /**
     * Creates a new collateral document for the specified collateral case.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the document belongs
     * @param dto the CollateralDocumentDTO object containing the details of the collateral document to be created
     * @return a Mono emitting the created CollateralDocumentDTO object
     */
    Mono<CollateralDocumentDTO> create(UUID collateralCaseId, CollateralDocumentDTO dto);

    /**
     * Retrieves a collateral document's details by its unique identifier within a specified collateral case.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the document belongs
     * @param collateralDocumentId the unique identifier of the collateral document to be retrieved
     * @return a Mono emitting the CollateralDocumentDTO containing the details of the collateral document,
     *         or an empty Mono if the document is not found
     */
    Mono<CollateralDocumentDTO> getById(UUID collateralCaseId, UUID collateralDocumentId);

    /**
     * Updates an existing collateral document identified by its collateral case ID and collateral document ID
     * with the provided updated details.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the document belongs
     * @param collateralDocumentId the unique identifier of the collateral document to be updated
     * @param dto the data transfer object containing the updated details of the collateral document
     * @return a Mono emitting the updated CollateralDocumentDTO object
     */
    Mono<CollateralDocumentDTO> update(UUID collateralCaseId, UUID collateralDocumentId, CollateralDocumentDTO dto);

    /**
     * Deletes a collateral document associated with a specific collateral case by their respective identifiers.
     *
     * @param collateralCaseId the unique identifier of the collateral case to which the document belongs
     * @param collateralDocumentId the unique identifier of the collateral document to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(UUID collateralCaseId, UUID collateralDocumentId);
}

