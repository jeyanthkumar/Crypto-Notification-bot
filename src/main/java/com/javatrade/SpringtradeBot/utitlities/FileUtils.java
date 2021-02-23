package com.javatrade.SpringtradeBot.utitlities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

	public static ArrayList<String> feedPairsFromTextFile(String loc) throws IOException {
		File file = new File(loc);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		ArrayList<String> pairs = new ArrayList<String>();
		while ((st = br.readLine()) != null)
			pairs.add(st);
		return pairs;
	}
}
