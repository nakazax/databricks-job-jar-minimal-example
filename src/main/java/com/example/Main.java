package com.example;

public class Main {
  public static void main(String[] args) {
    try {
      run(args);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public static void run(String[] args) {
    if (args.length != 2) {
      System.err.println("Usage: java Main <seconds> <exit_code>");
      System.err.println("  <seconds>: number of seconds to wait");
      System.err.println("  <exit_code>: 0 for normal exit, non-zero for abnormal exit");
      throw new IllegalArgumentException("Invalid arguments");
    }

    int seconds;
    int exitCode;
    try {
      seconds = Integer.parseInt(args[0]);
      exitCode = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      System.err.println("Usage: java Main <seconds> <exit_code>");
      System.err.println("  <seconds>: number of seconds to wait");
      System.err.println("  <exit_code>: 0 for normal exit, non-zero for abnormal exit");
      throw new IllegalArgumentException("Invalid argument format");
    }

    try {
      System.out.println("Waiting for " + seconds + " seconds...");
      Thread.sleep(seconds * 1000L);
    } catch (InterruptedException e) {
      System.err.println("Interrupted while waiting.");
      throw new RuntimeException("Interrupted while waiting", e);
    }

    if (exitCode != 0) {
      System.out.println("Exiting abnormally with exit code " + exitCode);
      throw new RuntimeException("Abnormal exit with code " + exitCode);
    }

    System.out.println("Exiting normally.");
  }
}
