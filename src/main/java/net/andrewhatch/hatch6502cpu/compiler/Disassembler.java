package net.andrewhatch.hatch6502cpu.compiler;

import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.InstructionDecoder;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Disassembler {
  private final InstructionDecoder decoder;

  public Disassembler(InstructionDecoder decoder) {
    this.decoder = decoder;
  }

  public String disassemble(int[] program) {
    final StringBuffer output = new StringBuffer();

    int index = 0;
    while (index < program.length) {
      final Instr instr = this.decoder.decode(program[index])
          .orElseThrow(() -> new RuntimeException("invalid opcode"));

      final String arguments = instructionArguments(program, index, instr);

      output.append(instructionName(instr));
      if (arguments.length() > 0) {
        output.append(" " + arguments);
      }

      output.append("\n");

      index += instr.length();
    }

    return output.toString();
  }

  private String instructionName(Instr instr) {
    return instr.getClass().getSimpleName().toUpperCase();
  }

  private String instructionArguments(int[] program, int index, Instr instr) {
    return IntStream.range(index + 1, index + instr.length())
        .boxed()
        .map(i -> "" + program[i])
        .collect(Collectors.joining(" "));
  }
}
