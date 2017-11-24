package com.yong.orders.common;


import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.google.common.base.Preconditions.checkArgument;
import static com.yong.orders.common.ResultCode.FAIL;
import static com.yong.orders.common.ResultCode.SUCCESS;

/**
 * Created by yong.a.liang on 6/22/2017.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result {

    private ResultCode status = SUCCESS;
    private String message;
    private Object payload;

    public static Result success() {
        return new Result();
    }

    public static  Result success(Object payload) {
        checkArgument(payload != null, "payload should be not null");
        return new Result(SUCCESS, null, payload);
    }

    public static Result fail(ResultCode code, String message) {
        checkArgument(!Strings.isNullOrEmpty(message), "message should be not empty");
        return new Result(FAIL, message, null);
    }
    
}
