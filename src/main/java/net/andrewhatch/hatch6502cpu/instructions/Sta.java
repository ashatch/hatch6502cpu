package net.andrewhatch.hatch6502cpu.instructions;

import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Ram;

public class Sta implements Instr {
  @Override
  public int length() {
    return 2;
  }

  @Override
  public void execute(final Cpu cpu, final Ram ram) {
    cpu.programCounter++;
    int address = cpu.memoryAtProgramCounter();
    ram.memory[address] = cpu.registerA;
    cpu.programCounter++;
  }
}
