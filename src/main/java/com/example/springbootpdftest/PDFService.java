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
import java.util.List;

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
            List<SvcTableRow> rows = List.of(
                    new SvcTableRow("Страховка","\"GROSS SUG'URTA KOMPANIYASI\" AJ"),
                    new SvcTableRow("Серия и номер полиса","GIU-194325"),
                    new SvcTableRow("Тип страховки","Без ограничений"),
                    new SvcTableRow("Сумма страховки","40000000 (месячный взнос - 168000)"),
                    new SvcTableRow("Срок действия страховки","02.03.2022-01.03.2023"),
                    new SvcTableRow("Модель автотранспорта","COBALT"),
                    new SvcTableRow("Гос.номер","01R425SB")
                    );
            context.setVariable("docNumber", "1630");
            context.setVariable("createdDate", "2022-02-03");
            context.setVariable("userFullName", "KURBANOV BEXZOD ZAFARALIYEVICH");
            context.setVariable("pinfl", "32410996600028");
            context.setVariable("serviceName","Проверка действия страхового полиса автотранспорта");
            context.setVariable("data", rows);
            String renderedContent = engine.process("index", context);
            HtmlConverter.convertToPdf(renderedContent, outputStream);
            return new FileSystemResource(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
