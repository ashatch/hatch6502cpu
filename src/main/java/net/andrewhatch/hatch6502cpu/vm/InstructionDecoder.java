package net.andrewhatch.hatch6502cpu.vm;

import net.andrewhatch.hatch6502cpu.vm.Instr;

import java.util.Optional;

public interface InstructionDecoder {
  Optional<Instr> decode(int opcode);

  boolean isStopInstruction(Instr currentInstruction);
}
