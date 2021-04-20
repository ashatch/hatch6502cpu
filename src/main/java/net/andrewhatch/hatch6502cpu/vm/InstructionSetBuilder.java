package net.andrewhatch.hatch6502cpu.vm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InstructionSetBuilder {
  private final Map<Integer, Instr> opCodeToInstruction;
  private final Map<String, Integer> instructionNameToOpCode;
  private Instr breakInstruction;

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
    return new BaseInstructionSet(this.breakInstruction, opCodeToInstruction, instructionNameToOpCode);
  }

  public InstructionSetBuilder withBreakInstruction(Instr breakInstruction) {
    this.breakInstruction = breakInstruction;
    return this;
  }

  public static class BaseInstructionSet implements InstructionSet {

    private final Instr haltInstruction;
    private final Map<Integer, Instr> opCodeToInstruction;
    private final Map<String, Integer> instructionNameToOpCode;

    public BaseInstructionSet(
        final Instr haltInstruction,
        final Map<Integer, Instr> opCodeToInstruction,
        final Map<String, Integer> instructionNameToOpCode
    ) {
      this.haltInstruction = haltInstruction;
      this.opCodeToInstruction = opCodeToInstruction;
      this.instructionNameToOpCode = instructionNameToOpCode;
    }

    @Override
    public Instr haltInstruction() {
      return haltInstruction;
    }

    @Override
    public Optional<Instr> instruction(int opcode) {
      return Optional.ofNullable(opCodeToInstruction.get(opcode));
    }

    @Override
    public Optional<Integer> opCode(final String instruction) {
      return Optional.ofNullable(instructionNameToOpCode.get(instruction));
    }

  }

}
