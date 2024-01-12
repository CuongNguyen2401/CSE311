package com.fastfood.utils;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;

@Component
public class FileUploadUtil {

	public static String saveFiles(MultipartFile[] files, HttpServletRequest request, long id) {
		ServletContext servletctx = request.getServletContext();
		String ctxFullPath = servletctx.getRealPath("\\");
		int numberOfPic = 1;
		String imgs = "upload\\product";
		String result = "";
		for (MultipartFile multipartFile : files) {
			try {
				String fileName = "Product" + id + "-" + numberOfPic + ".png";
				File file = new File(ctxFullPath + SystemConstant.UPLOAD_PRODUCT_DIRECTORY, fileName);
				multipartFile.transferTo(file);
				result+=imgs;
				result += "\\" + fileName + "_";
			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
			numberOfPic++;

		}

		return result.substring(0, result.length() - 1);
	}
}
