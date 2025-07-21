package kr.or.ddit.controller.chapt09.item01.service;

import java.util.List;

import kr.or.ddit.vo.Item;

public interface IItemService {

	public void register(Item item);

	public List<Item> list();

	public Item read(int itemId);

	public String getPicture(int itemId);

	public void modify(Item item);

	public void remove(int itemId);

}
