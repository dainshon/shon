package practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class genSnippet {
	
File file = new File("C:\\Users\\shon1\\OneDrive\\바탕 화면\\input.txt");
	

public void getfile() throws IOException
{
	FileReader fr = new FileReader(file);
	BufferedReader br = new BufferedReader(fr);
	String line="";
	int num[]=new int[5];
	String input[] = new String[5];
	
//	String input1[]=new String[5];
//	String input2[]=new String[5];
//	String input3[]=new String[5];
//	String input4[]=new String[5];
//	String input5[]=new String[5];
	
	int i=0;
	while((line=br.readLine())!=null) {
		input[i] = line;
		System.out.println(line);
		i++;
	}
	
	String[] input1=input[1].split("");
	String[] input2=input[1].split("");
	String[] input3=input[1].split("");
	String[] input4=input[1].split("");
	String[] input5=input[1].split("");
	
	System.out.println("입력: ");
	Scanner sc = new Scanner(System.in);
	
	String scan = sc.next();
	
	String word[] = scan.split(" ");
	
	
	
}	
}
