package com.xiaogangkui.util.common;

import com.github.pagehelper.util.StringUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mysql.cj.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

public class QRCodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * map key 为 url value 为图片名称
     * @param map
     * @param response
     */
    public static void urlToqrcodeZip(Map<String, String> map, HttpServletResponse response) {
        //通过活动标识和商户id查询活动
        ZipOutputStream zos = null;
        try {
            String downloadFilename = URLEncoder.encode("download", "UTF-8");//转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename + ".zip");// 设置在下载框默认显示的文件名
            zos = new ZipOutputStream(response.getOutputStream());
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String name =  StringUtil.isNotEmpty(next.getValue())?next.getValue():String.valueOf(System.currentTimeMillis());
                zos.putNextEntry(new ZipEntry(  name+ ".png"));//命名
                URL url = new URL(next.getKey());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();//利用HttpURLConnection对象,我们可以从网络中获取网页数据.
                conn.setDoInput(true);
                conn.connect();
                InputStream in = conn.getInputStream();
                BufferedImage bi = ImageIO.read(in);
                bi = addNameToImage(next.getValue(), bi);
                String format = "png";
                if (!ImageIO.write(bi, format, zos)) {
                    throw new IOException("Could not write an image of format " + format);
                }
            }
            zos.flush();
            zos.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zos != null) {
                try {
                    zos.flush();
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void getBarCodeImgByUrl(String url, OutputStream os,String productName) throws WriterException, IOException {
        //二维码参数
        int width = 400; // 图像宽度
        int height = 400; // 图像高度
        String format = "png";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix;
        bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
        writeToStream(bitMatrix, format, os,productName);
    }
    static void writeToStream(BitMatrix matrix, String format, OutputStream stream,String productName) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        image =  addNameToImage(productName,image);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }


    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static BufferedImage addNameToImage(String productName,BufferedImage image){
        if (productName != null && !productName.equals("")) {
            //新的图片，把带logo的二维码下面加上文字
            BufferedImage outImage = new BufferedImage(500, 500, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D outg = outImage.createGraphics();
            //画二维码到新的面板
            outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            //画文字到新的面板
            outg.setColor(Color.BLACK);
            outg.setFont(new Font("宋体",Font.BOLD,30)); //字体、字型、字号
            int strWidth = outg.getFontMetrics().stringWidth(productName);
            if (strWidth > 499) {
// //长度过长就截取前面部分
// outg.drawString(productName, 0, image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 5 ); //画文字
                //长度过长就换行
                String productName1 = productName.substring(0, productName.length()/2);
                String productName2 = productName.substring(productName.length()/2, productName.length());
                int strWidth1 = outg.getFontMetrics().stringWidth(productName1);
                int strWidth2 = outg.getFontMetrics().stringWidth(productName2);
                outg.drawString(productName1, 220  - strWidth1/2, image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 12 );
                BufferedImage outImage2 = new BufferedImage(500, 500, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg2 = outImage2.createGraphics();
                outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                outg2.setColor(Color.BLACK);
                outg2.setFont(new Font("宋体",Font.BOLD,30)); //字体、字型、字号
                outg2.drawString(productName2, 220  - strWidth2/2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight())/2 + 5 );
                outg2.dispose();
                outImage2.flush();
                outImage = outImage2;
            }else {
                outg.drawString(productName, 220  - strWidth/2 , image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 12 ); //画文字
            }
            outg.dispose();
            outImage.flush();
            image = outImage;
        }
        return image;

    }

}
