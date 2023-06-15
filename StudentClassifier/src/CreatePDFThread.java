import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

class CreatePDFThread extends Thread {
    private ArrayList<String> arrayList;
    private String filename;

    public CreatePDFThread(ArrayList<String> arrayList, String filename) {
        this.arrayList = arrayList;
        this.filename = filename;
    }

    @Override
    public void run() {
        
        Document doc = new Document();

        try {
            Path outputPath = Paths.get("C:\\Users\\Dell\\eclipse-workspace\\" + filename);
            PdfWriter.getInstance(doc, new FileOutputStream(outputPath.toFile()));
            doc.open();

            for (String element : arrayList) {
                doc.add(new Paragraph(element));
            }

            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
