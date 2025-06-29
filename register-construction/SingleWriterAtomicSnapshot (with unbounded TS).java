class StampedSnapshot {
  long stamp; // unbounded timestamp
  byte value;
  byte[] snap;

  StampedSnapshot(long stamp, byte value, byte[] snap) {
    this.stamp = stamp;
    this.value = value;
    this.snap = snap;
  }
}

public class SingleWriterAtomicSnapshot {
  StampedSnapshot registers[]; // MRSW atomic registers

  public AtomicSnapshot(int n) {
    this.registers = new StampedSnapshot[n];
    for (int i = 0; i < n; ++i) {
      byte[] snap = new byte[n];
      this.registers[i] = new StampedSnapshot(0, (byte) 0, snap);
    }
  }

  private StampedSnapshot[] collect() {
    StampedSnapshot[] copy = new StampedSnapshot[registers.length];
    for (int i = 0; i < this.registers.length; ++i) {
      copy[i] = registers[i];
    }
    return copy;
  }

  public void update(int id, byte newValue) {
    byte[] snap = scan();
    StampedSnapshot oldValue = registers[id];
    registers[id] = new StampedSnapshot(oldValue.stamp + 1, newValue, snap);
  }

  public byte[] scan() {
    StampedSnapshot[] oldSnaps = this.collect();
    StampedSnapshot[] newSnaps;
    boolean moved[] = new boolean[this.registers.length];
    collect: while (true) {
      newSnaps = this.collect();
      for (int i = 0; i < oldSnaps.length; ++i) {
        if (oldSnaps[i].stamp != newSnaps[i].stamp) {
          if (moved[i]) {
            return oldSnaps[i].snap;
          } else {
            moved[i] = true;
            oldSnaps = newSnaps;
            continue collect;
          }
        }
      }

      byte result[] = new byte[this.registers.length];
      for (int i = 0; i < this.registers.length; ++i) {
        result[i] = newSnaps[i].value;
      }
      return result;
    }
  }
}
