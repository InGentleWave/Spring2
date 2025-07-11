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
										3) Model 객체에 자바빈즈 클래스 객체를 특정한 이름을 지정하여 전달할 수 있다.
									</p>
									user.userId : ${user.userId }<br/>
									user.password : ${user.password }<br/>
									user.userName : ${user.userName }<br/>
									user.email: ${user.email }<br/>
									user.birthDay : ${user.birthDay }<br/>
									user.dateOfBirth : ${user.dateOfBirth }<br/>
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