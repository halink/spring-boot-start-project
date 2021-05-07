package com.halink.scaffold.common.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("测试参数类")
public class TestParam {

    @ApiModelProperty(value = "姓名")
    private String name;
}
