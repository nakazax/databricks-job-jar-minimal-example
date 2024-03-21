package com.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

  @Test
  void testNormalExit() throws InterruptedException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    String[] args = { "5", "0" };
    Main.main(args);

    String expectedOutput = "Waiting for 5 seconds...\nExiting normally.\n";
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  void testAbnormalExit() throws InterruptedException {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    String[] args = { "3", "1" };
    int exitCode = -1;
    try {
      Main.main(args);
    } catch (Exception e) {
      exitCode = 1;
    }

    String expectedOutput = "Waiting for 3 seconds...\nExiting abnormally with exit code 1\n";
    assertEquals(expectedOutput, errContent.toString());
    assertEquals(1, exitCode);
  }

  @Test
  void testInvalidArguments() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    String[] args = { "invalid", "args" };
    int exitCode = -1;
    try {
      Main.main(args);
    } catch (Exception e) {
      exitCode = 1;
    }

    String expectedOutput = "Usage: java Main <seconds> <exit_code>\n" +
        "  <seconds>: number of seconds to wait\n" +
        "  <exit_code>: 0 for normal exit, non-zero for abnormal exit\n";
    assertTrue(errContent.toString().startsWith(expectedOutput));
    assertEquals(1, exitCode);
  }
}
