package org.xf.app.net;

import java.io.*;
import java.net.*;
public class EchoClient extends AbstractConnection implements Runnable {
	// 
	private Thread exec;
 
	// 
	public EchoClient(Socket s) {
		super(s);
		exec = new Thread(this);
	}

	public void start() {
		exec.start();
	}

	public void run() {
		// 
		if (!open()) {
			System.out.println("Could not open connection.");
			return;
		}
		// 
		final String sentinel = "ZZZ";
		// 
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		// 
		String input = "";
		while (true) {
			System.out.print("Enter a string, or '" + sentinel + "' to quit: ");
			try {
				input = reader.readLine();
			} catch (IOException e) {
				System.err.println("General I/O error; client terminating.");
				close(true);
				return;
			}
			//
			if (sentinel.equals(input)) {
				send(END_OF_TRANSMISSION, "");
				close(false);
				return;
			}
			//
			send("Reverse", input);
			recv();
			//
			System.out.println(parseMessage());
		}
	}
} // EchoClient