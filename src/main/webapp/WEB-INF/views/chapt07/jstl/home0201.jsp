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
										2) JSTL, c:if, c:when, c:otherwise
									</p>
									<c:if test="${member.hobbyArray == null }">
										<p>member.hobbyArray == null</p>
									</c:if>
									
									<p>test 속성에 true나 false를 값으로 가지는 boolean 타입의 변수가 올 수 있다.</p>
									<c:if test="${member.foreigner }">
										<p>member.foreiner == true</p>
									</c:if>
									
									<p>c:when, c:otherwise</p>
									<c:choose>
										<c:when test="${member.gender == 'M' }">
											<p>남자</p>
										</c:when>
										<c:otherwise>
											<p>여자</p>
										</c:otherwise>
									</c:choose>
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