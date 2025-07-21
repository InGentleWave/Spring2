package kr.or.ddit.controller.chapt09.item01.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.Item;

@Mapper
public interface IItemMapper {

	public void create(Item item);

	public List<Item> list();

	public Item read(int itemId);

	public String getPicture(int itemId);

	public void modify(Item item);

	public void remove(int itemId);

}
