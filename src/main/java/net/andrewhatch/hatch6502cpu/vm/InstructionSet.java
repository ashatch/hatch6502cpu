package net.andrewhatch.hatch6502cpu.vm;

import net.andrewhatch.hatch6502cpu.vm.Instr;

import java.util.Map;
import java.util.Optional;

public class InstructionSet {
  private final Map<Integer, Instr> opCodeToInstruction;

  public InstructionSet(final Map<Integer, Instr> opCodeToInstruction) {
    this.opCodeToInstruction = opCodeToInstruction;
  }

  public Optional<Instr> instruction(int opcode) {
    return Optional.ofNullable(opCodeToInstruction.get(opcode));
  }
}
