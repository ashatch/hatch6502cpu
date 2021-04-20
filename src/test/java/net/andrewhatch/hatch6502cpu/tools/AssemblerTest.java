package net.andrewhatch.hatch6502cpu.tools;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import net.andrewhatch.hatch6502cpu.instructionsets.Dojo6502InstructionSet;
import org.junit.jupiter.api.Test;

class AssemblerTest {
  @Test
  public void smallProgram() {
    Assembler c = new Assembler(Dojo6502InstructionSet.make());
    final int[] program = c.assemble("LDA 100 ; load something\nADC 7\nSTA 15\nBRK\n");

    assertThat(program).isEqualTo(new int[] {
        0x01, 100,
        0x02, 7,
        0x03, 15,
        0x00
    });
  }
}