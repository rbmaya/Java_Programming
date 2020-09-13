package JumperServices;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static JumperConstants.Constants.COUNT_OF_RECORDS;

public class PdfFileRecordsService implements RecordsService{

    @Override
    public void save(Integer score) {
        Properties records = new Properties();
        try {
            records.load(new FileInputStream("src/main/resources/records.properties"));
        } catch (Exception ignored) {
        }

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\rmaya\\Java\\lab2_Jumper\\src\\main\\resources\\records.pdf"));
        } catch (Exception ignored) {
        }

        document.open();
        try {
            Paragraph paragraph = new Paragraph("Jumper Records:",
                    FontFactory.getFont(FontFactory.COURIER_BOLD, 25, Font.BOLD, CMYKColor.BLUE));
            paragraph.setAlignment(paragraph.ALIGN_CENTER);
            document.add(paragraph);
        } catch (DocumentException ignored) {
        }

        for (int i = 1; i <= COUNT_OF_RECORDS; ++i) {
            try {
                document.add(new Paragraph(Integer.valueOf(i).toString() + ". " + records.getProperty(Integer.valueOf(i).toString()),
                        FontFactory.getFont(FontFactory.COURIER_BOLD, 14, Font.BOLD, CMYKColor.BLACK)));
            } catch (DocumentException ignored) {
            }
        }

        try {
            document.add(new Paragraph("Last score: " + records.getProperty(Integer.valueOf(0).toString()),
                    FontFactory.getFont(FontFactory.COURIER_BOLD, 14, Font.BOLD, CMYKColor.BLUE)));
        } catch (DocumentException ignored) {
        }

        document.close();
    }
}
