package com.example.service.party;

import com.example.api.ApiResult;
import com.example.domain.party.Party;
import com.example.dto.request.CreatePartyRequest;
import com.example.repository.party.PartyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PartyService {
    private final PartyRepository partyRepository;

    @Transactional
    public ApiResult<?> createParty(CreatePartyRequest createPartyRequest){

        Party joinParty = createPartyRequest.toEntity();
        partyRepository.save(joinParty);

        return ApiResult.success("파티 생성이 성공적으로 처리되었습니다.");
    }
}