<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
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
										1) JSTL fn 태그
									</p>
									<p>str : ${str }</p>
									<p>contains : ${fn:contains(str, 'Hello') }</p>
									<p>containsIgnoreCase : ${fn:containsIgnoreCase(str, 'Hello') }</p>
									<p>startsWith : ${fn:startsWith(str, 'Hello') }</p>
									<p>endsWith : ${fn:endsWith(str, 'World!') }</p>
									<p>indexOf : ${fn:indexOf(str, 'World!') }</p>
									<p>length : ${fn:length(str) }</p>
									<p>escapeXml : ${fn:escapeXml(str) }</p>
									<p>replace : ${fn:replace(str,'Hello','Hi') }</p>
									<p>toLowerCase : ${fn:toLowerCase(str) }</p>
									<p>toUpperCase : ${fn:toUpperCase(str) }</p>
									<p>trim : ${fn:trim(str) }</p>
									<p>substring : ${fn:substring(str,7,12) }</p>
									<p>substringAfter : ${fn:substringAfter(str,'World') }</p>
									<p>substringBefore : ${fn:substringBefore(str,'World!') }</p>
									<p>split : ${fn:split(str, ' ') }</p>
									
									<c:set value="${fn:split(str, ' ') }" var="strArray"/>
									<p>join : ${fn:join(strArray, '-') }</p>
									<p>join : ${fn:join(fn:split(str, ' '), '-') }</p>
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