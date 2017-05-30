package util;

import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;

import org.apache.jasper.runtime.ProtectedFunctionMapper;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * Html2PDF tool can transfer HTML file to PDF with third-party
 * 1. IText needs corresponding jar(Strict html file)
 * 2. PD4L needs jar too(not strict html file)
 * @author Dangdang
 *
 */
public class Html2PDFUtil {
	public static void main(String[] args) {
		test(new File("G:/Storm/apache-storm-src-0.10.2/docs/Tutorial.html"));
	}

	public static void test(File dir) {
		if (!dir.isDirectory()) {
			transFileWithPD4L(dir);
		} else {
			File[] listFiles = dir.listFiles();
			for (File fItem : listFiles) {
				if (fItem.isDirectory()) {
					test(fItem);
				} else {
					transFileWithPD4L(fItem);
				}
			}
		}

	}

	/**
	 * transfer a html file to PDF with IText tool, HTML file must strict html-file, <a><a/>
	 * 
	 * @param fItem
	 *            fItem must a raw file not a dirctory
	 */
	public static void transFileWithIText(File fItem) {

		String absolutePath = fItem.getAbsolutePath();
		System.out.println("abs " + absolutePath);
		if (absolutePath.endsWith(".html")) {
			int index = absolutePath.lastIndexOf('.');
			System.out.println("index " + index + "len " + absolutePath.length());
			String to = absolutePath.substring(0, index) + ".pdf";
			System.out.println("to " + to);
			Document document = new Document();
			try {
				// creation of the different writers
				// HtmlWriter.getInstance(document , System.out);
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(to));
				// we add some meta information to the document
				// document.addAuthor("Bruno Lowagie");
				// document.addSubject("This is the result of a Test.");
				// we open the document for writing
				document.open();
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(absolutePath),
						Charset.forName("UTF-8"));
				// document.add(new Paragraph("Hello world"));
			} catch (DocumentException de) {
				System.err.println(de.getMessage());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();

		}
	}

	/**
	 * transfer a html file to PDF with PD4L tool, HTML file can not be strict html-file, <a> no</a>
	 * 
	 * @param fItem
	 *            fItem must a raw file not a dirctory
	 */
	public static void transFileWithPD4L(File file) {
		int topValue = 10;
		int leftValue = 20;
		int rightValue = 10;
		int bottomValue = 10;
		int userSpaceWidth = 1300;

		String absolutePath = file.getAbsolutePath();
		if (absolutePath.endsWith(".html")) {
			String fileDir = absolutePath.substring(0, absolutePath.lastIndexOf('.'));
			String outPut = fileDir + ".pdf";
			System.out.println("outpath " + outPut);
			File toFile = new File(outPut);
			OutputStream outputStream;
			try {
				outputStream = new FileOutputStream(toFile);
				PD4ML pd4ml = new PD4ML();
				pd4ml.setHtmlWidth(userSpaceWidth);
				pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
				pd4ml.enableImgSplit(false);
				pd4ml.addStyle("BODY{margin: 0}", true);
				pd4ml.enableDebugInfo();
				pd4ml.render(new InputStreamReader(new FileInputStream(file)), outputStream);
				outputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}