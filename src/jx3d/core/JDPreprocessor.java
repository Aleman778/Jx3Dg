package jx3d.core;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jx3d.desktop.DesktopFiles;

/**
 * Javadoc preprocessor for inheriting documentation from other methods.
 * Syntax: {@inheritDoc Class#method()}
 * 
 * @author alema
 *
 */
public class JDPreprocessor {
	
	public static final String LOCAL_DIR = new File("").getAbsolutePath() + File.separator;
	public static final DesktopFiles df = new DesktopFiles(null);
	public static File[] entries;

	public static int numLines;
	
	public static void main(String[] args) {
		File projectFolder = new File(LOCAL_DIR + "src/");
		entries = listAllFiles(projectFolder);
		for (File s: entries) {
			//preprocess(s);
			//removeFile(s);
			countNumLines(s);
		}
		System.out.println("No. Lines: " + numLines);
	}
	
	public static void preprocess(File f) {
		df.open(Constants.READ, f.getAbsolutePath());
		String file = df.read(); 
		df.close();
		
		int idx = -1;
		while ((idx = file.indexOf("@inheritDoc")) != -1) {
			System.out.println("Preprocessing: " + f.getAbsolutePath());
			int start = 0; int end = 0;
			start = file.lastIndexOf("\n", idx);
			end = file.indexOf("\n", idx);
			String line = file.substring(start, end);
			file = file.replace(line, "");
			
			Pattern p = Pattern.compile("\\w*#(\\w?\\d?,?\\s?\\)?\\(?)*");
			Matcher m = p.matcher(line);
			System.out.println("FOUND: " + line);
			if (m.find()) {
				System.out.println(m.group(0));
			}
					
		}
		/*
		df.open(Constants.WRITE, f.getAbsolutePath().replace(".java", ".jdp"));
		
		df.close();*/
	}
	
	public static void removeFile(File f) {
		System.out.println("Removing: " + f.getAbsolutePath());
		if (f.getAbsolutePath().contains(".java")) {
			File nf = new File(f.getAbsolutePath().replace(".java", ".jdp"));
			nf.delete();
		}
	}
	
	public static void countNumLines(File f) {
		if (f.getAbsolutePath().contains(".java")) {
			if (df.open(Constants.READ, f.getAbsolutePath())) {
				String line = "";
				while ((line = df.readln()) != null) {
					if (!line.equals(""))
						numLines++;	
				}
				df.close();
			}
		}
		System.out.println("Counting: " + f.getAbsolutePath() + "(so far: " + numLines +")");
	}
	
	public static String retreiveDoc(File f, String method) {
		
		return null;
	}
	
	public static File[] listAllFiles(File dir) {
		File[] entries = dir.listFiles();
		if (entries == null) {
			return new File[] {};
		}
		
		if (entries.length == 0) {
			return new File[] {};
		}
		
		ArrayList<File> list = new ArrayList<>();
		for (File s: entries) {
			list.add(s);
		}
		for (File s: entries) {
			File[] f = listAllFiles(s);
			for (File s2: f) {
				list.add(s2);
			}
		}
		ArrayList<File> remove = new ArrayList<>();
		for (File s: list) {
			if (s.isDirectory()) {
				remove.add(s);
			}
		}
		list.removeAll(remove);
		
		return list.toArray(new File[list.size()]);
	}
}
