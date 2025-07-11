<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	th {
		text-align:center;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<!-- 
		문제01) 회원가입 양식을 만들고 서버로 전송해주세요.
			항 목		|	name							|		value
		───────────────────────────────────────────────────────────────────────────
			아이디	|	userId 							|
			비밀번호	|	password  						|
			이름		|	userName 						|
			이메일	|	eamil 							|
			생년월일	|	dateOfBirth 					|
			성별		|	gender							|	남자(male), 여자(female)
		개발자 여부	|	developer						|	개발자(Y), 비개발자(null)
		외국인 여부	|	foreigner						|	외국인(true), 내국인(false)
			국적		|	nationality						|	대한민국(korea),독일(germany),캐나다(canada),미국(usa)
			소유차량	|	cars, carArray, carList			|	BMW,AUDI,VOLVO,JEEP
			취미		|	hobby, hobbyArray, hobbyList	|	운동(sports),독서(book),영화감상(movie),음악감상(music)
			우편번호	|	postCode						|
			주소		|	location						|
		카드1-번호		|	no								|
		카드1-유효년월	|	validMonth						|	날짜 데이터
		카드2-번호		|	no								|
		카드2-유효년월	|	validMonth						|	날짜 데이터
			소개		|	introduction					|
		───────────────────────────────────────────────────────────────────────────
		** 사용 변수 및 타입은 자유
		문제02) 입력한 데이터를 '/chapt05/test/result'로 전송해주세요. (result.jsp는 'chapt05/test/result.jsp')
	 -->
	 <h1>Register All Form	</h1>
	 <form action="/chapt05/test/result" method="post" id="mform">
		 <table class="table table-bordered">
		 	<tbody>	
			 	<tr>
			 		<th>유저 ID</th>
			 		<td>
						<input type="text" name="userId" required>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>패스워드</th>
			 		<td>
						<input type="text" name="password" required>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>이름</th>
			 		<td>
						<input type="text" name="userName" required>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>E-mail</th>
			 		<td>
						<input type="text" name="email" required placeholder="sample@sample.com">
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>생년월일</th>
			 		<td>
						<input type="text" name="dateOfBirthStr" required placeholder="예시 : 2025/07/07">
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>성별</th>
			 		<td>
						<input type="radio" name="gender" value="male" checked> Male
						<input type="radio" name="gender" value="female" > Female
						<input type="radio" name="gender" value="other" > Other
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>개발자 여부</th>
			 		<td>
						<input type="checkbox" name="developer"  value="Y"> 개발자
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>외국인 여부</th>
			 		<td>
						<input type="checkbox" name="foreigner"  > 외국인
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>국적</th>
			 		<td>
						<select name="nationality">
							<option value="korea" selected>대한민국</option>
							<option value="germany">독일</option>
							<option value="canada" >캐나다</option>
							<option value="usa" >미국</option>
						</select>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>소유차량</th>
			 		<td>
						<select name="carList" multiple="multiple">
							<option value="JEEP">JEEP</option>
							<option value="BMW">BMW</option>
							<option value="AUDI">AUDI</option>
							<option value="VOLVO">VOLVO</option>
						</select>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>취미</th>
			 		<td>
						<input type="checkbox" name="hobbyList"  value="sports"> Sports
						<input type="checkbox" name="hobbyList"  value="music"> Music
						<input type="checkbox" name="hobbyList"  value="movie"> Movie
						<input type="checkbox" name="hobbyList"  value="book"> Book
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>우편번호</th>
			 		<td>
						<input type="text" name="address.postCode" required>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>주소</th>
			 		<td>
						<input type="text" name="address.location" required>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>카드1 - 번호</th>
			 		<td>
						<input type="text" name="no1" id="cardNo1" >
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>카드1 - 유효년월</th>
			 		<td>
						<input type="text" name="validMonth1" id="cardVal1" placeholder="예시 : 2025/07/07" >
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>카드2 - 번호</th>
			 		<td>
						<input type="text" name="no2" id="cardNo2" >
			 		</td>	
			 	</tr>
			 	<tr>
			 		<th>카드2 - 유효년월</th>
			 		<td>
						<input type="text" name="validMonth2" id="cardVal2" placeholder="예시 : 2025/07/07">
			 		</td>
			 	</tr>
			 	<tr>
			 		<th>소개</th>
			 		<td>
						<textarea name="introduction" required></textarea>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td colspan="2">
						<button type="submit" id="btn">등록</button>
						<button type="reset">리셋</button>
			 		</td>
			 	</tr>
		 	</tbody>
		 </table>
	 </form>
</body>
<script type="text/javascript">
	$(function(){
		let btn = $("#btn");
		let mform = $("#mform");
		
		btn.on("click",function(event){
			event.prevendDefault();
// 			mform.submit();
			mform.requestSubmit();
		})
	})
</script>
</html>