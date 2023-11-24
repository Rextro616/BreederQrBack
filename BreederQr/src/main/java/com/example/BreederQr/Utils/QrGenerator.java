package com.example.BreederQr.Utils;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.animal.AnimalWrapper;
import com.example.BreederQr.services.cloudinary.FileUpload;
import com.example.BreederQr.services.cloudinary.FileUploadImpl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
@AllArgsConstructor
@Component
public class QrGenerator {

    FileUploadImpl fileUpload;

    public Path generateQRCode(AnimalWrapper animal) throws WriterException, IOException {
        String qrCodePath = "/Users/rextro/Documents/Github Repository/BreederQrBack/BreederQr/src/main/resources/files/qr/";
        String qrCodeName = qrCodePath+animal.getName()+"-QRCODE.png";

        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                        "Nombre: "+animal.getName()+ "\n"+
                        "Nacimiento: "+animal.getBirthday()+ "\n"+
                        "Genero: "+animal.getGender()+ "\n" +
                        "Registro: "+animal.getRegisterNumber()+ "\n" +
                        "Descripción: "+animal.getDescription(),
                BarcodeFormat.QR_CODE, 400, 400);

        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        File file = new File(String.valueOf(path));

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("archivo", new FileBody(file));


        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "image/png", IOUtils.toByteArray(input));

        return Path.of(fileUpload.uploadFile(multipartFile));
    }
}

