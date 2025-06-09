// Dummy class
// Meant to represent a fictional srsw boolean safe regisiter only
class SrswBooleanSafeRegister implements Register<Boolean> {
  private boolean value;

  public SrswBooleanSafeRegister() {
    this.value = false;
  }

  public Boolean read(int id) {
    return this.value;
  }

  public void write(int id, Boolean value) {
    this.value = value;
  }
}
