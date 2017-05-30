package dang.advance.parsexml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParseDemo implements XmlDocument {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "src/main/resource/ParseXML.xml";
		new DomParseDemo().parseXml(path);
	}

	@Override
	public void parseXml(String path) {
		// TODO Auto-generated method stub
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(path);

			NodeList users = document.getChildNodes();

			for (int i = 0; i < users.getLength(); i++) {
				Node user = users.item(i);
				NodeList userInfo = user.getChildNodes();

				for (int j = 0; j < userInfo.getLength(); j++) {
					Node node = userInfo.item(j);
					NodeList userMeta = node.getChildNodes();// 底层
					for (int k = 0; k < userMeta.getLength(); k++) {
						if (userMeta.item(k).getNodeName() != "#text")
							System.out
									.println(userMeta.item(k).getNodeName() + " :" + userMeta.item(k).getTextContent());
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
