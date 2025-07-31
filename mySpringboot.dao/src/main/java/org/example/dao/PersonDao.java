package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.vo.PersonVo;

import java.util.List;

public interface PersonDao {

    List<PersonVo> queryPersonInfoById(@Param("id")int id);

    List<PersonVo> queryPersonInfoByName(@Param("name") String name);

//    @Select("select id, name, phone_number as phoneNumber from user")
    List<PersonVo> queryAllPersonInfo();

}
