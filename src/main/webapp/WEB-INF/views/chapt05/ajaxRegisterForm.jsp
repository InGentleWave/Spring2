<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<h4>Ajax 방식 요청 처리</h4>
	<hr/>
	
	<form action="">
		<p>userId : <input type="text" name="userId" id="userId"/></p>
		<p>password : <input type="text" name="password" id="password"/></p>
	</form>
	
	<p>1) 객체 타입의 JSON 요청 데이터 @RequetBody 어노테이션을 지정하여 자바빈즈 매개변수로 처리한다.</p>
	<button type="button" id="registerBtn01">요청01</button>
	
	<p>2) 객체 타입의 JSON 요청 데이터를 문자열 매개변수로 처리한다.(문자열 매개변수로 처리 불가.MAP을 이용)</p>
	<button type="button" id="registerBtn02">요청02</button>

	<p>3) 요청 URL에 쿼리파라미터를 붙여서 전달하면 문자열 매개변수로 요청한다.</p>
	<button type="button" id="registerBtn03">요청03</button>

	<p>4) 객체 배열 타입의 JSON 요청 데이터를 자바빈즈 요소를 가진 리스트 컬렉션 매개변수로 @RequestBody 어노테이션을 지정하여 처리한다.</p>
	<button type="button" id="registerBtn04">요청04</button>

	<p>5) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.</p>
	<button type="button" id="registerBtn05">요청05</button>

	<p>6) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.</p>
	<button type="button" id="registerBtn06">요청06</button>
</body>
<script type="text/javascript">
	$(function(){
		// $.ajax()에서 활용되니ㅡㄴ 기본적인 속성
		// - url : 목적지 주소를 설정합니다.
		// - type or method : method 방식을 설정합니다.
		// - contentType : 요청 Mime Type
		// - headers : 요청 header 정보
		// - data : 요청으로 보낼 데이터
		// - dataType : 응답으로 받을 데이터 Mime Type
		//				jquery ajax에서 dataTpye 속성을 설정하지 않으면, ContentType을 보고 dataType 예측함
		// - success : 요청에 대한 성공 callback
		// - error : 요청에 대한 실패 callback
		// - async : 요청을 동기/비동기 처리할 지 설정(기본값은 true=>비동기, false 설정 시 동기 방식) :::권장사항 아님
		// - timeout : 요청이 실패가 되기까지  기다릴 최대 시간(밀리초), 실패 시 에러로 간주
		// - beforeSend : 요청이 전송되기 전에 호출되는 콜백
		let registerBtn01 = $("#registerBtn01");
		let registerBtn02 = $("#registerBtn02");
		let registerBtn03 = $("#registerBtn03");
		let registerBtn04 = $("#registerBtn04");
		let registerBtn05 = $("#registerBtn05");
		let registerBtn06 = $("#registerBtn06");
		
		registerBtn01.on("click",function(){
			let userId = $("#userId").val();
			let password = $("#password").val();
			
			let userObject = {
				userId : userId,
				password : password
			};
			
			// JSON.stringify() : JSON 객체를 문자열로 변경(JSON 객체의 형태를 띄고 있는 문자열로 변경)
			//						데이터를 쉽고 빠르게 전송할 수 있도록 JSON 객체 형태를 문자열로 직렬화해서 전송한다.
			$.ajax({
				url : "/chapt05/ajax/register01",
				type : "post",
				data : JSON.stringify(userObject),
				contentType : "application/json;charset=utf-8",
				success : function(result){
					console.log(result);
					if(result == "SUCCESS"){
						alert(result);
					}
				},
				error : function(error,status,thrown){
					console.error(error);		
					console.error(status);		
					console.error(thrown);		
				}
			})
		});
		
		registerBtn02.on("click",function(){
			let userId = $("#userId").val();
			let password = $("#password").val();
			
			let userObject = {
				userId : userId,
				password : password
			};
			
			$.ajax({
				url : "/chapt05/ajax/register02",
				method : "post",
				data : JSON.stringify(userObject),
				contentType : "application/json;charset=utf-8",
				success : function(result){
					console.log("result"+result);
					if(result === "SUCCESS"){
						alert(result);
					}
				},
				error : function(error, status, thrown){
					console.error(error);		
					console.error(status);		
					console.error(thrown);		
				}
			})
			
		});
		registerBtn03.on("click",function(){
			let userId = $("#userId").val();
			let password = $("#password").val();
			
			let userObject = {
				userId : userId,
				password : password
			};
			
			$.ajax({
				url : "/chapt05/ajax/register03?userId="+userId,
				method : "post",
				data : JSON.stringify(userObject),
				contentType : "application/json;charset=utf-8",
				success : function(result){
					console.log("result"+result);
					if(result === "SUCCESS"){
						alert(result);
					}
				},
				error : function(error, status, thrown){
					console.error(error);		
					console.error(status);		
					console.error(thrown);		
				}
			})
		});
		
		registerBtn04.on("click",function(){
			let userArray = [
				{"userId":"name01", "password":"pw1234"},
				{"userId":"name02", "password":"pw4321"}
			];
			$.ajax({
				url : "/chapt05/ajax/register04",
				method : "post",
				data : JSON.stringify(userArray),
				contentType : "application/json;charset=utf-8",
				success : function(result){
					console.log("result"+result);
					if(result === "SUCCESS"){
						alert(result);
					}
				},
				error : function(error, status, thrown){
					console.error(error);		
					console.error(status);		
					console.error(thrown);		
				}
			})
		});
		
		registerBtn05.on("click",function(){
			let userId = $("#userId").val();
			let password = $("#password").val();
			
			let userObject = {
				userId : userId,
				password : password,
				address : {
					postCode : "010988",
					location : "DaeJeon"
				}
			};
			
			$.ajax({
				url : "/chapt05/ajax/register05",
				method : "post",
				data : JSON.stringify(userObject),
				contentType : "application/json;charset=utf-8",
				success : function(result){
					console.log("result"+result);
					if(result === "SUCCESS"){
						alert(result);
					}
				},
				error : function(error, status, thrown){
					console.error(error);		
					console.error(status);		
					console.error(thrown);		
				}
			})
		});
		
		registerBtn06.on("click",function(){
			let userId = $("#userId").val();
			let password = $("#password").val();
			
			let userObject = {
				userId : userId,
				password : password,
				cardList : [
					{ no : "12345", validMonth : "20250708"},	
					{ no : "43124", validMonth : "20250709"}	
				]
			};
			
			$.ajax({
				url : "/chapt05/ajax/register06",
				method : "post",
				data : JSON.stringify(userObject),
				contentType : "application/json;charset=utf-8",
				success : function(result){
					console.log("result"+result);
					if(result === "SUCCESS"){
						alert(result);
					}
				},
				error : function(error, status, thrown){
					console.error(error);		
					console.error(status);		
					console.error(thrown);		
				}
			})
		});
	})

</script>
</html>