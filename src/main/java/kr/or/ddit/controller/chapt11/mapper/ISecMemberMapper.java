package kr.or.ddit.controller.chapt11.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.crud.CrudMember;

@Mapper
public interface ISecMemberMapper {

	public CrudMember readByUserInfo(String username);
}
