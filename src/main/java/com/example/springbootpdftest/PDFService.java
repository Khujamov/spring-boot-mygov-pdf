package com.example.springbootpdftest;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PDFService {

    private final TemplateEngine engine;

    public Resource create() {
        File file;
        Path path = Paths.get("C:\\Users\\khumoyun\\work\\spring-boot-pdf-test\\src\\main\\resources\\app.pdf");
        file = path.toFile();
        try (OutputStream outputStream = new FileOutputStream(file)) {
            Context context = new Context();
            context.setVariable("gov", "govNumber");
            context.setVariable("number", "01R425SB");
            String renderedContent = engine.process("index", context);
            HtmlConverter.convertToPdf(renderedContent, outputStream);
            return new FileSystemResource(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
