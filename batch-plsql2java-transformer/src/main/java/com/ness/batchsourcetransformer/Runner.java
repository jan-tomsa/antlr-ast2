package com.ness.batchsourcetransformer;

import java.io.*;

public class Runner {
	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.run("d:\\work\\RUIAN\\SVN_17\\trunk\\ISUI-Oracle-full\\trunk\\application\\schema\\ui_apl\\type\\statistiky");
	}

	private void run(String path) {
		File dir = new File(path);
		for(File file : dir.listFiles()) {
			final String filePath = file.getAbsolutePath();
			System.out.println(filePath);
			loadAndTranformSource(file);
		}
	}

	private void loadAndTranformSource(File file) {
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
