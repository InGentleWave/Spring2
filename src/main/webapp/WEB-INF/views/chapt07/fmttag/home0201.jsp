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
										2) JSTL FMT 태그, fmt:parseNumber
									</p>
									
									
									<p>넘겨받은 문자의 type 속성 형태가 number인 경우</p>
									<p>coin : ${coinNumber }</p>
									<fmt:parseNumber value="${coinNumber }" var="coinNum"/>
									<p>coinNumber : ${coinNum }</p>
									<hr/>
									<p>넘겨받은 문자의 type 속성 형태가 currency인 경우</p>
									<p>coin : ${coinCurrency }</p>
									<fmt:parseNumber value="${coinCurrency }" var="coinCur" type="currency"/>
									<p>coinNumber : ${coinCur }</p>
									<hr/>
									<p>넘겨받은 문자의 type 속성 형태가 percent인 경우</p>
									<p>coin : ${coinPercent }</p>
									<fmt:parseNumber value="${coinPercent }" var="coinPer" type="percent"/>
									<p>coinNumber : ${coinPer }</p>
									<hr/>
									<p>넘겨받은 문자의 type 속성 형태가 pattern인 경우</p>
									<p>coin : ${coinPattern }</p>
									<fmt:parseNumber value="${coinPattern }" var="coinPat" pattern="0,000.00"/>
									<p>coinNumber : ${coinPat }</p>
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