package net.andrewhatch.hatch6502cpu.instructions;

import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.Ram;

public class Jsr implements Instr {
  @Override
  public int length() {
    return 2;
  }

  @Override
  public void execute(Cpu cpu, Ram ram) {
    ram.memory[cpu.stackPointer] = cpu.programCounter;
    cpu.stackPointer--;
    cpu.programCounter = ram.memory[cpu.programCounter + 1];
  }
}
