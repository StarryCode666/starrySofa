package com.starry.commonService.handler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Satrry
 * @Description TODO
 * @Date 2022/2/22 10:19
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarryException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;
}
