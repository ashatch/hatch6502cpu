package net.andrewhatch.hatch6502cpu.vm;

import java.util.Map;
import java.util.Optional;

public class InstructionSet {
  private final Map<Integer, Instr> opCodeToInstruction;
  private final Map<String, Integer> instructionNameToOpCode;

  public InstructionSet(
      final Map<Integer, Instr> opCodeToInstruction,
      final Map<String, Integer> instructionNameToOpCode
  ) {
    this.opCodeToInstruction = opCodeToInstruction;
    this.instructionNameToOpCode = instructionNameToOpCode;
  }

  public Optional<Instr> instruction(int opcode) {
    return Optional.ofNullable(opCodeToInstruction.get(opcode));
  }

  public Optional<Integer> opCode(final String instruction) {
    return Optional.ofNullable(instructionNameToOpCode.get(instruction));
  }

}
