package net.andrewhatch.hatch6502cpu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.andrewhatch.hatch6502cpu.variants.Dojo6502Decoder;
import net.andrewhatch.hatch6502cpu.vm.Cpu;
import net.andrewhatch.hatch6502cpu.vm.Ram;
import org.junit.jupiter.api.Test;

public class Dojo3Test {
  @Test
  public void jsrBehaviour() {
    final int JSR = 0x0B;
    final int targetMemoryAddress = 0x0A;
    final int[] program = {
        0x01, 100,                // LDA 100 ; store 100 in register A
        JSR, targetMemoryAddress  // JSR ; jump
    };

    final Ram ram = new Ram(256);
    final Cpu cpu = new Cpu(new Dojo6502Decoder(), ram);

    assertEquals(0x00, ram.peekMemory(255));
    assertEquals(255, cpu.stackPointer);
    assertEquals(0, cpu.programCounter);

    cpu.loadProgram(program);
    cpu.runProgram();

    assertEquals(0x02, ram.peekMemory(255));
    assertEquals(255-1, cpu.stackPointer);
    assertEquals(targetMemoryAddress, cpu.programCounter);
  }

  @Test
  public void rtsBehaviour() {
    final int JSR = 0x0B;
    final int RTS = 0x0C;
    final int targetMemoryAddress = 0x07;
    final int[] program = {
        0x01, 100,                 // LDA 100 ; store 100 in register A
        JSR, targetMemoryAddress,  // JSR ; jump
        0x00,                      // JSR jumps over these
        0x00,
        0x00,
        0x01, 200,                 // target: LDA 200
        RTS
    };

    final Ram ram = new Ram(256);
    final Cpu cpu = new Cpu(new Dojo6502Decoder(), ram);
    cpu.loadProgram(program);
    cpu.runProgram();

    assertEquals(200, cpu.registerA);
    assertEquals(4, cpu.programCounter);
  }
}
