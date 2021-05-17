package com.share.happy.common.model.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseResults<T> extends ResponseResult {
    T data;

    public ResponseResults(ResultCode resultCode,T data){
        super(resultCode);
        this.data = data;
    }
}
