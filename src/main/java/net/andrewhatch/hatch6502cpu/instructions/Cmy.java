package net.andrewhatch.hatch6502cpu.instructions;

import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.Ram;

public class Cmy extends Instr {
  @Override
  public void execute(final Cpu cpu, final Ram ram) {
    cpu.programCounter++;
    cpu.equalFlag = cpu.registerY == cpu.memoryAtProgramCounter();
  }
}
