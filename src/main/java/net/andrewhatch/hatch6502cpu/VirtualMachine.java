package net.andrewhatch.hatch6502cpu;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class VirtualMachine implements QuarkusApplication {


  @Override
  public int run(final String... args) {
    final int[] program = {
        0xa9, 100,  // LDA 100 ; store 100 in register A
        0x69, 7,    // ADC 7   ; add 7 to register A
        0x8d, 15,   // STA 15  ; store register A at memory address 15
        0x00        // BRK     ; stop
    };

    final Hatch6502Cpu hatch6502Cpu = new Hatch6502Cpu();
    hatch6502Cpu.loadProgram(program);
    hatch6502Cpu.runProgram();
    return 0;
  }

  public static void main(String... args) {
    Quarkus.run(VirtualMachine.class, args);
  }
}
