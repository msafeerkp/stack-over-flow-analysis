package org.stackoverflow.analysis.startup;

import org.stackoverflow.analysis.service.StorageService;

public class Start {
	
	public static void main(String[] args) throws InterruptedException {
		
		StorageService.getInstance().start();
		
	}

}
