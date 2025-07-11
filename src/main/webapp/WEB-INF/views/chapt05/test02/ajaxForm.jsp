<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<!-- 
		문제 )
		파일을 업로드 합니다.<br/>
		비동기 요청을 이용해 파일을 서버로 업로드 하고
		서버로부터 전달받은 업로드 한 파일의 파일명, 크기, ContentType을 출력해주세요.
		이때, 이미지 파일인 경우라면 썸네일을 함께 출력하고 일반적인 파일이면 파일명만 출력해주세요.
		
		C ------------------------ Server
											전송한 파일명, 크기, ContentType 출력
											3개의 출력 데이터 다시 Client callback으로 전달
		전달받은 데이터 결과 출력
		이때, 이미지 파일이면 썸네일까지 출력
		일반 파일이면 파일명 출력
	 -->
	 <input type="file" id="inputFile"/>
	 
	<div id="resultArea">
	 
	</div>
</body>
<script type="text/javascript">
	function isImage(file){
		let ext = file.name.split(".").pop().toLowerCase();
		return (
			$.inArray(ext,["jpg","jpeg","png","gif"]) === -1 ? false : true
		);
	}
	$(function(){
		let inputFile = $("#inputFile");
		let resultArea = $("#resultArea");
		
		inputFile.on("change",function(event){
			console.log("change event!");
			let files = event.target.files;
			let file = files[0];
			
			console.log(file);
			let formData = new FormData();
			formData.append("file",file);
			
			$.ajax({
				url : "/chapt05/test02/upload",
				method : "post",
				contentType : false,
				processData : false,
				data : formData,
				success : function(data){
					//data = ["UPLOAD SUCCESS", 파일명, 파일크기, 파일contentType]
					let originalName = data[1];
					let fileSize = data[2];
					let fileContentType = data[3];
					if(data[0] === "UPLOAD SUCCESS"){
						let file = event.target.files[0];
						let reader = new FileReader();
						reader.onload = function(e){
							let divHTML = `	<div style="width:200px; display:inline-block;">
											<table  class="table table-bordered">
												<tr>
													<td width="100%">\${originalName}</td>
												</tr>
											`;
							if(isImage(file)){
								divHTML +=`
												<tr>
													<td><img width="100%" src="\${e.target.result}"/></td>
												</tr>
											`;
							}
							divHTML += `
												<tr>
													<td>파일 크기 : \${fileSize}</td>
												</tr>
												<tr>
													<td>
														파일 타입 :<br/>
														\${fileContentType}
													</td>
												</tr>
												
											</table>
											</div>
										`;
							resultArea.append(divHTML);
						}
						reader.readAsDataURL(file);
					} else {
						alert("파일 등록 실패");
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