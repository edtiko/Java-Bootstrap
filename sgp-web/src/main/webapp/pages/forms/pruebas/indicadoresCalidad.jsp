<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<title><spring:message code="indicadoresCalidad_titulo" /></title>
</head>
<body>
	<div class="container">
		<div class="alert alert-success" style="display: none;" id="alerta">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
			<span id="msg"><ul></ul></span>
		</div>

		<fieldset>
			<legend>
				<h5>
					<strong><spring:message
							code="lbl_indicadoresProyectos" /></strong>
				</h5>
			</legend>
			<form class="form-horizontal" id="form_indicadoresProyecto"
				name="form_indicadoresProyecto" method="get">
				<div class="form-group">
					<label class="col-sm-2 control-label"> <spring:message
							code="lbl_proyecto" />:
					</label>
					<div class="col-sm-4">
						<div class="input-group data-proyecto">
							<input type="text" id="proyecto" name="proyecto"
								class="form-control input-sm" data-id=""
								placeholder="Seleccione"
								title="<spring:message code="lbl_proyecto"/>" readonly>
							<!--<input type="hidden" id="idProyecto" name="idProyecto" />-->
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm"
									id="buscarProyecto">
									<spring:message code="btn_buscarProyecto" />
								</button>
							</div>
						</div>
					</div>
					<label class="col-sm-2 control-label" id="lblfecha"><spring:message
							code="lbl_fechaCorte" />:
					</label>
<!-- 					<div class="col-sm-2"> -->
<!-- 						<input type="text" id="fechaFrom" name="fechaFrom" -->
<!-- 							class="form-control input-sm" readonly -->
<%-- 							placeholder="<spring:message code="lbl_fechaInicial"/>" --%>
<%-- 							title="<spring:message code="lbl_fechaInicial"/>"> --%>
<!-- 					</div> -->
					<div class="col-sm-2">
						<input type="text" id="fechaTo" name="fechaTo"
							class="form-control input-sm" readonly
							placeholder='<spring:message code='lbl_fechaFinal'/>'
							title='<spring:message code='lbl_fechaFinal'/>'>
					</div>
				</div>
				
			</form>
		</fieldset>
		
		<br/><br/>
		
		<div id="indicadoresContent">
		
		</div>

	</div>
	
	 <hr />
	<footer class="text-center">
		<p>
			<spring:message code="footer" />
		</p>
	</footer>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/indicadoresCalidad.js"></script>

</body>
</html>