package net.andrewhatch.hatch6502cpu;

public class Hatch6502Cpu {
  private static final int MEMORY_SIZE = 256;

  private final int[] memory = new int[MEMORY_SIZE];
  private int programCounter = 0;
  private int registerA = 0;
  private int registerX = 0;
  private int registerY = 0;
  private boolean equalFlag = false;

  public void loadProgram(final int[] program) {
    System.arraycopy(program, 0, this.memory, 0, program.length);
  }

  public void runProgram() {
    Instruction currentInstruction = null;

    while (currentInstruction != Instruction.BRK) {
      currentInstruction = Instruction.from(this.memory[programCounter]);
      int increment = executeInstruction(currentInstruction);
      programCounter += increment;
    }
  }

  private int executeInstruction(
      final Instruction instruction
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
      case LDX: {
        registerX = memory[programCounter + 1];
        return 2;
      }
      case INX: {
        registerX++;
        return 1;
      }
      case STA_X: {
        memory[registerX] = registerA;
        return 1;
      }
      case DEY: {
        registerY--;
        return 1;
      }
      case BNE: {
        if (!equalFlag) {
          programCounter += memory[programCounter + 1];
          return 1;
        } else {
          return 2;
        }
      }
      case CMY: {
        this.equalFlag = registerY == this.memory[programCounter + 1];
        return 2;
      }
      case LDY: {
        registerY = this.memory[programCounter + 1];
        return 2;
      }
      case BRK: {
        return 1;
      }
      default: {
        throw new RuntimeException("Unknown instruction");
      }
    }
  }

  public int peekMemory(int address) {
    return this.memory[address];
  }
}
