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
										5) JSTL, c:redirect
									</p>
									
									<p>지정한 페이지로 리다이렉트한다.</p>
									<c:redirect url="http://localhost:8060/chapt03/list"/>
									
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
									<p>리다이렉트 이후의 코드는 실행되지 않는다.</p>
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