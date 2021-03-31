package project;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class kuir {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, ClassNotFoundException {
		// TODO Auto-generated method stub
		makeCollection mc = new makeCollection();
		makeKeyword mk = new makeKeyword();
		indexer idx = new indexer();
		HashMap<Integer, HashMap<String, Integer>> hm = new HashMap<>();
		HashMap<String, Integer> count = new HashMap<>();
		
//		String filePath = args[1];

		mc.getfile();
		mc.makexml();

		mk.kkma(mc.files, mc.str, hm, count);
		mk.makexml(mc.files, mc.food);
		
//		System.out.println("");
//		for(Integer id : hm.keySet()) {
//			System.out.println("======"+id+"=======");
//			for(String key :  hm.get(id).keySet()) {
//				System.out.println(key+" : "+hm.get(id).get(key));
//			}
//		}
//		
//		System.out.println("몇개의 문서에서 등장하는지");
//		for(String word : count.keySet()) {
//			System.out.println(word+" : "+count.get(word));
//		}
		
		idx.save(hm,count);
		idx.hashmap();


	}

}
