package com.halink.scaffold.common.vo;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class ExceptionResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer errorCode;
    private String errorMessage;
}
