// Unbounded timestamp
class StampedValue {
  public byte value;
  public long timestamp;

  StampedValue(byte value, long timestamp) {
    this.value = value;
    this.timestamp = timestamp;
  }
}

public class SrswAtomicRegister implements Register<Byte> {
  ThreadLocal<StampedValue> lastValue;
  ThreadLocal<Long> lastStamp;
  StampedValue register; // regular SRSW timestamp-value pair

  SrswAtomicRegister() {
    this.register = new StampedValue((byte) 0, 0);
    this.lastStamp = new ThreadLocal<Long>() {
      protected Long initialValue() {
        return (long) 0;
      }
    };
    this.lastValue = new ThreadLocal<StampedValue>() {
      protected StampedValue initialValue() {
        return register;
      }
    };
  }

  public Byte read(int id) {
    StampedValue value = register;
    StampedValue lastValue = this.lastValue.get();
    StampedValue returnedValue = value.timestamp > lastValue.timestamp ? value : lastValue;
    this.lastValue.set(lastValue);
    return returnedValue.value;
  }

  public void write(int id, Byte value) {
    long stamp = this.lastStamp.get() + 1;
    this.register = new StampedValue(value, stamp);
    this.lastStamp.set(stamp);
  }
}
