package com.ness.batchsourcetransformer;

import java.io.*;

import com.ness.sourcetransformer.java.JavaSourceCodeTransformer;

public class Runner {
	private final JavaSourceCodeTransformer codeTransformer;

	private Runner() {
		codeTransformer = new JavaSourceCodeTransformer();
	}

	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.run(args[0]);
	}

	private void run(String path) {
		File dir = new File(path);
		for(File file : dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".tps");
			}
		})) {
			final String filePath = file.getAbsolutePath();
			System.out.println(filePath);
			loadAndTranformSource(file);
		}
	}

	private void loadAndTranformSource(File file) {
		String fileContents = readFileContents(file);
		String code = codeTransformer.transformCode(fileContents);
		System.out.println(code);
	}

	private String readFileContents(File file) {
		StringBuilder fileContents = new StringBuilder();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line;
			while ((line = reader.readLine()) != null) {
				fileContents.append(line).append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return fileContents.toString();
	}
}
