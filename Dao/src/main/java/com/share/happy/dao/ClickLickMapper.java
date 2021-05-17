package com.share.happy.dao;

import com.share.happy.model.ClickLick;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClickLickMapper {

    @Insert("INSERT INTO `clicklick` (`lickuid`, `lickartid`, `createTime`, `updateTime`) VALUES (#{lickuid}, #{lickartid}, #{createTime}, #{updateTime}')")
    int clickLick(ClickLick clickLick);

    @Select("SELECT * FROM `clicklick` WHERE lickuid=#{uid}")
    List<ClickLick> myClickList(String uid);

    @Select("SELECT * FROM `clicklick` WHERE lickartid=#{artId}")
    List<ClickLick> artClick(String artId);

    @Select("SELECT * FROM `clicklick` WHERE lickartid=#{aid} and lickuid=#{uid}")
    ClickLick isLick(@Param("uid")  String uid, @Param("aid") String aid);

    @Delete("DELETE FROM  `clicklick` WHERE `lickid` = #{clickId}; ")
    int CancelClick(@Param("uid")  String uid, @Param("aid") String aid);


}
