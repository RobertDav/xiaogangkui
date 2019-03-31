package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto implements Serializable {
    private double bail;
    private int bidCount;
    private double partBail;
    private double partBiAmount;
    private double partBackBail;
    private int partHitBidCount;
    private int partBidCount;
    private int partMakeBidCount;
    private int partCount;


    private int contractTotal;
    private int contractMonthAdd;

    private int partContractTotal;
    private double partAmount;

    private int partCollectContractTotal;
    private double partCollectAmount;

    private int partPayContractTotal;
    private double partPayAmount;

    private double partCollectRecordAmount;

    private double partPayRecordAmount;

    private double payAmount;
}
