package net.andrewhatch.hatch6502cpu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Hatch6502CpuTest {
  @Test
  public void dojoTest() {
    final int[] program = {
        0xa9, 100,  // LDA 100 ; store 100 in register A
        0x69, 7,    // ADC 7   ; add 7 to register A
        0x8d, 15,   // STA 15  ; store register A at memory address 15
        0x00        // BRK     ; stop
    };

    final Hatch6502Cpu cpu = new Hatch6502Cpu();
    cpu.loadProgram(program);
    cpu.runProgram();

    int value = cpu.peekMemory(15);
    assertEquals(107, value);
  }
}
