package com.unifi.fattureApp.App;

import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) throws UnknownHostException {
		
		//Launch UI
//		new MainWindowUI(new MongoUiComunication());
		new MongoUiComunication();
		System.out.println("Fatture-app terminates");
		

		
	}
}