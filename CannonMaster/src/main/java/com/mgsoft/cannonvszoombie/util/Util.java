package com.mgsoft.cannonvszoombie.util;

import java.io.InputStream;
import java.net.URL;

import javafx.application.Platform;

public class Util {

	public static void runLater(Runnable r) {
		Platform.runLater(r);
	}

	public static InputStream getResource(String fileName) {

		return Util.class.getClassLoader().getResourceAsStream(fileName);
	}

}
