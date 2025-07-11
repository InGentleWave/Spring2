<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>registerForm</h1>
	<hr/>
	
	<h4>1. 컨트롤러 메서드 매개변수</h4>
	<hr/>
	
	<p>1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.</p>	
	<a href="/chapt05/register?userId=hongkd&password=1234">요청(쿼리파라미터)</a>
	
	<p>2) HTML Form 필드명과 컨트롤러 매개변수 명이 일치하면 요청 데이터를 취득할 수 있다.</p>	
	<form action="/chapt05/register01" method="post">
		userId : <input type="text" name="userId"/><br/>
		password : <input type="text" name="password"/><br/>
		coin : <input type="text" name="coin"/><br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<h4>2. 요청 데이터 처리 어노테이션</h4>
	<hr/>
	
	<p>1) URL 경로 상의 변수가 여러 개일 때, @PathVariable 어노테이션을 사용하여 특정한 경로 변수명을 지정해준다.</p>	
	<a href="/chapt05/register/hongkd/100">요청(경로 상의 포함된 파라미터)</a>
	
	<p>2) @RequestParam 어노테이션을 사용하여 특정한 HTML Form의 필드명을 지정하여 요청을 처리한다.</p>	
	<form action="/chapt05/register0201" method="post">
		userId : <input type="text" name="userId"/><br/>
		password : <input type="text" name="password"/><br/>
		coin : <input type="text" name="coin"/><br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<h4>3. 요청 처리 자바 빈즈</h4>
	<hr/>
	
	<p>1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.</p>	
	<form action="/chapt05/beans/register01" method="post">
		userId : <input type="text" name="userId"/><br/>
		password : <input type="text" name="password"/><br/>
		coin : <input type="text" name="coin"/><br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수와 기본 데이터 타입인 정수 타입 매개변수로 처리한다.</p>	
	<form action="/chapt05/beans/register02" method="post">
		userId : <input type="text" name="userId"/><br/>
		password : <input type="text" name="password"/><br/>
		coin : <input type="text" name="coin"/><br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<h4>4. Date 타입 처리</h4>
	<hr/>
	
	<p>1) 쿼리 파라미터(dateOfBirth=1234)로 전달받은 값은 Date 타입으로 데이터를 받을 수 있는가?</p>	
	<a href="/chapt05/registerByGet01?dateOfBirth=1234">요청01</a>
	
	<p>2) 쿼리 파라미터(dateOfBirth=2025-07-04)로 전달받은 값은 Date 타입으로 데이터를 받을 수 있는가?</p>	
	<a href="/chapt05/registerByGet01?dateOfBirth=2025-07-04">요청02</a>
	
	<p>3) 쿼리 파라미터(dateOfBirth=20250704)로 전달받은 값은 Date 타입으로 데이터를 받을 수 있는가?</p>	
	<a href="/chapt05/registerByGet01?dateOfBirth=20250704">요청03</a>
	
	<p>4) 쿼리 파라미터(dateOfBirth=2025/07/04)로 전달받은 값은 Date 타입으로 데이터를 받을 수 있는가?</p>	
	<a href="/chapt05/registerByGet01?dateOfBirth=2025/07/04">요청04</a>
	
	<p>5) 쿼리 파라미터(dateOfBirth)로 전달받은 값은 Member 객체를 이용한 Date 타입의 데이터를 받을 수 있는가?</p>	
	<form action="/chapt05/registerByGet02" method="post">
		dateOfBirth : <input type="text" name="dateOfBirth"/><br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<h4>5. @DateTimeFormat 어노테이션</h4>
	<hr/>
	
	<p>1) Member 매개변수와 폼 방식 요청 전달받은 값이 날짜 문자열 형식으로 설정 시, Date 타입으로 받을 수 있는가?</p>	
	<form action="/chapt05/registerByGet03" method="post">
		dateOfBirth : <input type="text" name="dateOfBirth"/><br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<h4>6. 폼 방식 요청 처리</h4>
	<hr/>
	
	<p>1) 폼 텍스트 필드 요소값을 기본 자바빈즈 매개변수로 처리한다.</p>
	<form action="/chapt05/registerMemberUserId" method="post">
		userId : <input type="text" name="userId"/><br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>2) 폼 비밀번호 필드 요소값을 기본 자바빈즈 매개변수로 처리한다.</p>
	<form action="/chapt05/registerPassword" method="post">
		password : <input type="password" name="password"/><br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>3) 폼 라디오 버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerRadio" method="post">
		gender : <br>
		<input type="radio" name="gender" value="male" checked="checked"/>Male<br/>
		<input type="radio" name="gender" value="female" />Female<br/>
		<input type="radio" name="gender" value="other" />Other<br/>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>4) 폼 셀렉트 버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerSelect" method="post">
		nationallity : <br>
		<select name="nationallity" >
			<option value="korea">대한민국</option>
			<option value="germany">독일</option>
			<option value="austrailia">호주</option>
			<option value="canada">캐나다</option>
			<option value="usa">미국</option>
		</select>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>5) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerMultiSelect01" method="post">
		cars : <br>
		<select name="cars" multiple="multiple" >
			<option value="bmw">BMW</option>
			<option value="audi">AUDI</option>
			<option value="bolvo">BOLVO</option>
		</select>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>6) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerMultiSelect02" method="post">
		carArray : <br>
		<select name="carArray" multiple="multiple" >
			<option value="bmw">BMW</option>
			<option value="audi">AUDI</option>
			<option value="bolvo">BOLVO</option>
		</select>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerMultiSelect03" method="post">
		carList : <br>
		<select name="carList" multiple="multiple" >
			<option value="bmw">BMW</option>
			<option value="audi">AUDI</option>
			<option value="bolvo">BOLVO</option>
		</select>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>8) 폼 체크박스 요소값을 문자열 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerCheckbox01" method="post">
		hobby : <br>
			<input type="checkbox" name="hobby" value="sports"/>Sports<br>
			<input type="checkbox" name="hobby" value="music"/>Music<br>
			<input type="checkbox" name="hobby" value="movie"/>Movie<br>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>9) 폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerCheckbox02" method="post">
		hobbyArray : <br>
			<input type="checkbox" name="hobbyArray" value="sports"/>Sports<br>
			<input type="checkbox" name="hobbyArray" value="music"/>Music<br>
			<input type="checkbox" name="hobbyArray" value="movie"/>Movie<br>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>10) 폼 체크박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerCheckbox03" method="post">
		hobbyList : <br>
			<input type="checkbox" name="hobbyList" value="sports"/>Sports<br>
			<input type="checkbox" name="hobbyList" value="music"/>Music<br>
			<input type="checkbox" name="hobbyList" value="movie"/>Movie<br>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>11) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerCheckbox04" method="post">
		developer : <br>
			<input type="checkbox" name="developer" value="Y"/>개발자 여부<br>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>12) 폼 체크박스 요소값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerCheckbox05" method="post">
		foreigner : <br>
			<input type="checkbox" name="foreigner" value="true"/>외국인 여부<br>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>13) 폼 텍스트 필드 요소값을 중첩된 자바 빈즈 매개변수로 처리한다.</p>
	<form action="/chapt05/registerUserAddress" method="post">
		postCode : <input type="text" name="address.postCode" /><br>
		location : <input type="text" name="address.location" /><br>
		<input type="submit" value="요청"	/>
	</form>
	
	<p>14) 폼 텍스트 필드 요소값을 중첩된 자바 빈즈 매개변수로 처리한다.</p>
	<form action="/chapt05/registerUserCardList" method="post">
		카드1 - 번호 : <input type="text" name="cardList[0].no" /><br>
		카드1 - 유효년월 : <input type="text" name="cardList[0].validMonth" /><br>
		카드2 - 번호 : <input type="text" name="cardList[1].no" /><br>
		카드2 - 유효년월 : <input type="text" name="cardList[1].validMonth" /><br>
		<input type="submit" value="요청"	/>
	</form>
	
	<h4>8. 파일 업로드 폼 방식 요청 처리</h4>
	<hr/>
	
	<p>1) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.</p>	
	<form action="/chapt05/registerFile01" method="post" enctype="multipart/form-data">
		userId : <input type="text" name="userId"/><br/>
		password : <input type="text" name="password"/><br/>
		<input type="file" name="picture"/><br/>
		<input type="submit" value="업로드"	/>
	</form>
	
	<p>2) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리한다.</p>
	<form action="/chapt05/registerFile02" method="post" enctype="multipart/form-data">
		userId : <input type="text" name="userId"/><br/>
		password : <input type="text" name="password"/><br/>
		<input type="file" name="picture"/><br/>
		<input type="submit" value="업로드"	/>
	</form>
	
	<p>3) 여러 개의 파일업로드를 폼 파일 요소값을 MultipartFile 타입의 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
	<form action="/chapt05/registerFile03" method="post" enctype="multipart/form-data">
		userId : <input type="text" name="userId"/><br/>
		password : <input type="text" name="password"/><br/>
		<input type="file" name="pictureList" /><br/>
		<input type="file" name="pictureList" /><br/>
		<input type="submit" value="업로드"	/>
	</form>
	
	<p>3-2) 여러 개의 파일업로드를 폼 파일 요소값을 MultiFileMember 타입의 자바빈즈 매개변수로 처리한다.</p>
	<form action="/chapt05/registerFile04" method="post" enctype="multipart/form-data">
		userId : <input type="text" name="userId"/><br/>
		password : <input type="text" name="password"/><br/>
		<input type="file" name="pictureList" multiple="multiple"/><br/>
		<input type="submit" value="업로드"	/>
	</form>
</body>
</html>