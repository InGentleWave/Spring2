package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Member {
	private String userId = "a001";
	private String userName = "hongkd";
	private String password = "1234";
	private int coin = 100;
	private Date dateOfBirth;
	
	private String email;
	private String gender;
	private boolean foreigner;
	private String developer;
	private String nationality;
	
	private String hobby;
	private String[] hobbyArray;
	private List<String> hobbyList;
	
	private String cars;
	private String[] carArray;
	private List<String> carList;
	
	private List<Card> cardList;
	private Address address;
	
	private String introduction;
	private String birthDay;
}
