package net.andrewhatch.hatch6502cpu.compiler;

import static org.assertj.core.api.Assertions.*;

import net.andrewhatch.hatch6502cpu.variants.dojo6502.Dojo6502Decoder;
import org.junit.jupiter.api.Test;

class DisassemblerTest {
  @Test
  public void singleLineWithOpcodeLengthOne() {
    final Disassembler disassembler = new Disassembler(new Dojo6502Decoder());
    final String program = disassembler.disassemble(new int[] {
        0x05
    });

    String[] lines = program.split("\n");
    assertThat(lines.length).isEqualTo(1);
    assertThat(lines[0]).isEqualTo("INX");
  }

  @Test
  public void singleLineWithOpcodeLengthTwo() {
    final Disassembler disassembler = new Disassembler(new Dojo6502Decoder());
    final String program = disassembler.disassemble(new int[] {
        0x01, 100
    });

    String[] lines = program.split("\n");
    assertThat(lines.length).isEqualTo(1);
    assertThat(lines[0]).isEqualTo("LDA 100");
  }

  @Test
  public void smallProgram() {
    final Disassembler disassembler = new Disassembler(new Dojo6502Decoder());
    final String program = disassembler.disassemble(new int[] {
        0x01, 100,  // LDA 100 ; store 100 in register A
        0x02, 7,    // ADC 7   ; add 7 to register A
        0x03, 15,   // STA 15  ; store register A at memory address 15
        0x00        // BRK     ; stop
    });

    assertThat(program).isEqualTo("LDA 100\nADC 7\nSTA 15\nBRK\n");
  }
}
