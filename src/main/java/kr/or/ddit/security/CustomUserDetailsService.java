package kr.or.ddit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.controller.chapt11.mapper.ISecMemberMapper;
import kr.or.ddit.vo.crud.CrudMember;
import kr.or.ddit.vo.security.CustomUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private ISecMemberMapper memberMapper;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("# CustomUserDetailsService.loadUserByUsername()실행!");
		log.info("# username : " + username);
		
		CrudMember member = memberMapper.readByUserInfo(username);
		
		return member == null ? null : new CustomUser(member);
	}

}
