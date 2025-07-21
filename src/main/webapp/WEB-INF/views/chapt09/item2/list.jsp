<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DDIT ITEM2</title>
<%@ include file="../../skydash/headPart.jsp"%>
</head>
<body>
	<div class="container-scroller">
		<%@ include file="../../skydash/header.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@ include file="../../skydash/aside.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Chapt09</h4>
								</div>
								<div class="card-body">
									<p class="card-description">LIST</p>
									
									<table class="table table-bordered">
										<tr>
											<th align="center" width="80">상품ID</th>
											<th align="center" width="320">상품명</th>
											<th align="center" width="100">가격</th>
											<th align="center" width="80">편집</th>
											<th align="center" width="80">제거</th>
										</tr>
										<c:choose>
											<c:when test="${empty itemList }">
												<tr>
													<td colspan="5">조회하신 게시글이 존재하지 않습니다.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${itemList }" var="item">
													<tr>
														<td align="center">${item.itemId }</td>
														<td align="left">${item.itemName }</td>
														<td align="right">${item.price }원</td>
														<td align="center">
															<a href="/item2/modify?itemId=${item.itemId }">상품 편집</a>
														</td>
														<td align="center">
															<a href="/item2/remove?itemId=${item.itemId }" id="">상품 제거</a>
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</table>
									<button type="button" onclick="javascript:location.href='/item2/register'">등록</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../../skydash/footer.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../../skydash/footerPart.jsp"%>
</body>
</html>