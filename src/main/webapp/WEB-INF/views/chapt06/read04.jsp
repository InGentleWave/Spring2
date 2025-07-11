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
										4) Model 객체를 통해 다양한 타입의 값을 전달할 수 있다.
									</p>
									
									<table class="table table-bordered">
									<tr>
										<td>userId</td>
										<td>${user.userId }</td>
									</tr>
									<tr>
										<td>password</td>
										<td>${user.password }</td>
									</tr>
									<tr>
										<td>userName</td>
										<td>${user.userName }</td>
									</tr>
									<tr>
										<td>email</td>
										<td>${user.email }</td>
									</tr>
									<tr>
										<td>birthDay</td>
										<td>${user.birthDay }</td>
									</tr>
									<tr>
										<td>gender</td>
										<td>${user.gender }</td>
									</tr>
									<tr>
										<td>developer</td>
										<td>${user.developer }</td>
									</tr>
									<tr>
										<td>foreigner</td>
										<td>${user.foreigner }</td>
									</tr>
									<tr>
										<td>nationality</td>
										<td>${user.nationality }</td>
									</tr>
									<tr>
										<td>cars</td>
										<td>${user.cars }</td>
									</tr>
									<tr>
										<td>carArray</td>
										<td>
											<table class="table table-bordered">
												<c:if test="${not empty user.carArray }">
													<c:forEach items="${user.carArray }" var="car">
														<tr>
															<td>${car }</td>
														</tr>
													</c:forEach>
												</c:if>
											</table>
										</td>
									</tr>
									<tr>
										<td>carList</td>
										<td>
											<table class="table table-bordered">
												<c:if test="${not empty user.carList }">
													<c:forEach items="${user.carList }" var="car">
														<tr>
															<td>${car }</td>
														</tr>
													</c:forEach>
												</c:if>
											</table>
										</td>
									</tr>
									<tr>
										<td>hobby</td>
										<td>${user.hobby}</td>
									</tr>
									<tr>
										<td>hobbyArray</td>
										<td>
											<table class="table table-bordered">
												<c:if test="${not empty user.hobbyArray }">
													<c:forEach items="${user.hobbyArray }" var="hobby">
														<tr>
															<td>${hobby }</td>
														</tr>
													</c:forEach>
												</c:if>
											</table>
										</td>
									</tr>
									<tr>
										<td>hobbyList</td>
										<td>
											<table class="table table-bordered">
												<c:if test="${not empty user.hobbyList }">
													<c:forEach items="${user.hobbyList }" var="hobby">
														<tr>
															<td>${hobby }</td>
														</tr>
													</c:forEach>
												</c:if>
											</table>
										</td>
									</tr>
									<tr>
										<td>postCode</td>
										<td>${user.address.postCode}</td>
									</tr>
									<tr>
										<td>location</td>
										<td>${user.address.location}</td>
									</tr>
									<tr>
										<td>dateOfBirth</td>
										<td>${user.dateOfBirth}</td>
									</tr>
									<tr>
										<td>introduction</td>
										<td>${user.introduction}</td>
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