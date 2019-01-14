package com.xiaogangkui.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by luchunyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String postcode;

    private String street;

    private String state;
}
