package com.share.happy.common.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */
@Data
@ToString
public class QueryResults<T> extends ResponseResult {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
    public QueryResults(ResultCode resultCode,List<T> list){
        super(resultCode);
        this.list=list;
    }

}
