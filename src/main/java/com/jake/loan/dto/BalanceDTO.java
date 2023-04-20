package com.jake.loan.dto;

import com.jake.loan.constant.RepaymentType;
import lombok.*;

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
    public static class RepaymentRequest {
        private Long applicationId;
        private RepaymentType type;
        private BigDecimal repaymentAmount;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long balanceId;
        private Long applicationId;
        private BigDecimal balance;
    }
}
