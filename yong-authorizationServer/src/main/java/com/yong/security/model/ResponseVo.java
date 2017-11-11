package com.yong.security.model;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author  LiangYong
 * @createdDate 2017/10/6.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVo {

    private int code;

    private String message;

    private Object data;

    /**
     * 正常返回
     */
    public static ResponseVo success(Object object) {
        return new ResponseVo(0, "success", object);
    }
    public static ResponseVo success(String string) {
        return new ResponseVo(0, string,null);
    }

    /**
     * 错误返回
     */
    public static ResponseVo error(Exception exception) {
        return new ResponseVo(-1,
                Throwables.getRootCause(exception).getMessage(),
        ""
        );
    }
    public static ResponseVo error(String string) {
        return new ResponseVo(-1, string, null);
    }
    public static ResponseVo error(String string,Object object) {
        return new ResponseVo(-1, string, object);
    }
}