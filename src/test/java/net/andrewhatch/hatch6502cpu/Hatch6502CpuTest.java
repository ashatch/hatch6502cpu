package net.andrewhatch.hatch6502cpu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Hatch6502CpuTest {
  @Test
  public void dojo1Test() {
    final int[] program = {
        0x01, 100,  // LDA 100 ; store 100 in register A
        0x02, 7,    // ADC 7   ; add 7 to register A
        0x03, 15,   // STA 15  ; store register A at memory address 15
        0x00        // BRK     ; stop
    };

    final Hatch6502Cpu cpu = new Hatch6502Cpu();
    cpu.loadProgram(program);
    cpu.runProgram();

    int value = cpu.peekMemory(15);
    assertEquals(107, value);
  }

  @Test
  public void lettingTheDogsOut() {
    final int[] program = {
        4, 128,        // LDX 128 x-register will track where to put our ascii letters
        1, 0x77,       // LDA 0x77 ('w')
        8,             // STA_X puts 'w' in location 128
        5,             // increment X to 129
        1, 0x68, 8, 5, // repeat the routine of loading a letter ('h') into A and placing into next address
        1, 0x6F, 8, 5, // 'o'
        1, 0x20, 8, 5, // ' ' (a space)
        1, 0x6c, 8, 5, // carry on for ' let the dogs out '
        1, 0x65, 8, 5,
        1, 0x74, 8, 5,
        1, 0x20, 8, 5,
        1, 0x74, 8, 5,
        1, 0x68, 8, 5,
        1, 0x65, 8, 5,
        1, 0x20, 8, 5,
        1, 0x64, 8, 5,
        1, 0x6F, 8, 5,
        1, 0x67, 8, 5,
        1, 0x73, 8, 5,
        1, 0x20, 8, 5,
        1, 0x6F, 8, 5,
        1, 0x75, 8, 5,
        1, 0x74, 8, 5,
        1, 0x20, 8, 5, // finished 'who let the dogs out '
        10, 3,         // load 3 into y (we will repeat 3 times)
        1, 0x77, 8, 5, // 'w'
        1, 0x68, 8, 5, // 'h'
        1, 0x6F, 8, 5, // 'o'
        1, 0x20, 8, 5, // ' '
        9,             // dec y
        6, 0,          // equal flag set if y == 0?
        7, -20,        // if equal flag not set, rewind 20 instructions
        0              // finish letting the dogs out
    };

    final Hatch6502Cpu cpu = new Hatch6502Cpu();
    cpu.loadProgram(program);
    cpu.runProgram();


    byte[] buf = new byte[128];
    cpu.copyMemoryBytes(buf, 128, 128);
    final String output = new String(buf).split("\0")[0]; // deal with null (\0) terminated string

    assertEquals("who let the dogs out who who who ", output);
  }
}
