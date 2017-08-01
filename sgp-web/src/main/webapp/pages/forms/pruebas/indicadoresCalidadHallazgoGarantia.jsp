 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!--  <script type="text/javascript" -->
<%-- 		src="<%=request.getContextPath()%>/resources/js/pruebas/indicadoresCalidadCuerpo.js"></script> --%>
 <div>
	<fieldset>
		<legend>
			<h5>
				<strong> <spring:message code="lbl_hallazgosporgarantias" />
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="formulaIndEfectividadHallazgosReportados" />
			</p>
		</div>
		<br/>
		<div style="text-align: center; margin: 0 30px;">
			<div class="row">
				<div class="col-md-4" style="font-weight: bold">
					<spring:message code="lbl_indicadorEfectividadHallazgosReportados" />
				</div>
				<div class="col-md-1">${indicadores.indicadorEfectividadHallazgosReportados.indicador.indicadorString}</div>
				<div class="col-md-1">
					<img style="width: 20px; height: 20px"
						src="<%=request.getContextPath()%>/resources/images/indicadores/${indicadores.indicadorEfectividadHallazgosReportados.indicador.semaforoImage}" />
				</div>
				<div class=""></div>
			</div>
			<br/>
			<div class=row>
				<div class="col-md-8">
					<table class="table table-bordered" style="font-size: 12px">
						<thead>
							<tr>
								<th>Hallazgos Reportados</th>
								<th>Hallazgos Anulados</th>
								<th>Hallazgos Invalidados</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${hallazgosReportados}</td>
								<td>${hallazgosAnulados}</td>
								<td>${hallazgosInvalidados}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</fieldset>

	<fieldset>
		<legend>
			<h5>
				<strong> <spring:message code="lbl_hallazgosporgarantias" />
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="formulaHallazgoGarantia" />
			</p>
		</div>

		<div > 
		<p>
			<spring:message code="OmitirCausasGeneracion" />
		</p>
		</div>
		<div  style="font-weight: bold">
					<spring:message code="lblIndicadorHallazgoGarantia" />
					<div >${indicadores.indicadorReprocesoConstruccionPruebas.indicador.indicadorString}</div>
		</div>
		
		



	</fieldset>
</div>