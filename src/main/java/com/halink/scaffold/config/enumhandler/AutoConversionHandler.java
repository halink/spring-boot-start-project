package com.halink.scaffold.config.enumhandler;

import com.halink.scaffold.common.enumerate.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自动枚举转换
 *
 * @author halink
 * @date 2020/9/28 2:15 下午
 */
public class AutoConversionHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private final BaseTypeHandler<E> typeHandler;

    public AutoConversionHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        if (BaseEnum.class.isAssignableFrom(type)) {
            // 如果实现了 BaseCodeEnum 则使用我们自定义的转换器
            typeHandler = new CustomEnumTypeHandler(type);
        } else {
            // 默认转换器 也可换成 EnumOrdinalTypeHandler
            typeHandler = new EnumTypeHandler<>(type);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        typeHandler.setNonNullParameter(ps, i, parameter, jdbcType);
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return typeHandler.getNullableResult(rs, columnName);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return typeHandler.getNullableResult(rs, columnIndex);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return typeHandler.getNullableResult(cs, columnIndex);
    }
}
