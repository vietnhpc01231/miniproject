package com.example.demo.implement;

import java.io.File;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.uploadService;


@Service
public class uploadServiceImpl implements uploadService {
	@Autowired
	ServletContext app;
	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath(folder));
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String s = System.currentTimeMillis() +file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) +s.substring(s.lastIndexOf("."));
		try {
			File saveF=new File(dir,name);
			file.transferTo(saveF);
			System.err.println(saveF);
			return saveF;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
