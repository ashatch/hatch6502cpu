package net.andrewhatch.hatch6502cpu.instructions;

import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.Ram;

public class Rts implements Instr {
  @Override
  public int length() {
    return 1;
  }

  @Override
  public void execute(Cpu cpu, Ram ram) {
    cpu.stackPointer++;
    cpu.programCounter = ram.memory[cpu.stackPointer];
    cpu.programCounter+=2;
  }
}
