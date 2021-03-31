package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class makeCollection {
	File dir = new File("C:\\Users\\shon1\\OneDrive\\바탕 화면\\2주차 실습 html");
	File files[] = dir.listFiles();

	String str[] = new String[files.length];
	String food[] = new String[files.length];
	
	public void getfile() throws IOException{
		for (int i = 0; i < files.length - 2; i++) {
			str[i] = "";
			food[i] = "";
		}

		for (int i = 0; i < files.length; i++) {
			String filename = files[i].getName();
			int idx=filename.lastIndexOf(".");
			String _filename=filename.substring(0, idx);
			
			FileReader filereader = new FileReader(files[i]);
			BufferedReader bufReader = new BufferedReader(filereader);

			String line="";
			line=bufReader.readLine();

			while((line=bufReader.readLine())!=null) {
			String _line = line.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");

			final Pattern pattern = Pattern.compile("<p>(.+?)</p>");
			final Pattern pattern1 = Pattern.compile("<title>(.+?)</title>");
			Matcher matcher = pattern.matcher(line);
			Matcher matcher1 = pattern1.matcher(line);
			
			while(matcher1.find()) {
				food[i] = matcher1.group(1);
			}
			
			while(matcher.find()) {
				str[i] += (matcher.group(1)+"\n");
			}

			}
			
		//	System.out.println(str[i]);
			
		}
				
	}
	
	public void makexml() throws ParserConfigurationException, FileNotFoundException, TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document document = docBuilder.newDocument();
		
		Element docs = document.createElement("docs");
		document.appendChild(docs);

		for(int i=0;i<files.length;i++) {
			
			Element doc = document.createElement("doc");
			docs.appendChild(doc);
			String j= Integer.toString(i);
			
		doc.setAttribute("id", j);

		Element title = document.createElement("title");
		title.appendChild(document.createTextNode(food[i]));//����
		doc.appendChild(title);	
		
		Element body = document.createElement("body");
		body.appendChild(document.createTextNode(str[i]));//����
		doc.appendChild(body);	
		
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new FileOutputStream(new File("C:\\Users\\shon1\\simpleIR\\collection.xml")));
		
		transformer.transform(source, result);
			}
}
