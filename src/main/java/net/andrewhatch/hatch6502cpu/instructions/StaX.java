package net.andrewhatch.hatch6502cpu.instructions;

import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Ram;

public class StaX extends Instr {
  @Override
  public void execute(final Cpu cpu, final Ram ram) {
    ram.memory[cpu.registerX] = cpu.registerA;
  }
}
