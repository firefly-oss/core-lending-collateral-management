package com.firefly.core.lending.collateral.core.services.guarantee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteePartyDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GuaranteePartyService {

    /**
     * Retrieves a paginated list of GuaranteePartyDTO objects associated with a specific guarantee record,
     * based on the provided filtering criteria.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record for which the parties are being retrieved
     * @param filterRequest the filtering criteria and pagination information used to query the guarantee parties
     * @return a Mono emitting a paginated response containing a list of GuaranteePartyDTO objects
     *         that match the provided filtering criteria
     */
    Mono<PaginationResponse<GuaranteePartyDTO>> findAll(UUID guaranteeRecordId, FilterRequest<GuaranteePartyDTO> filterRequest);

    /**
     * Creates a new guarantee party associated with a specific guarantee record.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the party is associated
     * @param dto the GuaranteePartyDTO object containing the details of the guarantee party to be created
     * @return a Mono emitting the created GuaranteePartyDTO object
     */
    Mono<GuaranteePartyDTO> create(UUID guaranteeRecordId, GuaranteePartyDTO dto);

    /**
     * Retrieves a specific guarantee party associated with the given guarantee record and guarantee party IDs.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the guarantee party belongs
     * @param guaranteePartyId the unique identifier of the guarantee party to be retrieved
     * @return a Mono emitting the GuaranteePartyDTO containing the details of the guarantee party,
     *         or an empty Mono if the guarantee party is not found
     */
    Mono<GuaranteePartyDTO> getById(UUID guaranteeRecordId, UUID guaranteePartyId);

    /**
     * Updates an existing GuaranteeParty record with the provided details.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the guarantee party belongs
     * @param guaranteePartyId the unique identifier of the guarantee party to be updated
     * @param dto the data transfer object containing the updated details of the guarantee party
     * @return a Mono emitting the updated GuaranteePartyDTO object
     */
    Mono<GuaranteePartyDTO> update(UUID guaranteeRecordId, UUID guaranteePartyId, GuaranteePartyDTO dto);

    /**
     * Deletes a guarantee party associated with the specified guarantee record and party identifiers.
     *
     * @param guaranteeRecordId the unique identifier of the guarantee record to which the party belongs
     * @param guaranteePartyId the unique identifier of the guarantee party to be deleted
     * @return a Mono signaling when the deletion operation is completed
     */
    Mono<Void> delete(UUID guaranteeRecordId, UUID guaranteePartyId);
}