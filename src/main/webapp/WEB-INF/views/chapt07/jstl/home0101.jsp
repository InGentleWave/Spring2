<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DDIT BOARD</title>
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
									<h4 class="card-title">Chapt07</h4>
								</div>									
								<div class="card-body">
									<p class="card-description">
										1) JSTL, c:set
									</p>
									<c:set value="${member.userId }" var="id" />
									<table class="table table-bordered">
										<tr>
											<td>\${member.userId }</td>
											<td>${id }</td>
										</tr>
									</table>
									<!-- c:set의 몸체를 이용해 Member의 userId를 memId라는 변수로 저장한다. -->
									<c:set var="memId">${member.userId }</c:set>
									<table class="table table-bordered">
										<tr>
											<td>\${member.userId }</td>
											<td>${memId }</td>
										</tr>
									</table>
									
									<!-- c:set의 target을 Member로 지정 후, 해당 프로퍼티 중 userId의 값을 'hongkildong'으로 변경한다. -->
									<c:set target="${member }" property="userId" value="hongkildong"/>
									<table class="table table-bordered">
										<tr>
											<td>\${member.userId }</td>
											<td>${member.userId }</td>
										</tr>
									</table>
									
									<!-- memId에 저장된 값을 삭제한다. -->
									<c:remove var="memId"/>
									<p>memId 변수를 삭제함.</p>
									<table class="table table-bordered">
										<tr>
											<td>\${member.userId }</td>
											<td>${memId }</td>
										</tr>
									</table>
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
</html>