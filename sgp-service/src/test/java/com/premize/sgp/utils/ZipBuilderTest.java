package com.premize.sgp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class ZipBuilderTest
 * @description
 * @date 4/05/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class ZipBuilderTest {
	
	private static final String DIRECTORIO_WIN = "c:\\zipTest"; 
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/05/2014
	 * @description
	 */
	@Test
	public void createString() {
		String str = ZipBuilder.createString();
		Assert.assertNotNull(str);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/05/2014
	 * @description
	 */
	@Test
	public void constructorZipBuilder() {
		String zipFolder = ZipBuilder.createString();
		String nombreProyecto = "SOFIB";
		try {
			ZipBuilder zipBuilder = new ZipBuilder(DIRECTORIO_WIN, zipFolder, nombreProyecto);
			Assert.assertNotNull(zipBuilder);
			zipBuilder.done();
		} catch (FileNotFoundException ex) {
			fail(ex.getMessage());
		} catch (IOException ioex) {
			fail(ioex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/05/2014
	 * @description
	 */
	@Test
	public void compressFile() {
		String zipFolder = ZipBuilder.createString();
		String nombreProyecto = "SOFIB";
		FileOutputStream out = null;
		
		try {
			ZipBuilder zipBuilder = new ZipBuilder(DIRECTORIO_WIN, zipFolder, nombreProyecto);
			File directorio = new File(DIRECTORIO_WIN);
			if(!directorio.exists()) {
				directorio.mkdir();
			}
			
			//Creando el archivo y escribiendo su nombre en el mismo.
			String fileName = "testFile1.txt";
			File f = new File(zipBuilder.getSourceFolder() + File.separator + fileName);
			f.createNewFile();
			out = new FileOutputStream(f);
			out.write(fileName.getBytes());
			out.flush(); out.close();
			
			//El archivo "testFile1.txt sera comprimido y posteriormente eliminado.
			zipBuilder.compressFile(fileName, true);

			//Creando el archivo y escribiendo su nombre en el mismo.
			fileName = "testFile2.txt";
			f = new File(zipBuilder.getSourceFolder() + File.separator + fileName);
			f.createNewFile();
			out = new FileOutputStream(f);
			out.write(fileName.getBytes());
			out.flush(); out.close();
			
			// El archivo "testFile2.txt sera comprimido pero no eliminado.
			zipBuilder.compressFile(fileName, false);
			
			zipBuilder.done();
		} catch (FileNotFoundException ex) {
			fail(ex.getMessage());
		} catch (IOException ioex) {
			fail(ioex.getMessage());
		}
	}

}
