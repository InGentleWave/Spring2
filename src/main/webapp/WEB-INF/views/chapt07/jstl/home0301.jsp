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
										3) JSTL, c:forEach, c:forTokens
									</p>
									
									<c:forEach items="${member.hobbyArray }" var="hobby">
										${hobby }<br/>
									</c:forEach>
									
									<!-- 
										delims 속성에 지정된 구분자를 사용하여 items 속성에 전달된 문자열을 나누고
										var 속성에 명시한 변수에 나뉘어진 문자열을 지정합니다.
									 -->
									<p>c:forTokens</p>
									<c:forTokens items="${member.hobby }" delims="," var="hobby">
										${hobby }<br/>
									</c:forTokens>
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