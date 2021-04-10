package net.andrewhatch.hatch6502cpu.vm;

public class Cpu {

  private final Ram ram;
  private final InstructionDecoder instructionDecoder;

  public int programCounter = 0;
  public int registerA = 0;
  public int registerX = 0;
  public int registerY = 0;
  public boolean equalFlag = false;

  public Cpu(
      final InstructionDecoder decoder,
      final Ram ram
  ) {
    this.instructionDecoder = decoder;
    this.ram = ram;
  }

  public void loadProgram(final int[] program) {
    System.arraycopy(program, 0, this.ram.memory, 0, program.length);
  }

  public void runProgram() {
    Instr currentInstruction = null;

    while (!instructionDecoder.isStopInstruction(currentInstruction)) {
      int x = this.ram.memory[programCounter];
      currentInstruction = instructionDecoder.decode(x)
          .orElseThrow(() -> new RuntimeException("unknown instruction " + x));

      currentInstruction.execute(this, this.ram);
      this.programCounter++;
    }
  }

  public int memoryAtProgramCounter() {
    return this.ram.memory[this.programCounter];
  }
}
