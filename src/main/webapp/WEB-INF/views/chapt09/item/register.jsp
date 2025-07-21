<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DDIT ITEM</title>
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
									<h4 class="card-title">Chapt09</h4>
								</div>									
								<div class="card-body">
									<p class="card-description">REGISTER</p>
									<form action="/item/register" method="post" enctype="multipart/form-data">
										<table class="table table-bordered">
											<tr>
												<td>상품명</td>
												<td>
													<input class="form-control" type="text" name="itemName" id="itemName"/>
												</td>
											</tr>
											<tr>
												<td>가격</td>
												<td>
													<input class="form-control"  type="text" name="price" id="price"/>
												</td>
											</tr>
											<tr>
												<td>파일</td>
												<td>
													<input type="file" name="picture" id="picture"/>
												</td>
											</tr>
											<tr>
												<td>개요</td>
												<td>
													<textarea class="form-control"  rows="5" cols="20" name="description" id="description">
													
													</textarea>
												</td>
											</tr>
										</table>
										<button type="submit">Register</button>
										<button type="button" onclick="javascript:location.href='/item/list'">List</button>
									</form>
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