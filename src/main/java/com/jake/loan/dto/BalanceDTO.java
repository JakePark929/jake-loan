package com.jake.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceDTO implements Serializable {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long applicationId;
        private BigDecimal entryAmount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private Long applicationId;
        private BigDecimal beforeEntryAmount;
        private BigDecimal afterEntryAmount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long balanceId;
        private Long applicationId;
        private BigDecimal balance;
    }
}
