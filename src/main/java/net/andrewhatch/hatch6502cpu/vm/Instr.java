package net.andrewhatch.hatch6502cpu.vm;

public abstract class Instr {
  public abstract void execute(Cpu cpu, Ram ram);
}
