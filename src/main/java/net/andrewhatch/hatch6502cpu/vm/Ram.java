package net.andrewhatch.hatch6502cpu.vm;

public class Ram {
  public final int[] memory;

  public Ram(int size) {
     this.memory = new int[size];
  }

  public int peekMemory(int address) {
    return this.memory[address];
  }

  public void copyMemoryBytes(byte[] destination, int address, int length) {
    for(int i = 0; i < length; i++) {
      destination[i] = (byte) this.memory[address + i];
    }
  }
}
