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

public class MrswAtomicRegister implements Register<Byte> {
  StampedValue registers[][]; // SRSW atomic registers
  ThreadLocal<Long> lastStamp;

  MrswAtomicRegister(int n) {
    this.registers = new StampedValue[n][n];
    for (int i = 0; i < n; ++i) {
      this.registers[i] = new StampedValue[n];
      for (int j = 0; j < n; ++j) {
        this.registers[i][j] = new StampedValue((byte) 0, 0);
      }
    }
    this.lastStamp = new ThreadLocal<Long>() {
      protected Long initialValue() {
        return (long) 0;
      }
    };
  }

  public Byte read(int id) {
    StampedValue readValue = this.registers[id][id];
    for (int i = 0; i < registers.length; ++i) {
      readValue = StampedValue.max(readValue, this.registers[id][i]);
    }
    for (int i = 0; i < registers.length; ++i) {
      if (i != id) {
        this.registers[i][id] = readValue;
      }
    }
    return readValue.value;
  }

  public void write(int id, Byte value) {
    Long lastStamp = this.lastStamp.get();
    StampedValue newValue = new StampedValue(value, lastStamp + 1);
    this.lastStamp.set(lastStamp + 1);
    for (int i = 0; i < registers.length; ++i) {
      this.registers[i][i] = newValue;
    }
  }
}
