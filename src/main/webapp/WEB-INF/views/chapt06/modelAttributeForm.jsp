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
									<h3>Chapt06</h3>
									<h4 class="card-title">1. Model</h4>
								</div>									
								<div class="card-body">
									<p class="card-description">
										1) 기본 자료형인 매개변수에 @ModelAttribute 어노테이션을 지정하여 데이터를 전달한다.
									</p>
									<form action="/chapt06/modelattribute/register01" method="post">
										userId : <input type="text" name="userId"/>
										password : <input type="text" name="password"/>
										<input type="submit" value="전송"/>
									</form>
									
									<hr/>
									
									<p class="card-description">
										2) 자바빈즈 규칙에 맞는 객체는 매개변수로 선언하면 기본적으로 전달한다.
									</p>
									<form action="/chapt06/modelattribute/register02" method="post">
										userId : <input type="text" name="userId"/>
										password : <input type="text" name="password"/>
										<input type="submit" value="전송"/>
									</form>
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