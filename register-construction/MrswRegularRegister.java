public class MrswRegularRegister {
  static int RANGE = Byte.MAX_VALUE - Byte.MIN_VALUE + 1;

  MrswBooleanSafeRegister bits[];

  public MrswRegularRegister(int n) {
    this.bits = new MrswBooleanSafeRegister[RANGE];
    for (int i = 0; i < RANGE; ++i) {
      this.bits[i] = new MrswBooleanSafeRegister(n);
      this.bits[0].write(0, false);
    }
    this.bits[0].write(0, true);
  }

  public int read(int id) {
    for (int i = RANGE; i >= 0; --i) {
      if (this.bits[i].read(id)) {
        return i;
      }
    }
    return -1;
  }

  public void write(int id, int value) {
    this.bits[value].write(id, true);
    for (int i = value + 1; i < RANGE; ++i) {
      this.bits[value].write(id, false);
    }
  }
}
