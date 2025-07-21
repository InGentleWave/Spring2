package kr.or.ddit.controller.chapt09.item02.service;

import java.util.List;

import kr.or.ddit.vo.Item2;

public interface IItemService2 {

	public void register(Item2 item);

	public List<Item2> list();

	public Item2 read(int itemId);

	public List<String> getAttach(int itemId);

	public void modify(Item2 item);

	public void remove(int itemId);
	
}
