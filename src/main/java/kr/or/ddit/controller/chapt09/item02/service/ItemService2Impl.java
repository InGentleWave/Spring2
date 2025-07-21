package kr.or.ddit.controller.chapt09.item02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.controller.chapt09.item02.mapper.IItemMapper2;
import kr.or.ddit.vo.Item2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemService2Impl implements IItemService2 {

	@Autowired
	private IItemMapper2 mapper;
	
	@Override
	public void register(Item2 item) {
		mapper.create(item);
		String[] files = item.getFiles();
		if(files == null ) {
			log.debug("files가 null");
			return;
		}
		
		for(String fileName : files) {
			log.debug("fileName"+fileName);
			mapper.addAttach(fileName);
		}
		
	}

	@Override
	public List<Item2> list() {
		return mapper.list();
	}

	@Override
	public Item2 read(int itemId) {
		return mapper.read(itemId);
	}

	@Override
	public List<String> getAttach(int itemId) {
		return mapper.getAttach(itemId);
	}

	@Override
	public void modify(Item2 item) {
		// 1. 일반 데이터를 수정
		mapper.modify(item);
		// 2. itemId와 일치하는 기존 파일들을 삭제
		int itemId = item.getItemId();
		mapper.deleteAttach(itemId);
		
		String[] files = item.getFiles();
		if(files == null) {
			return;
		}
		// 3. 새롭게 추가된 파일들을 다시 add
		for(String fileName : files) {
			mapper.replaceAttach(fileName,itemId);
		}
	}

	@Override
	public void remove(int itemId) {
		mapper.deleteAttach(itemId);
		mapper.delete(itemId);
	}

}
