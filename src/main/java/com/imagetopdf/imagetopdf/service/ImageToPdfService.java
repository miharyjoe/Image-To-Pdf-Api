package com.imagetopdf.imagetopdf.service;

import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageToPdfService {
    public byte[] convertImagesToPdf(MultipartFile[] images) {
        try {
            PDDocument document = new PDDocument();
            for (MultipartFile image : images) {
                PDPage page = new PDPage();
                document.addPage(page);
                PDImageXObject imageObject = PDImageXObject.createFromByteArray(document, image.getBytes(), image.getOriginalFilename());
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    float scale = Math.min(page.getMediaBox().getWidth() / imageObject.getWidth(),
                            page.getMediaBox().getHeight() / imageObject.getHeight());
                    float x = (page.getMediaBox().getWidth() - (imageObject.getWidth() * scale)) / 2;
                    float y = (page.getMediaBox().getHeight() - (imageObject.getHeight() * scale)) / 2;
                    contentStream.drawImage(imageObject, x, y, imageObject.getWidth() * scale, imageObject.getHeight() * scale);
                }
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            document.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error converting images to PDF", e);
        }
    }
}
