package net.andrewhatch.hatch6502cpu;

import java.util.HashMap;
import java.util.Map;

public enum Instruction {
  BRK(0x00),
  LDA(0x01),
  ADC(0x02),
  STA(0x03),
  LDX(0x04),
  INX(0x05),
  CMY(0x06),
  BNE(0x07),
  STA_X(0x08),
  DEY(0x09),
  LDY(0x0A),
  UNKNOWN(0xFF);

  private static final Map<Integer, Instruction> lookup = new HashMap<>();

  static {
    for (Instruction instruction : Instruction.values()) {
      lookup.put(instruction.getNumber(), instruction);
    }
  }

  static Instruction from(int number) {
    if (!lookup.containsKey(number)) {
      throw new RuntimeException("Unknown instruction " + number);
    } else {
      return lookup.get(number);
    }
  }

  private final int number;

  private Integer getNumber() {
    return this.number;
  }

  Instruction(int number) {
    this.number = number;
  }

}
