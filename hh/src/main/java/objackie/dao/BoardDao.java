package objackie.dao;

import java.util.List;
import java.util.Map;

import objackie.vo.Board;

public interface BoardDao {
  List<Board> selectList(Map<String,Object> paramMap) throws Exception;
  Board selectOne(int no) throws Exception;
  int insert(Board board) throws Exception;
  int update(Board board) throws Exception;
  int delete(int no) throws Exception;
}








