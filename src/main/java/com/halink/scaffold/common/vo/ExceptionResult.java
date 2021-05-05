package com.halink.scaffold.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 特定响应格式
 *
 * @author Halink
 * @date 2021-05-05 21:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResult {
    private Integer errorCode;
    private String errorMessage;
}
