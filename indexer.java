package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class indexer {
	public static makeCollection mc = new makeCollection();
	public static makeKeyword mk = new makeKeyword();
	public void save(HashMap<Integer, HashMap<String, Integer>> hm, HashMap<String, Integer> count) throws IOException {
	
		FileOutputStream fileStream = new FileOutputStream("indexer.post");

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream);

		HashMap<String, String> result = new HashMap<>();

		double len = mc.files.length;
			
		for (String word : count.keySet()) {
			ArrayList<String> list = new ArrayList<>();
			for (int id = 0; id < len; id++) {
				HashMap<String, Integer> document = hm.get(id);
				double weight = 0;
				if (document.get(word) != null) { //해당 단어가 존재하면
					weight = Math.log10(len / (double) (count.get(word)));
					weight = weight * (double) document.get(word);
				}
				list.add(String.valueOf(id));
				list.add(String.valueOf(weight));
			}
			result.put(word, list.toString());
		}

		objectOutputStream.writeObject(result);

		objectOutputStream.close();
	}

	////////////////////////////////////////////////////////
	public void hashmap() throws IOException, ClassNotFoundException {
		FileInputStream fileStream = new FileInputStream("indexer.post");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);
		
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		
		System.out.println("읽어온 객체의 type ->"+object.getClass());
		
		HashMap hashMap = (HashMap)object;
		Iterator<String> it = hashMap.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = (String)hashMap.get(key);
			System.out.println(key+"->"+value);
		}
	
	}
}
