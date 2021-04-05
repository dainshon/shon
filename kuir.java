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
		searcher sc = new searcher();
		HashMap<Integer, HashMap<String, Integer>> hm = new HashMap<>();
		HashMap<String, Integer> count = new HashMap<>();


		mc.getfile();
		mc.makexml();

		mk.kkma(mc.files, mc.str, hm, count);
		mk.makexml(mc.files, mc.food);
		
		
		idx.save(hm,count);
		idx.hashmap();

		sc.CalcSim();
	}

}
