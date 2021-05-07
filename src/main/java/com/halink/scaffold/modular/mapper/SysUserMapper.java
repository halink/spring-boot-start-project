package com.halink.scaffold.modular.mapper;

import com.halink.scaffold.common.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

public interface SysUserMapper {
    /**
     * delete by primary key
     *
     * @param userId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(SysUser record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(SysUser record);

    /**
     * select by primary key
     *
     * @param userId primary key
     * @return object by primary key
     */
    SysUser selectByPrimaryKey(Integer userId);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SysUser record);
}