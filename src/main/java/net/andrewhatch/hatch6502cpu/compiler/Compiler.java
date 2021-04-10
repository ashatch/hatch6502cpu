package net.andrewhatch.hatch6502cpu.compiler;

import net.andrewhatch.hatch6502cpu.vm.Instr;
import net.andrewhatch.hatch6502cpu.vm.InstructionSet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Compiler {
  private final InstructionSet instructionSet;

  public Compiler(InstructionSet instructionSet) {
    this.instructionSet = instructionSet;
  }
  public int[] compile(String lines) {
    final List<int[]> collect = Arrays.stream(lines.split("\n"))
        .map(this::decodeLine)
        .collect(Collectors.toList());

    int programLength = collect.stream().mapToInt(x -> x.length).sum();

    int[] p = new int[programLength];

    int index = 0;
    for (int[] z : collect) {
      for (int w = 0; w < z.length; w++) {
        p[index] = z[w];
        index++;
      }
    }

    return p;
  }

  private int[] decodeLine(String line) {
    final String[] parts = line.split(" ");
    int opcode = instructionSet.opCode(parts[0]).orElseThrow(() -> new RuntimeException("invalid instruction"));
    final Instr instruction = instructionSet.instruction(opcode).orElseThrow(() -> new RuntimeException("invalid instruction"));
    if (instruction.length() != parts.length) {
      throw new RuntimeException("decompile error - argument count mismatch >" + line);
    }

    int[] xs = new int[parts.length];
    xs[0] = opcode;
    for (int i = 1; i < parts.length; i++) {
      xs[i] = Integer.parseInt(parts[i]);
    }
    return xs;
  }
}
