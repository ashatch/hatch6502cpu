package net.andrewhatch.hatch6502cpu.vm;

public interface Instr {
  int length();
  void execute(Cpu cpu, Ram ram);
}
