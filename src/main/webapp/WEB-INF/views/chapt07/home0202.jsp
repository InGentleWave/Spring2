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
									<h4 class="card-title">Chapt07</h4>
								</div>									
								<div class="card-body">
									<p class="card-description">
										3) 비교 연산자 사용
									</p>
									<table class="table table-bordered">
										<tr>
											<td>\${coin}</td>
											<td>${coin}</td>
										</tr>
										<tr>
											<td>\${coin == 1000}</td>
											<td>${coin == 1000}</td>
											<td>\${coin eq 1000}</td>
											<td>${coin eq 1000}</td>
										</tr>
										<tr>
											<td>\${coin != 1000}</td>
											<td>${coin != 1000}</td>
											<td>\${coin ne 1000}</td>
											<td>${coin ne 1000}</td>
										</tr>
										<tr>
											<td>\${coin &lt; 2000}</td>
											<td>${coin < 2000}</td>
											<td>\${coin lt 2000}</td>
											<td>${coin lt 2000}</td>
										</tr>
										<tr>
											<td>\${coin &gt; 500}</td>
											<td>${coin > 500}</td>
											<td>\${coin gt 500}</td>
											<td>${coin gt 500}</td>
										</tr>
										<tr>
											<td>\${coin &lt;= 1000}</td>
											<td>${coin <= 1000}</td>
											<td>\${coin le 1000}</td>
											<td>${coin le 1000}</td>
										</tr>
										<tr>
											<td>\${coin &gt;= 1000}</td>
											<td>${coin >= 1000}</td>
											<td>\${coin ge 1000}</td>
											<td>${coin ge 1000}</td>
										</tr>
									</table>
									<hr/>
									<table class="table table-bordered">
										<tr>
											<td>\${userId == "hongkd" }</td>
											<td>${userId == "hongkd" }</td>
											<td>\${userId eq "hongkd" }</td>
											<td>${userId eq "hongkd" }</td>
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