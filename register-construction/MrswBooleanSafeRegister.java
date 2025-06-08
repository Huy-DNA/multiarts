public class MrswBooleanSafeRegister {
  private SrswBooleanSafeRegister registers[];
  public MrswBooleanSafeRegister(int n) {
    this.registers = new SrswBooleanSafeRegister[n];
  }

  public boolean read(int id) {
    return registers[id].read();
  }

  public void write(int id, boolean value) {
    for (int i = 0; i < registers.length; ++i) {
      registers[id].write(value);
    }
  }
}
