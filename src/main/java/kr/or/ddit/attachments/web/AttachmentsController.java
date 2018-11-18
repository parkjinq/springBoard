package kr.or.ddit.attachments.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/attachments")
@Controller
public class AttachmentsController {

	@RequestMapping("/attFileDown")
	public void attFileDown(@RequestParam("att_path") String att_path, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String directory = request.getServletContext().getRealPath("/attachments");
		File file = new File(directory + File.separator + att_path);
		
		String mimeType = request.getServletContext().getMimeType(file.toString());
		if(mimeType == null){
			response.setContentType("aplication/octet-stream");
		}
		
		String downloadName = null;
		if(request.getHeader("user-agent").indexOf("MSIE") == -1){
			downloadName = new String(att_path.getBytes("UTF-8"), "8859_1");
		} else {
			downloadName = new String(att_path.getBytes("EUC-KR"), "8859_1");
		}
		response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadName + "\";");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte b[] = new byte[1024];
		int data = 0;
		
		while ((data = (fileInputStream.read(b, 0, b.length))) != -1) {
			servletOutputStream.write(b, 0, data);
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	}
	
}
