package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class makeKeyword {
public static makeCollection mc = new makeCollection();
	
	String[] kkmastr = new String[mc.files.length];
	Keyword wkrd;
	String[][] word;
	   public void kkma(File files[], String str[], HashMap<Integer, HashMap<String, Integer>> document, HashMap<String, Integer> count) {
		   
		   //document <문서, 단어와 빈출>
		   //count는  <단어, 빈출>
		   
		  // String[] kkmastr = new String[files.length];
	      int k=0;
	      for(int i=0; i<mc.files.length;i++) {
	    	  kkmastr[i]="";
	    	  document.put(i,new HashMap<>()); //i-newHashMap매칭
	      }

	      for(int i=0; i<mc.files.length;i++) {
	    	  HashMap<String,Integer> word = document.get(i);
	    	  System.out.println(word);
	    	  
		      String testString = str[i];
	
		      KeywordExtractor ke = new KeywordExtractor();
		      KeywordList kl = ke.extractKeyword(testString, true);
	
		    //  System.out.println("kl" + kl.size());
	
		      for (int j = 0; j < kl.size(); j++) {
		         Keyword kwrd = kl.get(j);
		         System.out.print(kwrd.getString() + " : " + kwrd.getCnt() + "#");
		         kkmastr[i] += kwrd.getString() + " : " + kwrd.getCnt() + "#";
		         word.put(kwrd.getString(), kwrd.getCnt());
		         count.put(kwrd.getString(), count.getOrDefault(kwrd.getString(), 0)+1);
		         
		         //word[k] = kwrd.getString();
		        // System.out.println(word[k]);
		         k++;
		      }
		   }
	      
	      
	      //indexer위함
//	      
//	      String word[] = new String[k];
//	      int p=0;
//	      for(int i=0; i<mc.files.length;i++) {
//		      String testString = str[i];
//	
//		      KeywordExtractor ke = new KeywordExtractor();
//		      KeywordList kl = ke.extractKeyword(testString, true);
//	
//		      System.out.println("kl" + kl.size());
//	
//		      for (int j = 0; j < kl.size(); j++) {
//		         Keyword kwrd = kl.get(j);
//		         System.out.print(kwrd.getString() + " : " + kwrd.getCnt() + "#");
//		    //     kkmastr[i] += kwrd.getString() + " : " + kwrd.getCnt() + "#";
//		      //   word[p] = kwrd.getString();
//		        // tf[p] = 
//		        // System.out.println(word[k]);
//		         p++;
//		      }
//		   }
	      
	      
	   }
	         
	         
	   
	   public void makexml(File files[], String food[]) throws ParserConfigurationException, FileNotFoundException, TransformerException {
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
	      title.appendChild(document.createTextNode(food[i]));//내용
	      doc.appendChild(title);   
	      
	      Element body = document.createElement("body");
	      body.appendChild(document.createTextNode(kkmastr[i]));//내용
	      doc.appendChild(body);   
	      }

	      TransformerFactory transformerFactory = TransformerFactory.newInstance();
	      
	      Transformer transformer = transformerFactory.newTransformer();
	      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	      
	      DOMSource source = new DOMSource(document);
	      StreamResult result = new StreamResult(new FileOutputStream(new File("C:\\Users\\shon1\\simpleIR\\index.xml")));
	      
	      transformer.transform(source, result);
	      
	   }

}
