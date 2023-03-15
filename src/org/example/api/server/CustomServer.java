package org.example.api.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomServer {
	private static final Logger log = LoggerFactory.getLogger(CustomServer.class);
	private final int port;

	public CustomServer(int port) {
		this.port = port;
	}

	public void start() {
		try (ServerSocket socket = new ServerSocket(port)) {
			log.info("[CustomServer] started {} port", port);

			Socket clientSocket;

			log.info("[CustomServer] waiting for client");

			while ((clientSocket = socket.accept()) != null) {
				log.info("[CustomServer] client connected!!");

				new Thread(new ClientRequestHandler(clientSocket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
