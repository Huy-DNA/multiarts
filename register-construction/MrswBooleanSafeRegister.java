public class MrswBooleanSafeRegister implements Register<Boolean> {
  private SrswBooleanSafeRegister registers[];

  public MrswBooleanSafeRegister(int n) {
    this.registers = new SrswBooleanSafeRegister[n];
  }

  public Boolean read(int id) {
    return registers[id].read(id);
  }

  public void write(int id, Boolean value) {
    for (int i = 0; i < registers.length; ++i) {
      registers[id].write(id, value);
    }
  }
}
