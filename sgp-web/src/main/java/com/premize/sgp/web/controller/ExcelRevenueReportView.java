package com.premize.sgp.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.facade.pruebas.HallazgoFacade;

public class ExcelRevenueReportView extends AbstractExcelView{

	@Autowired
	private HallazgoFacade hallazgoFacade;
	
	private FlujoHallazgoDTO flujoHallazgo;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Workbook libroExcel = hallazgoFacade.consultaHallazgosExcel(getFlujoHallazgo());
		
	}

	public FlujoHallazgoDTO getFlujoHallazgo() {
		return flujoHallazgo;
	}

	public void setFlujoHallazgo(FlujoHallazgoDTO flujoHallazgo) {
		this.flujoHallazgo = flujoHallazgo;
	}
	
	

}
