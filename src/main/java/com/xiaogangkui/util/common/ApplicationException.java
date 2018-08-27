package com.xiaogangkui.util.common;

/**
 * @author Created by luchunyu
 */
public class ApplicationException extends RuntimeException {
    private int code;
    public ApplicationException(){

    }

    public ApplicationException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public ApplicationException(String msg){
        super(msg);
    }

    public int getCode(){
        return code;
    }

}
