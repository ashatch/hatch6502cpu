package net.andrewhatch.hatch6502cpu.instructionsets;

import net.andrewhatch.hatch6502cpu.instructions.Adc;
import net.andrewhatch.hatch6502cpu.instructions.Bne;
import net.andrewhatch.hatch6502cpu.instructions.Brk;
import net.andrewhatch.hatch6502cpu.instructions.Cmy;
import net.andrewhatch.hatch6502cpu.instructions.Dey;
import net.andrewhatch.hatch6502cpu.instructions.Inx;
import net.andrewhatch.hatch6502cpu.instructions.Jsr;
import net.andrewhatch.hatch6502cpu.instructions.Lda;
import net.andrewhatch.hatch6502cpu.instructions.Ldx;
import net.andrewhatch.hatch6502cpu.instructions.Ldy;
import net.andrewhatch.hatch6502cpu.instructions.Rts;
import net.andrewhatch.hatch6502cpu.instructions.Sta;
import net.andrewhatch.hatch6502cpu.instructions.StaX;
import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.InstructionSet;
import net.andrewhatch.hatch6502cpu.vm.InstructionSetBuilder;

public class Dojo6502InstructionSet {
  public static InstructionSet make() {
    Instr breakInstruction = new Brk();
    return new InstructionSetBuilder()
        .withBreakInstruction(breakInstruction)
        .add(0x00, breakInstruction)
        .add(0x01, new Lda())
        .add(0x02, new Adc())
        .add(0x03, new Sta())
        .add(0x04, new Ldx())
        .add(0x05, new Inx())
        .add(0x06, new Cmy())
        .add(0x07, new Bne())
        .add(0x08, new StaX())
        .add(0x09, new Dey())
        .add(0x0A, new Ldy())
        .add(0x0B, new Jsr())
        .add(0x0C, new Rts())
        .build();
  }
}
