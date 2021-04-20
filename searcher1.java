package project;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class searcher1 {
public static indexer idx=new indexer();
	
	public void CalcSim() throws IOException, ClassNotFoundException {
		HashMap<String, String> similar=new HashMap<>();
		
		String title[]= {"떡", "라면", "아이스크림", "초밥", "파스타"};
		
		double id[] = new double[5]; //유사도
		double sim[] = new double[5];
		double idweight[]=new double[10];
		double weight[]=new double[5]; //w^2더한값 sqrt안의값
		double powweight[]=new double[5];
		
		for(int i=0;i<5;i++)
			id[i]=0;
		
		
		FileInputStream fileStream = new FileInputStream("indexer.post");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);
		
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		
		HashMap hashMap = (HashMap)object;
		Iterator<String> it = hashMap.keySet().iterator();
		
		String testString="라면에는 면, 분말 스프가 있다.";
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(testString, true);
		for(int i=0;i<kl.size();i++) {
			Keyword kwrd=kl.get(i);
			System.out.println(kwrd.getString() + " : " + kwrd.getCnt());
			similar.put(kwrd.getString(), null);
			
		}
		
		while(it.hasNext()) {
			String key = it.next();
			String value = (String)hashMap.get(key);

			for(String hashkey:similar.keySet()) {
				if(key.equals(hashkey)) {
					similar.put(hashkey, value);
				    System.out.println(hashkey+"value:"+value);
				    
					value =value.replace("[", "");//[]제거한 value
					value =value.replace("]", "");		
					value =value.replace(",", "");	
					
					String idweightstr[]=value.split(" ", 10);
					
					
					for(int i=0;i<10;i++) {//형변환
						idweight[i]=Double.valueOf(idweightstr[i]);
					}
					for(int i=0;i<5;i++) {
						weight[i]=idweight[2*i+1];
					}
					for(int i=0;i<5;i++) {
						id[i]+=weight[i];
						powweight[i]+=Math.pow(weight[i], 2);
					}
					
				}
			}
			
			for (int i = 0; i < 5; i++) {
				if (powweight[i] != 0)
					sim[i] = id[i] / (2 * Math.sqrt(powweight[i]));
			}

		}

		for(int i=0;i<5;i++)
			System.out.println("sim"+i+": "+sim[i]);//g확인용
		
		double rank[] = new double[5];
		for(int i=0;i<5;i++)
			rank[i]=1;
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(sim[i]>sim[j])
					rank[j]++;
			}
		}
		
		for(int i=0;i<5;i++) {
			if(rank[i]==1)
				System.out.println("1등: "+title[i]);
		}
		for(int i=0;i<5;i++) {
			if(rank[i]==2)
				System.out.println("2등: "+title[i]);
		}
		for(int i=0;i<5;i++) {
			if(rank[i]==3)
				System.out.println("3등: "+title[i]);
		}
		
	}
}
