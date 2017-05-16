package bh.gov.cio.gbs.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bh.gov.cio.gbs.exception.GBSFileUploadExcption;

public class FileUtil {
		
	private static final Logger logger = LoggerFactory
			.getLogger(FileUtil.class);
	
	private String folderUrl;
	private String fileServerDomain;
	private String fileServerPassword;
	private String fileServerUsername;


	public String getFolderUrl() {
		return folderUrl;
	}

	public void setFolderUrl(String folderUrl) {
		this.folderUrl = folderUrl;
	}

	public String getFileServerDomain() {
		return fileServerDomain;
	}

	public void setFileServerDomain(String fileServerDomain) {
		this.fileServerDomain = fileServerDomain;
	}

	public String getFileServerPassword() {
		return fileServerPassword;
	}

	public void setFileServerPassword(String fileServerPassword) {
		this.fileServerPassword = fileServerPassword;
	}

	public String getFileServerUsername() {
		return fileServerUsername;
	}

	public void setFileServerUsername(String fileServerUsername) {
		this.fileServerUsername = fileServerUsername;
	}

	public static final String UNDERSCORE = "_";
	public static final String DOT = ".";
	public static final String FILENAME = "/WEB-INF/spring/application.properties";

	public static String upload(String fileName, String fileBase64) throws GBSFileUploadExcption{
		logger.info("upload : start()");
		
		String fileNameTimestamp = fileWithTimestamp(fileName);
		Properties properties=loadPropsFile();
		String filePath = properties.getProperty("ega.file.server.folder.url")+fileNameTimestamp;
		byte[] imageByte = Base64.decodeBase64(fileBase64);
		String directory = filePath;
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(directory);
			outputStream.write(imageByte);
			outputStream.close();
		} catch (FileNotFoundException e) {
			logger.info("Faild to upload file " , e);
			throw new GBSFileUploadExcption(e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("Faild to write to file " , e);
			throw new GBSFileUploadExcption(e);
		}
		
		return fileNameTimestamp;
	}
	
	public static String getBase64Attachment(String fileName) throws IOException {
		
		Properties properties=loadPropsFile();
		String filePath = properties.getProperty("ega.file.server.folder.url")+fileName;
		logger.info("The Whole file path is: " + filePath);
         
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();
            String fileBase64=Base64.encodeBase64String(fileBytes);
            return fileBase64;
            
        } catch (FileNotFoundException e) {
			logger.info("Faild to download file " , e);
			throw new GBSFileUploadExcption(e);
		}
    }


	public static void uploadCIFS(String fileName, String fileBase64) throws GBSFileUploadExcption{
//			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(
//					fileServerDomain, fileServerUsername, fileServerPassword);
//			String fileFullPath = folderUrl + fileWithTimestamp(fileName);
//			logger.debug("file to read : {}", fileFullPath);
//			SmbFile sfile;
//			try {
//				sfile = new SmbFile(fileFullPath, auth);
//				SmbFileOutputStream sfos = new SmbFileOutputStream(sfile);
//				byte[] fileBytes = Base64.decodeBase64(fileBase64);
//				sfos.write(fileBytes);
//				sfos.close();
//				logger.info("File Length : ", fileBytes.length);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (SmbException e) {
//				e.printStackTrace();
//				throw new GBSFileUploadExcption(e);
//			} catch (UnknownHostException e) {
//				e.printStackTrace();
//				throw new GBSFileUploadExcption(e);
//			} catch (IOException e) {
//				e.printStackTrace();
//				throw new GBSFileUploadExcption(e);
//			}
			
			
	}

	public static String fileWithTimestamp(String fileName){
		Date now = new Date();
		String fileNameNoExt = FilenameUtils.removeExtension(fileName);
		String ext = FilenameUtils.getExtension(fileName);
		return fileNameNoExt + UNDERSCORE + now.getTime() + DOT + ext;

	}
	
	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}
	
	public static Properties loadPropsFile() {
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		ServletContext servletContext =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = servletContext.getResourceAsStream(FILENAME);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	public static void main(String[] args) throws Throwable {

//		String filePath = "C:/Users/csdvdnm/Desktop/GBS/read/";
//		File file = new File(filePath + "test.txt");
//
//		FileInputStream imageInFile = new FileInputStream(file);
//		byte imageData[] = new byte[(int) file.length()];
//		imageInFile.read(imageData);
//		String imageDataString = encodeImage(imageData);
//
//		imageInFile.close();
//
//		FileUtil.upload("Test.txt", imageDataString);
		loadPropsFile();
	}

	
}
