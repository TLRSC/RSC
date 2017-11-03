package tech.teslex.mpes.rsc.console.events;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConsoleUpdate extends Thread {

	private ConsoleUpdateEvent consoleUpdateEvent;

	public ConsoleUpdate(ConsoleUpdateEvent consoleUpdateEvent) {
		this.consoleUpdateEvent = consoleUpdateEvent;
	}

	@Override
	public void run() {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader("server.log"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String currentLine = null;

		while (true) {

			try {
				if ((currentLine = input.readLine()) != null) {
					consoleUpdateEvent.onText(currentLine);
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
}
