package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

public class EnvironmentCreator {

	public void create() throws URISyntaxException, IOException {
		final String folderName = "languages";
		final String englishPath = folderName + "/English.json";
		final String italianPath = folderName + "/Italian.json";
		File folder = new File(folderName);
		String ok = ClassLoader.getSystemClassLoader().getResource(englishPath).getPath();
		InputStream engInput = ClassLoader.getSystemClassLoader().getResourceAsStream(englishPath);
		InputStream itaInput = ClassLoader.getSystemClassLoader().getResourceAsStream(italianPath);
		File engOut = new File(englishPath);
		File itaOut = new File(italianPath);
		try {
			FileUtils.copyToFile(engInput, engOut);
			FileUtils.copyToFile(itaInput, itaOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
