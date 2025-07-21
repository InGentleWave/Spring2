<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DDIT ITEM2</title>
<%@ include file="../../skydash/headPart.jsp" %>
</head>
<body>
	<div class="container-scroller">
		<%@ include file="../../skydash/header.jsp" %>
		<div class="container-fluid page-body-wrapper">
			<%@ include file="../../skydash/aside.jsp" %>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Chapt09</h4>
								</div>									
								<div class="card-body">
									<p class="card-description">REMOVE</p>
									<form action="/item2/remove" method="post" id="item">
										<input type="hidden" name="itemId" value="${item.itemId }"/>
										<table class="table table-bordered">
											<tr>
												<td>상품명</td>
												<td>
													<input class="form-control" type="text" name="itemName" id="itemName" value="${item.itemName }" disabled="disabled"/>
												</td>
											</tr>
											<tr>
												<td>가격</td>
												<td>
													<input class="form-control"  type="text" name="price" id="price" value="${item.price }" disabled="disabled"/>
												</td>
											</tr>
											<tr>
												<td>파일</td>
												<td>
													<div class="uploadedList">
													
													</div>
												</td>
											</tr>
											<tr>
												<td>개요</td>
												<td>
													<textarea class="form-control"  rows="5" cols="20" name="description" id="description" disabled="disabled">
														${item.description }
													</textarea>
												</td>
											</tr>
										</table>
										<button type="submit">Remove</button>
										<button type="button" onclick="javascript:location.href='/item2/list'">List</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../../skydash/footer.jsp" %>
			</div>
		</div>
	</div>
	<%@ include file="../../skydash/footerPart.jsp" %>
</body>
<script type="text/javascript">
	$(function(){
		let itemId = "${item.itemId}";
		
		$.getJSON("/item2/getAttach/"+itemId, function(list){
			$(list).each(function(){
				console.log(list);	
				console.log(this);
				let result = this;
				
				let html = "";
				if(checkImageType(result)){	// 이미지라면 이미지태그를 이용하여 출력
					html += "<div>";						
					html += "	<a href='/item2/displayFile?fileName="+result+"'>";						
					html += "		<img src='/item2/displayFile?fileName="+getThumbnailName(result)+"'>";		
					html += "	</a>";						
					html += "</div>";						
				} else {
					console.log("이미지 아님!");
					html += "<div>";						
					html += "	<a href='/item2/displayFile?fileName="+ result +"'>";						
					html += "		" + getOriginalName(result);						
					html += "	</a>";						
					html += "</div>";						
				}
				$(".uploadedList").append(html);
			});
		});
	});
	
	// 이미지 파일인지 확인
	function checkImageType(fileName){
		let pattern = /jpg|gif|png|jpeg/i;
		return fileName.match(pattern);	// 패턴과 일치하면 true
	}
	
	// 임시 파일로 썸네일 이미지 만들기
	function getThumbnailName(fileName){
		let front = fileName.substr(0,12);	// /2025/07/16/ 폴더
		let end = fileName.substr(12);		// 뒤 파일명
		return front + "s_" + end;			// 썸네일 이미지 파일명 생성
	}
	// 파일명 추출
	function getOriginalName(fileName){
		// 이미지 파일이면 return
		if(checkImageType(fileName)){
			return;
		}
		let idx = fileName.indexOf("_")+1;
		return fileName.substr(idx);
	}
</script>
</html>