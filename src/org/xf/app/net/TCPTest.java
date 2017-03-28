package org.xf.app.net;

import java.net.Socket;

//
public class TCPTest {
	// 
	public final static int DEFAULT_PORT = 1234;

	public static void main(String[] args) {
		// 
		int port = DEFAULT_PORT;
		// 
		String[]  str = new String[2];
		str[0] = "192.168.45.43";
		str[1] = "1234";
		if (str.length > 1) {
			// 
			if (str.length >= 2) {
				try {
					port = Integer.parseInt(str[1]);
				} catch (NumberFormatException e) {
					System.err.println("Invalid port specification; using"
							+ " default port of " + DEFAULT_PORT);
					port = DEFAULT_PORT;
				}
			}
			// 
			try {
				new EchoClient(new Socket(str[0], port)).start();
			} catch (Exception e) {
				System.err.println("Could not create client socket!");
			}
		}
		// 
		else {
			System.out.println("Usage: java TCPTest <hostname> [<port>]");
			System.exit(0);
		}
	} // main
} // TCPTest