package com.xiaogangkui.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendance extends BaseEntity {
    private int customerId;
    private int userId;
    private String userName;
    private int type;
    private String address;
    private String longitude;
    private String latitude;
}
