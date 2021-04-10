package net.andrewhatch.hatch6502cpu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.andrewhatch.hatch6502cpu.variants.dojo6502.Dojo6502Decoder;
import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Ram;
import org.junit.jupiter.api.Test;

class Dojo1Test {
  @Test
  public void dojo1Test() {
    final int[] program = {
        0x01, 100,  // LDA 100 ; store 100 in register A
        0x02, 7,    // ADC 7   ; add 7 to register A
        0x03, 15,   // STA 15  ; store register A at memory address 15
        0x00        // BRK     ; stop
    };


    final Ram ram = new Ram(256);
    final Cpu cpu = new Cpu(new Dojo6502Decoder(), ram);

    cpu.loadProgram(program);
    cpu.runProgram();

    int value = ram.peekMemory(15);
    assertEquals(107, value);
  }
}
