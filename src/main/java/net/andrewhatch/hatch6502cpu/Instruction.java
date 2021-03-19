package net.andrewhatch.hatch6502cpu;

import java.util.HashMap;
import java.util.Map;

public enum Instruction {
  BRK(0x00),
  LDA(0xa9),
  ADC(0x69),
  STA(0x8d);

  private static final Map<Integer, Instruction> lookup = new HashMap<>();

  static {
    for (Instruction instruction : Instruction.values()) {
      lookup.put(instruction.getNumber(), instruction);
    }
  }

  static Instruction from(int number) {
    return lookup.getOrDefault(number, BRK);
  }

  private final int number;

  private Integer getNumber() {
    return this.number;
  }

  Instruction(int number) {
    this.number = number;
  }

}
