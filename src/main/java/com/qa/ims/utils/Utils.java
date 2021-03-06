package com.qa.ims.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

	private static final Logger LOGGER = LogManager.getLogger();

	private final Scanner scanner;

	public Utils(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public Utils() {
		scanner = new Scanner(System.in);
	}
	
	public void isContinue() {
		String message = System.lineSeparator() + "Press ENTER to continue";
		LOGGER.info(message);
		scanner.nextLine();
	}

	public Long getLong() {
		String input = null;
		Long longInput = null;
		do {
			try {
				input = getString();
				longInput = Long.parseLong(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (longInput == null);
		return longInput;
	}

	public String getString() {
		return scanner.nextLine();
	}

	public Double getDouble() {
		String input = null;
		Double doubleInput = null;
		do {
			try {
				input = getString();
				doubleInput = Double.parseDouble(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (doubleInput == null);
		return doubleInput;
	}

	public List<Long> getLongList() {
		String input = null;
		List<Long> listInput = new ArrayList<>();
		do {
			try {
				input = getString();
				String[] listInputString = input.split(" ");
				for (String s : listInputString) {
					listInput.add(Long.parseLong(s));
				}
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter numbers separated by space");
			}
		} while (listInput.isEmpty());
		return listInput;
	}

	public void clear() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				Runtime.getRuntime().exec("clear");
				LOGGER.info("\033[H\033[2J");  

			}
			String message = "Welcome to the Inventory Management System!" + System.lineSeparator();
			LOGGER.info(message);
		} catch (IOException | InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}
