<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="panel-error-descarga">
	<div class="panel panel-danger">
		<div class="panel-heading">
			<h3 class="panel-title">${tituloPanel}</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<div class="col-md-8">
					<h5>${result}</h5>
				</div>
			</div>
			<c:if test="${backButton}">
				<div class="form-group">
					<div class="col-md-6">
						<button type="button" class="btn btn-primary btn-xs"
							onclick="history.back();">
							<spring:message code="lblBtnBack" />
						</button>
					</div>
				</div>
			</c:if>

		</div>
	</div>
</div>