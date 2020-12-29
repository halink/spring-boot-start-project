package com.halink.scaffold.config.enumhandler;

import com.halink.scaffold.common.enumerate.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义枚举转换
 *
 * @author halink
 */
public class CustomEnumTypeHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {

    private final Class<E> aClass;

    public CustomEnumTypeHandler(Class<E> aClass) {
        if (aClass == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.aClass = aClass;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return codeOf(value);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return codeOf(value);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return codeOf(value);
        }
    }

    private E codeOf(int value) {
        try {
            for (E enumConstant : aClass.getEnumConstants()) {
                if (enumConstant.getValue() == value) {
                    return enumConstant;
                }
            }
            return null;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + aClass.getSimpleName() + " by ordinal value.",
                    ex);
        }
    }
}
