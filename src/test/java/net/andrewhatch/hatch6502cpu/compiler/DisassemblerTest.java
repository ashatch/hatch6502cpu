package net.andrewhatch.hatch6502cpu.compiler;

import static org.assertj.core.api.Assertions.*;

import net.andrewhatch.hatch6502cpu.variants.Dojo6502Decoder;
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

  @Test
  public void longProgramTest() {
    final Disassembler disassembler = new Disassembler(new Dojo6502Decoder());
    final String program = disassembler.disassemble(new int[] {
        4, 128,        // LDX 128 x-register will track where to put our ascii letters
        1, 0x77,       // LDA 0x77 ('w')
        8,             // STA_X puts 'w' in location 128
        5,             // increment X to 129
        1, 0x68, 8, 5, // repeat the routine of loading a letter ('h') into A and placing into next address
        1, 0x6F, 8, 5, // 'o'
        1, 0x20, 8, 5, // ' ' (a space)
        1, 0x6c, 8, 5, // carry on for ' let the dogs out '
        1, 0x65, 8, 5,
        1, 0x74, 8, 5,
        1, 0x20, 8, 5,
        1, 0x74, 8, 5,
        1, 0x68, 8, 5,
        1, 0x65, 8, 5,
        1, 0x20, 8, 5,
        1, 0x64, 8, 5,
        1, 0x6F, 8, 5,
        1, 0x67, 8, 5,
        1, 0x73, 8, 5,
        1, 0x20, 8, 5,
        1, 0x6F, 8, 5,
        1, 0x75, 8, 5,
        1, 0x74, 8, 5,
        1, 0x20, 8, 5, // finished 'who let the dogs out '
        10, 3,         // load 3 into y (we will repeat 3 times)
        1, 0x77, 8, 5, // 'w'
        1, 0x68, 8, 5, // 'h'
        1, 0x6F, 8, 5, // 'o'
        1, 0x20, 8, 5, // ' '
        9,             // dec y
        6, 0,          // equal flag set if y == 0?
        7, -21,        // if equal flag not set, rewind 20 instructions
        0              // finish letting the dogs out
    });

    assertThat(program).isEqualTo(
        "LDX 128\n" +
        "LDA 119\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 104\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 111\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 32\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 108\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 101\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 116\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 32\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 116\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 104\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 101\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 32\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 100\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 111\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 103\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 115\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 32\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 111\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 117\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 116\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 32\n" +
        "STAX\n" +
        "INX\n" +
        "LDY 3\n" +
        "LDA 119\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 104\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 111\n" +
        "STAX\n" +
        "INX\n" +
        "LDA 32\n" +
        "STAX\n" +
        "INX\n" +
        "DEY\n" +
        "CMY 0\n" +
        "BNE -21\n" +
        "BRK\n");
  }
}
