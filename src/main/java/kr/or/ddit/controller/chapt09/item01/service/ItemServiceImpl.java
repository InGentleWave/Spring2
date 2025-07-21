package kr.or.ddit.controller.chapt09.item01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.controller.chapt09.item01.mapper.IItemMapper;
import kr.or.ddit.vo.Item;

@Service
public class ItemServiceImpl implements IItemService {
	
	@Autowired
	private IItemMapper mapper;
	
	@Override
	public void register(Item item) {
		mapper.create(item);
	}

	@Override
	public List<Item> list() {
		return mapper.list();
	}

	@Override
	public Item read(int itemId) {
		return mapper.read(itemId);
	}

	@Override
	public String getPicture(int itemId) {
		return mapper.getPicture(itemId);
	}

	@Override
	public void modify(Item item) {
		mapper.modify(item);
	}

	@Override
	public void remove(int itemId) {
		mapper.remove(itemId);
	}

}
