package com.example;

public class Main {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("Usage: java Main <seconds> <exit_code>");
      System.err.println("  <seconds>: number of seconds to wait");
      System.err.println("  <exit_code>: 0 for normal exit, non-zero for abnormal exit");
      System.exit(1);
    }

    int seconds;
    int exitCode;
    try {
      seconds = Integer.parseInt(args[0]);
      exitCode = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      System.err.println("Invalid argument format. Both arguments must be integers.");
      System.exit(1);
      return; // This line will never be reached, but added to avoid a compiler warning
    }

    try {
      System.out.println("Waiting for " + seconds + " seconds...");
      Thread.sleep(seconds * 1000L);
    } catch (InterruptedException e) {
      System.err.println("Interrupted while waiting.");
      System.exit(1);
    }

    if (exitCode == 0) {
      System.out.println("Exiting normally.");
      System.exit(0);
    } else {
      System.err.println("Exiting abnormally with exit code " + exitCode);
      System.exit(exitCode);
    }
  }
}
