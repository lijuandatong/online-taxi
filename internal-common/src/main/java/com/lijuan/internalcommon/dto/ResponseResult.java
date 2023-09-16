package com.lijuan.internalcommon.dto;

import com.lijuan.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 响应成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getMessage())
                .setData(data);
    }

    /**
     * 响应成功，data为空
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getMessage())
                .setData("");
    }

    /**
     * 响应失败，错误码和提示信息自定义
     * @return
     */
    public static ResponseResult fail(int code, String message){
        return new ResponseResult().setCode(code)
                .setMessage(message);
    }

    /**
     * 响应失败，错误码、提示信息、具体错误信息
     * @return
     */
    public static ResponseResult fail(int code, String message, String data){
        return new ResponseResult().setCode(code)
                .setMessage(message)
                .setData(data);
    }

    /**
     * 默认响应失败
     * @return
     */
    public static ResponseResult fail(){
        return new ResponseResult().setCode(CommonStatusEnum.FAIL.getCode())
                .setMessage(CommonStatusEnum.FAIL.getMessage());
    }

    /**
     * 响应失败，错误码和提示信息自定义
     * @return
     */
    public static ResponseResult fail(String message){
        return new ResponseResult().setCode(CommonStatusEnum.FAIL.getCode())
                .setMessage(message);
    }
}
