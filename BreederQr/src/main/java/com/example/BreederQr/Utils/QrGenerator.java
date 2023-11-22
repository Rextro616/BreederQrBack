package com.example.BreederQr.Utils;

import com.example.BreederQr.models.animal.Animal;
import com.example.BreederQr.models.animal.AnimalWrapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QrGenerator {
    public static Path generateQRCode(AnimalWrapper animal) throws WriterException, IOException {
        String qrCodePath = "/Users/rextro/Documents/Github Repository/BreederQrBack/BreederQr/src/main/resources/files/animals/";
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

        return path;
    }
}

