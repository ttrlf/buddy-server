package com.example.dto.request;

import com.example.domain.party.Party;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePartyRequest {

    private String leaderId;
    private String leaderPwd;
    private Integer recLimit;
    private Date startDate;
    private Integer durationMonth;
    private Date endDate;

    public Party toEntity(){
        return Party.builder()
                .leaderId(this.leaderId)
                .leaderPwd(this.leaderPwd)
                .recLimit(this.recLimit)
                .startDate(this.startDate)
                .durationMonth(this.durationMonth)
                .endDate(this.endDate)
                .build();
    }
}
