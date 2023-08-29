package cn.lzx.pdf_operate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 操作PDF
 *
 * @author lzx
 * @since 2023/8/25
 */
public class PdfTest {

    /**
     * 分离PDF
     */
    @Test
    public void splitPdfTest() {
        String resPdfFile = "D:\\workspace\\code\\backend\\linzx\\linzx-learning\\file\\绿色家居产业园标准化厂房二期2023.08.25.pdf";
        String savePath = "D:/pdf/22绿色家居产业园标准化厂房二期2023.08.25.pdf";
        splitPDFFile(resPdfFile, savePath, 99, 355);
    }

    /**
     * 截取pdfFile的第from页至第end页，组成一个新的文件名
     *
     * @param savePath 新PDF
     * @param from     起始页
     * @param end      结束页
     */
    public void splitPDFFile(String resPdfFile, String savePath, int from, int end) {
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(resPdfFile);
            int n = reader.getNumberOfPages();
            if (end == 0) {
                end = n;
            }
            ArrayList<String> savePaths = new ArrayList<String>();
            savePaths.add(savePath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savePaths.get(0)));
            document.open();
            for (int j = from; j <= end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
