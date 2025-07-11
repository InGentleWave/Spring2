<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DDIT BOARD</title>
<%@ include file="../skydash/headPart.jsp" %>
</head>
<body>
	<div class="container-scroller">
		<%@ include file="../skydash/header.jsp" %>
		<div class="container-fluid page-body-wrapper">
			<%@ include file="../skydash/aside.jsp" %>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Chapt07</h4>
								</div>									
								<div class="card-body">
									<p class="card-description">
										1) 자바빈즈 프로퍼티를 조회하는 경우 '속성명.프로퍼티명' 또는 '속성명[키명]'을 지정한다
									</p>
									<table class="table table-bordered">
										<tr>
											<td>\${memberMap.userId }</td>
											<td>${memberMap.userId }</td>
										</tr>
										<tr>
											<td>\${memberMap.password }</td>
											<td>${memberMap.password }</td>
										</tr>
										<tr>
											<td>\${memberMap["email"] }</td>
											<td>${memberMap["email"] }</td>
										</tr>
										<tr>
											<td>\${memberMap["userName"] }</td>
											<td>${memberMap["userName"] }</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../skydash/footer.jsp" %>
			</div>
		</div>
	</div>

	<%@ include file="../skydash/footerPart.jsp" %>
</body>
</html>