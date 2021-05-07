package com.halink.scaffold.core.mapstruct;

import com.halink.scaffold.common.entity.User;
import com.halink.scaffold.common.vo.user.UserVo;
import com.halink.scaffold.core.mapstruct.converter.CustomConverter;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = CustomConverter.class
)
public interface UserTransformMapper {

    UserVo toUserVO(User user);
}
