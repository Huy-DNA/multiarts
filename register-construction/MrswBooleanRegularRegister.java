public class MrswBooleanRegularRegister {
  private MrswBooleanSafeRegister register;

  public MrswBooleanRegularRegister(int n) {
  }

  public boolean read(int id) {
    return register.read(id);
  }

  public void write(int id, boolean value) {
    if (register.read(id) != value) {
      register.write(id, value);
    }
  }
}
