package org.example.api;

import org.example.api.server.CustomServer;

public class ExampleApplication {
  public static void main(String[] args){
    new CustomServer(8080).start();
  }
}
