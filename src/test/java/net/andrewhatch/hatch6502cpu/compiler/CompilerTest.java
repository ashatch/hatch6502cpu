package net.andrewhatch.hatch6502cpu.compiler;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import net.andrewhatch.hatch6502cpu.variants.dojo6502.Dojo6502Decoder;
import org.junit.jupiter.api.Test;

class CompilerTest {
  @Test
  public void smallProgram() {
    Compiler c = new Compiler(new Dojo6502Decoder().getInstructionSet());
    final int[] program = c.compile("LDA 100\nADC 7\nSTA 15\nBRK\n");

    assertThat(program).isEqualTo(new int[] {
        0x01, 100,
        0x02, 7,
        0x03, 15,
        0x00
    });
  }
}