<%@ include file="/pages/templates/layout.jsp"%><html>
<head>
<link href="<%=request.getContextPath()%>/resources/css/print.css" rel="stylesheet" media="print" type="text/css" />
<title>Informe de Seguimiento</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util/pivot.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/pivot.css" />
</head>
<body>
	<div class="container">
	<fieldset class="not-print">
	<legend><h5><strong>Informe de Seguimiento</strong></h5></legend>
	<form class="form-horizontal" role="form" id="frm_seguimiento" name="frm_seguimiento">
				<div class="form-group">
					<label class="col-sm-2 control-label">Proyecto:</label>
					<div class="col-sm-3">
						<div class="input-group data-proyecto">
							<input type="text" id="proyecto" name="proyecto"
								class="form-control input-sm" data-id=""
								placeholder="Seleccione un Proyecto" title="Proyecto" readonly>
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm"
									id="buscarProyecto">Buscar</button>
							</div>
						</div>
					</div>
				    <label class="col-sm-2 control-label">Tipo de Hallazgo:</label>
				 <div class="col-sm-3">
					<select id="tipoHallazgo" class="form-control input-sm" disabled>
					<option value="">Seleccione un Tipo de Hallazgo</option>
					</select>
					</div>
					
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label" id="lblfecha">Fecha de Corte:</label>
						<div class="col-sm-2" >
								<input type="text" id="fechaTo" name="fechaTo"
									class="form-control input-sm" placeholder="Fecha Final"
									title="Fecha de Corte" readonly>
					</div>
				
				</div>
			</form>
			
			</fieldset>
			<br />
	<div id="divInforme" class="container">
	
	</div>
	<div class="form-actions col-sm-offset-5 col-sm-5 not-print">
					<button type="button" class="btn btn-primary" id="pdf" >Generar PDF</button>
				</div>
	</div>
	<hr class="not-print" />
	<footer class="text-center not-print">
		<p>
			<spring:message code="footer" />
		</p>
	</footer>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/reporteSeguimiento.js"></script>
		
</body>
	