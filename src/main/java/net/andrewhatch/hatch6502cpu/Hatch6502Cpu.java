package net.andrewhatch.hatch6502cpu;

public class Hatch6502Cpu {
  private static final int MEMORY_SIZE = 16;

  private final int[] memory = new int[MEMORY_SIZE];
  private int programCounter = 0;
  private int registerA = 0;

  public void loadProgram(final int[] program) {
    System.arraycopy(program, 0, this.memory, 0, program.length);
  }

  public void runProgram() {
    Instruction currentInstruction = null;

    while (currentInstruction != Instruction.BRK) {
      currentInstruction = Instruction.from(this.memory[programCounter]);
      int increment = executeInstruction(currentInstruction, programCounter);
      programCounter += increment;
    }

    System.out.println(memory[15]);
  }

  private int executeInstruction(
      final Instruction instruction,
      final int programCounter
  ) {
    switch (instruction) {
      case LDA: {
        registerA = memory[programCounter + 1];
        return 2;
      }
      case ADC: {
        registerA += memory[programCounter + 1];
        return 2;
      }
      case STA: {
        int address = this.memory[programCounter + 1];
        this.memory[address] = registerA;
        return 2;
      }
      default: {
        return 1;
      }
    }
  }

  public int peekMemory(int address) {
    return this.memory[address];
  }
}
