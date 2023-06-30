package org.example.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class QRCodeGenerator {
    private static final Path QR_CODE_IMAGE_PATH = Paths.get("./QRUser.png"); //путь в котором сохраняется qr
    public String generateQR(String text, int width, int height) {
        String strQR = null;
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", QR_CODE_IMAGE_PATH);
            byte[] QRBytes = Files.readAllBytes(QR_CODE_IMAGE_PATH);
            strQR = Base64.getEncoder().encodeToString(QRBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strQR;
    }

    public byte[] getQRImageFromString(String strQR) {
        byte[] imageBytes = Base64.getDecoder().decode(strQR);
        try {
            Files.write(QR_CODE_IMAGE_PATH, imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageBytes;
    }
}
