package com.halink.scaffold.core.mapstruct;

import com.halink.scaffold.core.mapstruct.converter.CustomConverter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Slf4j
@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = CustomConverter.class
)
public class UserTransformMapper {

}
