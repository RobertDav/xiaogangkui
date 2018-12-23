package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuzzySearchDto {
    private int id;
    private int status;
    private int applyerId;
    private int applyer;
    private int customerId;
    private int applyStatus;
    private int userId;
    private Date startTime;
    private Date endTime;
}
