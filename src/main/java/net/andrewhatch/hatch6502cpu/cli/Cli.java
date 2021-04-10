package net.andrewhatch.hatch6502cpu.cli;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import net.andrewhatch.hatch6502cpu.variants.Dojo6502Decoder;
import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Ram;

@QuarkusMain
public class Cli implements QuarkusApplication {

  @Override
  public int run(final String... args) {
    final int[] program = {
        0xa9, 100,  // LDA 100 ; store 100 in register A
        0x69, 7,    // ADC 7   ; add 7 to register A
        0x8d, 15,   // STA 15  ; store register A at memory address 15
        0x00        // BRK     ; stop
    };


    final Ram ram = new Ram(256);
    final Cpu cpu = new Cpu(new Dojo6502Decoder(), ram);

    cpu.loadProgram(program);
    cpu.runProgram();
    return 0;
  }

  public static void main(String... args) {
    Quarkus.run(Cli.class, args);
  }
}
