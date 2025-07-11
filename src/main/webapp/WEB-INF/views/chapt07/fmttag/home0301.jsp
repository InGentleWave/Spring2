<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
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
										3) JSTL FMT 태그, fmt:formatDate
									</p>
									<p>type 속성을 date로 지정하여 날짜 포맷팅을 한다.</p>
									<p>now : ${now}</p>
									<p>date default : <fmt:formatDate value="${now }" type="date" dateStyle="default"/></p>
									<p>date short : <fmt:formatDate value="${now }" type="date" dateStyle="short"/></p>
									<p>date medium : <fmt:formatDate value="${now }" type="date" dateStyle="medium"/></p>
									<p>date long : <fmt:formatDate value="${now }" type="date" dateStyle="long"/></p>
									<p>date full : <fmt:formatDate value="${now }" type="date" dateStyle="full"/></p>
									<hr/>
									
									<p>type 속성을 time으로 지정하여 날짜 포맷팅을 한다.</p>
									<p>now : ${now}</p>
									<p>time default : <fmt:formatDate value="${now }" type="time" timeStyle="default"/></p>
									<p>time short : <fmt:formatDate value="${now }" type="time" timeStyle="short"/></p>
									<p>time medium : <fmt:formatDate value="${now }" type="time" timeStyle="medium"/></p>
									<p>time long : <fmt:formatDate value="${now }" type="time" timeStyle="long"/></p>
									<p>time full : <fmt:formatDate value="${now }" type="time" timeStyle="full"/></p>
									<hr/>
									
									<p>type 속성을 both으로 지정하여 날짜 포맷팅을 한다.</p>
									<p>now : ${now}</p>
									<p>both default : <fmt:formatDate value="${now }" type="both" dateStyle="default" timeStyle="default"/></p>
									<p>both short : <fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short"/></p>
									<p>both medium : <fmt:formatDate value="${now }" type="both" dateStyle="medium" timeStyle="medium"/></p>
									<p>both long : <fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="long"/></p>
									<p>both full : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/></p>
									<hr/>
									
									<!-- 
										y : 년
										m : 월
										d : 일
										H : 시간
										m : 분
										s : 초
										z : 나라 표기 시
										a : 오전/오후
									 -->
									<p>pattern 속성을 지정하여 날짜 포맷팅을 한다.</p>
									<p>now : ${now}</p>
									<p>pattern : <fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss" /></p>
									<p>pattern : <fmt:formatDate value="${now }" pattern="a h:mm" /></p>
									<p>pattern : <fmt:formatDate value="${now }" pattern="z a h:mm" /></p>
										
								
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