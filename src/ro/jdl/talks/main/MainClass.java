package ro.jdl.talks.main;

import ro.jdl.talks.ui.*;

public class MainClass {

	/*
	 * This is the entry point of the application
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Welcome to pancake presentation tool");
		
		ControlPanel window = new ControlPanel();
		window.showWindow();
	}
}
