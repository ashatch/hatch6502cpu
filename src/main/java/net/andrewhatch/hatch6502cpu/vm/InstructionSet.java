package net.andrewhatch.hatch6502cpu.vm;

import java.util.Optional;

public interface InstructionSet {
  Instr haltInstruction();
  Optional<Instr> instruction(int opcode);
  Optional<Integer> opCode(final String instruction);
}
