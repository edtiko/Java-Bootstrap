package com.premize.sgp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class ZipBuilder
 * @description
 * @date 29/04/2014
 */
public class ZipBuilder {
	
    public static final String EXT_ZIP = ".zip";
    
    private String directory;
    private String zipFolder;
    private String zipName;
    private File zipFile;
    private String sourceFolder;
    private FileOutputStream out;
    private ZipOutputStream outZipFile;
    private FileInputStream zipStream;
    
    private byte buffer[];

    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 29/04/2014 
     * @param directory
     * @param zipFolder
     * @param zipName
     * @throws FileNotFoundException
     */
    public ZipBuilder(String directory, String zipFolder, String zipName) throws FileNotFoundException {
        File dir = new File(directory);
        if(!dir.exists()) {
            dir.mkdir();
        }
        this.directory = directory;
        this.zipFolder = zipFolder;
        this.zipName = zipName;
        sourceFolder = this.directory + File.separator + zipFolder;
        dir = new File(sourceFolder);
        if(!dir.exists()) {
            dir.mkdir();
        }
        String strZipFile = sourceFolder + File.separator + zipName + EXT_ZIP;
        out = new FileOutputStream(strZipFile);
        outZipFile = new ZipOutputStream(out);
        buffer = new byte[1024];
        zipFile = new File(strZipFile);
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 29/04/2014
     * @description 
     * @param fileName
     * @param delete
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void compressFile(String fileName, boolean delete) throws FileNotFoundException, IOException {
        ZipEntry entry = new ZipEntry(fileName);
        outZipFile.putNextEntry(entry);
        
        String file = sourceFolder + File.separator + fileName;
        FileInputStream in = new FileInputStream(file);
        int len;
        while((len = in.read(buffer)) > 0) {
            outZipFile.write(buffer, 0, len);
        }
        in.close();
        if(delete) {
            File f = new File(file);
            f.delete();
        }
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 29/04/2014
     * @description 
     * @return
     */
    public static String createString() {
        return "mapas"+new Date().getTime();
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 29/04/2014
     * @description 
     * @throws IOException
     */
    public void done() throws IOException {
        outZipFile.closeEntry();
        outZipFile.close();
        out.close();
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 29/04/2014
     * @description 
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public FileInputStream getZipStream() throws FileNotFoundException, IOException {
        zipStream = new FileInputStream(zipFile);
        //FileUtils.deleteDirectory(zipFile);
        return zipStream;
    }

    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 29/04/2014
     * @description 
     * @return
     */
    public String getSourceFolder() {
        return sourceFolder;
    }

    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 29/04/2014
     * @description 
     * @return
     */
    public FileOutputStream getOut() {
        return out;
    }

}
