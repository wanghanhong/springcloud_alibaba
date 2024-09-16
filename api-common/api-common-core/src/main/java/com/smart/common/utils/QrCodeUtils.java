package com.smart.common.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类 使用ZXingjar包
 *
 * @author 三多
 * @Time 2020/6/19
 */
@Component
@Data
public class QrCodeUtils {
    /** 默认宽为300*/
    private Integer width = 600;
    /**默认高为300*/
    private Integer height = 600;
     /**默认二维码图片格式*/
    private String imageFormat = "png";
    /** 默认二维码字符编码*/
    private String charType = "utf-8";
     /**默认二维码的容错级别*/

    /**容错等级 L、M、Q、H 其中 L 为最低, H 为最高*/
    private ErrorCorrectionLevel correctionLevel = ErrorCorrectionLevel.M;
     /**二维码与图片的边缘*/
    private Integer margin = 0;
     /**二维码参数*/
    private Map<EncodeHintType, Object> encodeHits = new HashMap<EncodeHintType, Object>();


    public QrCodeUtils(Integer width, Integer height, String imageFormat, String charType,
                       ErrorCorrectionLevel correctionLevel, Integer margin) {
        if (width != null) {
            this.width = width;
        }
        if (height != null) {
            this.height = height;
        }
        if (imageFormat != null) {
            this.imageFormat = imageFormat;
        }
        if (charType != null) {
            this.charType = charType;
        }
        if (correctionLevel != null) {
            this.correctionLevel = correctionLevel;
        }
        if (margin != null) {
            this.margin = margin;
        }
    }

    public QrCodeUtils(Integer width, Integer height, String imageFormat, String charType,
                       ErrorCorrectionLevel correctionLevel) {
        this(width, height, imageFormat, charType, correctionLevel, null);
    }

    public QrCodeUtils(Integer width, Integer height, String imageFormat, String charType, Integer margin) {
        this(width, height, imageFormat, charType, null, margin);
    }

    public QrCodeUtils(Integer width, Integer height, String imageFormat, String charType) {
        this(width, height, imageFormat, charType, null, null);
    }

    public QrCodeUtils(Integer width, Integer height, String imageFormat) {
        this(width, height, imageFormat, null, null, null);
    }

    public QrCodeUtils(Integer width, Integer height) {
        this(width, height, null, null, null, null);
    }

    public QrCodeUtils() {
    }

    /**
     * 初始化二维码的参数
     */
    private void initialParameters() {
        // 字符编码
        encodeHits.put(EncodeHintType.CHARACTER_SET, this.charType);
        // 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        encodeHits.put(EncodeHintType.ERROR_CORRECTION, this.correctionLevel);
        // 二维码与图片边距
        encodeHits.put(EncodeHintType.MARGIN, margin);
    }

    /**
     * 得到二维码图片
     * @param content
     * @return
     */
    public BufferedImage getBufferedImage(String content) {
        initialParameters();
        BufferedImage bufferedImage = null;
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, this.width,
                    this.height, this.encodeHits);


            //去掉白边
            int[] rec = bitMatrix.getEnclosingRectangle();
            int resWidth = rec[2] + 1;
            int resHeight = rec[3] + 1;
            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
            resMatrix.clear();
            for (int i = 0; i < resWidth; i++) {
                for (int j = 0; j < resHeight; j++) {
                    if (bitMatrix.get(i + rec[0], j + rec[1])) {
                        resMatrix.set(i, j);
                    }
                }
            }
            //2
            int width = resMatrix.getWidth();
            int height = resMatrix.getHeight();
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bufferedImage.setRGB(x, y, resMatrix.get(x, y) == true ?
                            Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }
            //去掉白边

            // bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
        return bufferedImage;
    }

    // 将二维码保存到输出流中
    public void writeToStream(String content, OutputStream os) {
        initialParameters();
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, this.width, this.height,
                    this.encodeHits);
            MatrixToImageWriter.writeToStream(matrix, this.imageFormat, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 将二维码图片保存为文件
    public void createQrImage(String content, File file) {
        initialParameters();
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, this.width, this.height, this.encodeHits);
            MatrixToImageWriter.writeToFile(matrix, this.imageFormat, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将二维码图片保存到指定路径
     * @param content
     * @param path
     */
    public void createQrImage(String content, String path) {
        initialParameters();
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, this.width, this.height, this.encodeHits);
            MatrixToImageWriter.writeToPath(matrix, this.imageFormat, new File(path).toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新创建
     *
     * @param content
     * @param path
     */
    public void newCreateQrImage(String content, String path) {
        initialParameters();
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, this.width, this.height, this.encodeHits);
            // MatrixToImageWriter.writeToPath(matrix, this.imageFormat, new File(path).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 识别图片二维码
     *
     * @param file
     * @return
     */
    public String decodeQrImage(File file) {
        String content = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(file));
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap image = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> decodeHits = new HashMap<DecodeHintType, Object>();
            decodeHits.put(DecodeHintType.CHARACTER_SET, this.charType);
            Result result = new MultiFormatReader().decode(image, decodeHits);
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
