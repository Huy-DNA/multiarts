public class MrswBooleanRegularRegister implements Register<Boolean> {
  private MrswBooleanSafeRegister register;

  public MrswBooleanRegularRegister(int n) {
    this.register = new MrswBooleanSafeRegister(n);
  }

  public Boolean read(int id) {
    return register.read(id);
  }

  public void write(int id, Boolean value) {
    if (register.read(id) != value) {
      register.write(id, value);
    }
  }
}
