<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



			<div class="col-md-4" style="font-weight: bold">
					<spring:message code="lblIndicadorHallazgoGarantia" />
			</div>
			<div class="col-md-1">${hallazgoGarantia.indicadorString}</div>
			<div class="col-md-1"><img style="width: 20px; height: 20px"
						src="<%=request.getContextPath()%>/resources/images/indicadores/${hallazgoGarantia.semaforo}" />
				</div>

		<div class=row>
		<div class="col-md-8">
	
		<br/>	
		<br/>
		<table class="table table-bordered" style="font-size: 12px">
			<thead>
				<tr>
					<th>Hallazgos Reportados</th>
					<th>Hallazgos Anulados</th>
					<th>Hallazgos Invalidados</th>
					<th>Total de Artefactos del Proyecto</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${hallazgoGarantia.hallazgosReportados}</td>
					<td>${hallazgoGarantia.hallazgosAnulados}</td>
					<td>${hallazgoGarantia.hallazgosInvalidados}</td>
					<td>${hallazgoGarantia.totalArtefacto}</td>
				</tr>
			</tbody>
			</table>
		</div>	
		</div>