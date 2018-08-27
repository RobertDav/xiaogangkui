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
public class Company extends BaseEntity{
    private String companyName;
}
