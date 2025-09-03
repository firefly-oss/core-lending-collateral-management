package com.firefly.core.lending.collateral.core.services.guarantee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeRecordDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GuaranteeRecordService {

    /**
     * Retrieves all guarantee records based on the provided filtering criteria.
     *
     * @param filterRequest the filtering criteria and pagination information used to query guarantee records
     * @return a Mono emitting a paginated response containing a list of GuaranteeRecordDTO objects that match the filtering criteria
     */
    Mono<PaginationResponse<GuaranteeRecordDTO>> findAll(FilterRequest<GuaranteeRecordDTO> filterRequest);

    /**
     * Creates a new guarantee record using the details provided in the GuaranteeRecordDTO object.
     *
     * @param dto the GuaranteeRecordDTO containing the details of the guarantee record to be created
     * @return a Mono emitting the created GuaranteeRecordDTO object
     */
    Mono<GuaranteeRecordDTO> create(GuaranteeRecordDTO dto);

    /**
     * Retrieves a guarantee record by its unique identifier.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to be retrieved
     * @return a Mono emitting the GuaranteeRecordDTO corresponding to the specified ID, or an empty Mono if no record is found
     */
    Mono<GuaranteeRecordDTO> getById(UUID guaranteeRecordId);

    /**
     * Updates an existing guarantee record with the provided details.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to be updated
     * @param dto the data transfer object containing the updated details of the guarantee record
     * @return a Mono emitting the updated GuaranteeRecordDTO object
     */
    Mono<GuaranteeRecordDTO> update(UUID guaranteeRecordId, GuaranteeRecordDTO dto);

    /**
     * Deletes a guarantee record identified by its unique identifier.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to be deleted
     * @return a Mono signaling when the deletion operation is completed
     */
    Mono<Void> delete(UUID guaranteeRecordId);
}