package objackie.dao;

import java.util.List;
import java.util.Map;

import objackie.vo.Build;

public interface BuildDao {
  List<Build> selectList(Map<String,Object> paramMap) throws Exception;
  Build selectOne(int buildNo) throws Exception;
  int insert(Build build) throws Exception;
  int update(Build build) throws Exception;
  int delete(int buildNo) throws Exception;

}
