package com.cobus.chapter_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Chapter3Main {
	public static void main(String[] args) {
		process(()-> System.out.println("Am I a Runnable Object?"));
		
		try {
			String oneLine = processFile((BufferedReader br) -> br.readLine());
			String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
			
			System.out.println(oneLine);
			System.out.println(twoLine);
			
			Comparator c;
			Comparator.comparing(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void process(Runnable r) {
		r.run();
	}
	
	public String processFileOrg() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			return br.readLine();
		}
	}
	
	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			return p.process(br);
		}
	}
}
