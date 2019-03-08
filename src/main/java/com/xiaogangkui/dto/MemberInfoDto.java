package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDto {

    private int id;
    private String memberName;
    private String memberLevel;
    private int gender;
}
