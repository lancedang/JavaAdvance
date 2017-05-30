package dang.advance.parsexml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseDemo implements XmlDocument {
	public static void main(String[] args) {
		
	}

	@Override
	public void parseXml(String path) {
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			InputStream iStream = new FileInputStream(path);
			saxParser.parse(iStream, new MySaxHandler());
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class MySaxHandler extends DefaultHandler {
	boolean hasAttribute = false;
	Attributes attributes = null;

	public void startDocument() {

	}

	public void endDocument() {

	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if (qName.equals("users")) {
			return;
		}
		if (qName.equals("user")) {
			return;
		}

		if (attributes.getLength() > 0) {
			this.attributes = attributes;
			this.hasAttribute = true;
		}

	}

	public void endElement(String uri, String localName, String qName) {
		if (hasAttribute && (attributes != null)) {
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.print(attributes.getQName(0) + ":" + attributes.getValue(0));
			}
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.print(new String(ch, start, length));
	}

}