package com.akvone.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorld {
  public void greet(String name) {
    System.out.println("Hello, " + name);
  }
}
