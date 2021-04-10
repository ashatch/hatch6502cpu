package net.andrewhatch.hatch6502cpu.vm;

import java.util.HashMap;
import java.util.Map;

public class InstructionSetBuilder {
  private final Map<Integer, Instr> opCodeToInstruction;

  public InstructionSetBuilder() {
    opCodeToInstruction = new HashMap<>();
  }

  public InstructionSetBuilder add(int opcode, Instr instruction) {
    opCodeToInstruction.put(opcode, instruction);
    return this;
  }

  public InstructionSet build() {
    return new InstructionSet(opCodeToInstruction);
  }
}
