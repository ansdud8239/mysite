package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.FileuploadServiceException;

@Service
public class FileuploadService {

	private static String SAVE_PATH = "/mysite-uploads";
	private static String URL_PATH = "/assets/upload-images";

	public String restore(MultipartFile file) {
		String url = "";
		try {
			File uploadDirectory = new File(SAVE_PATH);
			if (!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}
			if (file.isEmpty()) {
				return url;
			}

			String originFilename = file.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf(".") + 1);
			String saveFilename = generateSaveFilename(extName);
			Long filesize = file.getSize();

			//System.out.println("#### " + extName);

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFilename);
			os.write(data);
			os.close();
			
			url = URL_PATH+"/"+saveFilename;
		} catch (IOException ex) {
			throw new FileuploadServiceException(ex.toString());
		}

		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

}
