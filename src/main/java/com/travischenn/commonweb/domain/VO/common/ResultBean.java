package com.travischenn.commonweb.domain.VO.common;

import com.travischenn.commonweb.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean<T> {

    /** 状态码 */
    private Integer code;

    /** 消息 */
    private String msg;

    /** 列表 */
    private T data;

    public ResultBean (ResultEnum resultEnum , T data){
        this.code = Integer.valueOf(resultEnum.getCode());
        this.msg = resultEnum.getDescribe();
        this.data = data;
    }

}
