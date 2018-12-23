package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by chenxiaobian on 16/7/4.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultMap implements Serializable {
    public static int SUCCESS_CODE = 200;
    public static int ERROR_CODE = 500;
    private int code;
    private String msg;
    private Object data;

    public static ResultMap generate(int code) {
        return ResultMap.builder().code(code).build();
    }


    public static ResultMap generate(int code, String msg) {
        return ResultMap.builder().code(code).msg(msg).build();
    }

    public static ResultMap generate(int code, String msg, Object data) {
        return ResultMap.builder().code(code).msg(msg).data(data).build();
    }

    public static ResultMap generate(String msg) {
        return ResultMap.builder().code(SUCCESS_CODE).msg(msg).build();
    }

    public static ResultMap generateDataResultMap(Object data) {
        return ResultMap.builder().code(SUCCESS_CODE).msg("").data(data).build();
    }

    public static ResultMap success() {
        return ResultMap.generate(SUCCESS_CODE);
    }

    public static ResultMap fail() {
        return ResultMap.generate(ERROR_CODE);
    }
}
