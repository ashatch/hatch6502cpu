package net.andrewhatch.hatch6502cpu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InstructionTest {
  @Test
  void instructionParse() {
    assertEquals(Instruction.BRK, Instruction.from(0x00));
    assertEquals(Instruction.ADC, Instruction.from(0x69));
    assertEquals(Instruction.STA, Instruction.from(0x8d));
    assertEquals(Instruction.LDA, Instruction.from(0xa9));
  }
}
