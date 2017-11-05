package tech.teslex.mpes.rsc.console.events.console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleUpdate extends Thread {

	private List<ConsoleUpdateEvent> listeners = new ArrayList<>();

	private String consoleFile;

	public ConsoleUpdate(String consoleFile) {
		this.consoleFile = consoleFile;
	}

	@Override
	public void run() {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(consoleFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String currentLine = null;

		while (true) {

			try {
				if ((currentLine = input.readLine()) != null) {
					String finalCurrentLine = currentLine;
					listeners.forEach(e -> e.onText(finalCurrentLine));
					continue;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}

		}

		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addUpdateListener(ConsoleUpdateEvent consoleUpdateEvent) {
		this.listeners.add(consoleUpdateEvent);
	}
}
