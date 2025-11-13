package com.app.file_opearations;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;
public class CountOperation {
	public static void main(String[] args) throws IOException {
		String filepath="names.txt";
		int count=0;
		try(BufferedReader br=new BufferedReader(new FileReader(filepath))){
			String line;
			while((line=br.readLine())!=null){
				if (line.trim().equalsIgnoreCase("india")){
					count++;
				}
			}
			System.out.println("Count: "+count);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
	}
		
		//using functional programming
		
		long count1=Files.lines(Paths.get(filepath))
				.map(r->r.trim())
				.filter(r->r.toLowerCase().contains("india"))
				.count();
		
		System.out.println("Count: "+count1);
		}
}
		
		
