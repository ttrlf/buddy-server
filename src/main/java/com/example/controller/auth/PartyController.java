package com.example.controller.auth;

import com.example.api.ApiResult;
import com.example.dto.request.CreatePartyRequest;
import com.example.service.auth.PartyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "파티 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class PartyController {

    private final PartyService partyService;

    /*
    파티 생성하기
     */
    @PostMapping("/party")
    @Operation(summary = "파티 생성하기", description = "")
    public ApiResult<?> createParty(@Valid @RequestBody CreatePartyRequest createPartyRequest){

        return partyService.createParty(createPartyRequest);
    }
}