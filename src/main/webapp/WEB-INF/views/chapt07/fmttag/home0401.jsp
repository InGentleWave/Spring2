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
										4) JSTL FMT 태그, fmt:parseDate
									</p>
									<p>dateStyle 속성을 지정하지 않으면 기본값은 default다.</p>
									<p>dateValue : ${dateValueDefault }</p>
									<fmt:parseDate value="${dateValueDefault }" type="date" var="dateDefault"/>
									<p>date : ${dateDefault }</p>
									<hr/>
								
									<p>dateStyle 속성을 short로 지정하여 문자열을 Date 객체로 변환한다.</p>
									<p>dateValue : ${dateValueShort }</p>
									<fmt:parseDate value="${dateValueShort }" type="date" dateStyle="short" var="dateShort"/>
									<p>date : ${dateShort }</p>
									<hr/>
								
									<p>dateStyle 속성을 medium로 지정하여 문자열을 Date 객체로 변환한다.</p>
									<p>dateValue : ${dateValueMedium }</p>
									<fmt:parseDate value="${dateValueMedium }" type="date" dateStyle="medium"  var="dateMedium"/>
									<p>date : ${dateMedium }</p>
									<hr/>
								
									<p>dateStyle 속성을 long로 지정하여 문자열을 Date 객체로 변환한다.</p>
									<p>dateValue : ${dateValueLong }</p>
									<fmt:parseDate value="${dateValueLong }" type="date"  dateStyle="long" var="dateLong"/>
									<p>date : ${dateLong }</p>
									<hr/>
								
									<p>dateStyle 속성을 full로 지정하여 문자열을 Date 객체로 변환한다.</p>
									<p>dateValue : ${dateValueFull }</p>
									<fmt:parseDate value="${dateValueFull }" type="date"  dateStyle="full" var="dateFull"/>
									<p>date : ${dateFull }</p>
									<hr/>
								
									<p>dateStyle 속성을 pattern로 지정하여 문자열을 Date 객체로 변환한다.</p>
									<p>dateValue : ${dateValuePattern }</p>
									<fmt:parseDate value="${dateValueShort }" type="date" pattern="yyyy-MM-dd HH:mm:ss" var="datePatten"/>
									<p>date : ${datePatten }</p>
									<hr/>
								
								
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