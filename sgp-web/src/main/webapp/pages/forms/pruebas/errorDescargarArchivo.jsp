<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<title>${titulo}</title>
</head>
<body>
	<div class="panel-error-descarga">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title">
					${tituloPanel}
				</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="col-md-8">
						<h5>${result}</h5>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<button type="button" class="btn btn-primary btn-xs"
							onclick="history.back();">
							<spring:message code="lblBtnBack" />
						</button>
					</div>
				</div>

			</div>
		</div>
	</div>

	<hr />
	<footer class="text-center">
		<p>
			<spring:message code="footer" />
		</p>
	</footer>
</body>
</html>