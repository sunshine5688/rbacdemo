package dao;

import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Mapper
@Resource
public interface CommonMapper {
    HashMap selectUser(HashMap map);
    List selectGroup();
    List selectRole();
    List selectMenu();
    Object insertRole(HashMap map);
    String selectRoleId(HashMap map);
    Object insertRole_Menu(HashMap map);
    String selectUserId(HashMap map);
    Object insertUser(HashMap map);
    Object insertUser_group(HashMap map);
    Object insertUser_role(HashMap map);
    List selectMenuUser(HashMap map);
}