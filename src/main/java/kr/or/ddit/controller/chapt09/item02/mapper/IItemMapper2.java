package kr.or.ddit.controller.chapt09.item02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.Item2;

@Mapper
public interface IItemMapper2 {

	public void create(Item2 item);

	public void addAttach(String fileName);

	public List<Item2> list();

	public Item2 read(int itemId);

	public List<String> getAttach(int itemId);

	public void modify(Item2 item);

	public void deleteAttach(int itemId);

	public void replaceAttach(@Param("fileName")String fileName,@Param("itemId") int itemId);

	public void delete(int itemId);

}
