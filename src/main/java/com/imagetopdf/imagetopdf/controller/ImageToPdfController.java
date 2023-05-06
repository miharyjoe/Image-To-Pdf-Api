package com.imagetopdf.imagetopdf.controller;

import com.imagetopdf.imagetopdf.service.ImageToPdfService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class ImageToPdfController {
    private final ImageToPdfService pdfService;

    @PostMapping("/convert")
    public ResponseEntity<byte[]> convertImagesToPdf(@RequestParam("images") MultipartFile[] images) {
        byte[] pdfFile = pdfService.convertImagesToPdf(images);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "converted.pdf");
        headers.setContentLength(pdfFile.length);
        return new ResponseEntity<byte[]>(pdfFile, headers, HttpStatus.OK);
    }
}
