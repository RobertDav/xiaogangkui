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
public class AddressCheckResult {
    // true:通过校验；false：未通过校验
    private boolean postCodeResult = false;
}
