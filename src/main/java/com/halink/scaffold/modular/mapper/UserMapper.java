package com.halink.scaffold.modular.mapper;

import com.halink.scaffold.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
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
    int insert(User record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(User record);

    /**
     * select by primary key
     *
     * @param userId primary key
     * @return object by primary key
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return object by username
     */
    @Select({
            "select user_id,username,`password`,nick_name,area_code,phone,photo,gender,birthday,created_at,updated_at,last_login_at ",
            "from p_user ",
            "where username = #{username} "
    })
    User selectByUsername(@Param("username") String username);
}