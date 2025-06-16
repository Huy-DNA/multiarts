// Unbounded timestamp
class StampedValue {
  public byte value;
  public long timestamp;

  StampedValue(byte value, long timestamp) {
    this.value = value;
    this.timestamp = timestamp;
  }

  static StampedValue max(StampedValue first, StampedValue second) {
    return first.timestamp > second.timestamp ? first : second;
  }
}

public class MrmwAtomicRegister implements Register<Byte> {
  StampedValue registers[]; // MRSW atomic registers

  MrmwAtomicRegister(int n) {
    this.registers = new StampedValue[n];
    for (int i = 0; i < n; ++i) {
      this.registers[i] = new StampedValue((byte) 0, 0);
    }
  }

  public Byte read(int id) {
    StampedValue maxValue = new StampedValue((byte) 0, 0);
    for (int i = 0; i < this.registers.length; ++i) {
      maxValue = StampedValue.max(maxValue, registers[i]);
    }
    return maxValue.value;
  }

  public void write(int id, Byte value) {
    StampedValue maxValue = new StampedValue((byte) 0, 0);
    for (int i = 0; i < this.registers.length; ++i) {
      maxValue = StampedValue.max(maxValue, registers[i]);
    }
    long stamp = maxValue.timestamp;
    registers[id] = new StampedValue(value, stamp + 1);
  }
}
