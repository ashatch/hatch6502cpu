package net.andrewhatch.hatch6502cpu.vm;

import java.util.HashMap;
import java.util.Map;

public class InstructionSetBuilder {
  private final Map<Integer, Instr> opCodeToInstruction;
  private final Map<String, Integer> instructionNameToOpCode;

  public InstructionSetBuilder() {
    opCodeToInstruction = new HashMap<>();
    instructionNameToOpCode = new HashMap<>();
  }

  public InstructionSetBuilder add(int opcode, Instr instruction) {
    opCodeToInstruction.put(opcode, instruction);
    instructionNameToOpCode.put(instruction.getClass().getSimpleName().toUpperCase(), opcode);
    return this;
  }

  public InstructionSet build() {
    return new InstructionSet(opCodeToInstruction, instructionNameToOpCode);
  }
}
