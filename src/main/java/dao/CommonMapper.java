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
}