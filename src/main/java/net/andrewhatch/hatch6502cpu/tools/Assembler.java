package net.andrewhatch.hatch6502cpu.tools;

import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.InstructionSet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Assembler {
  private final InstructionSet instructionSet;

  public Assembler(final InstructionSet instructionSet) {
    this.instructionSet = instructionSet;
  }

  public int[] assemble(final String programString) {
    final List<int[]> compiledLines = Arrays.stream(programString.split("\n"))
        .map(this::assembleLine)
        .collect(Collectors.toList());

    int programLength = compiledLines.stream()
        .mapToInt(x -> x.length)
        .sum();

    int[] compiledProgram = new int[programLength];

    int index = 0;
    for (int[] line : compiledLines) {
      for (int i = 0; i < line.length; i++) {
        compiledProgram[index] = line[i];
        index++;
      }
    }

    return compiledProgram;
  }

  private int[] assembleLine(final String line) {
    final String[] parts = line.split(";")[0].split(" ");

    int opcode = instructionSet.opCode(parts[0])
        .orElseThrow(() -> new RuntimeException("invalid instruction"));

    final Instr instruction = instructionSet.instruction(opcode)
        .orElseThrow(() -> new RuntimeException("invalid instruction"));

    if (instruction.length() != parts.length) {
      throw new RuntimeException("decompile error - argument count mismatch >" + line);
    }

    int[] assembledLine = new int[parts.length];
    assembledLine[0] = opcode;
    for (int i = 1; i < parts.length; i++) {
      assembledLine[i] = Integer.parseInt(parts[i]);
    }
    return assembledLine;
  }
}
