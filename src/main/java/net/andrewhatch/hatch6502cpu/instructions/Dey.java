package net.andrewhatch.hatch6502cpu.instructions;

import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Ram;

public class Dey implements Instr {
  @Override
  public int length() {
    return 1;
  }

  @Override
  public void execute(final Cpu cpu, final Ram ram) {
    cpu.registerY--;
  }
}
