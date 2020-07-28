package com.famgomjas.ws.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.famgomjas.ws.service.UserDetailsImpl;

@RestController
@RequestMapping("report")
public class ReportCtrl {
	
	@GetMapping("/lalito")
	public void helloWorld(HttpServletResponse response) {
		try {
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        output = generatePDFTest();

	        response.addHeader("Content-Type", "application/pdf"); 
	        response.addHeader("Content-Disposition", "inline; filename=\"yourFile.pdf\"");
	        response.getOutputStream().write(output.toByteArray());

	    } catch (Exception ex) {            
	        ex.printStackTrace();
	    }   
	}

	public ByteArrayOutputStream generatePDFTest() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		 
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		 
		contentStream.setFont(PDType1Font.COURIER, 12);
		contentStream.beginText();
		contentStream.showText("Hello World");
		contentStream.endText();
		contentStream.close();
		 
		document.save(output);
		document.close();
		
		return output;
	}
}
