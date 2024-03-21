package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void testNormalExit() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    String[] args = { "5", "0" };
    Main.main(args);

    String expectedOutput = "Waiting for 5 seconds...\nExiting normally.\n";
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  void testAbnormalExit() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    String[] args = { "3", "1" };
    assertThrows(RuntimeException.class, () -> {
      Main.main(args);
    });

    String expectedOutput = "Waiting for 3 seconds...\nExiting abnormally with exit code 1\n";
    assertEquals(expectedOutput, outContent.toString());
  }
}
