// Dummy class
// Meant to represent a fictional srsw boolean safe regisiter only
class SrswBooleanSafeRegister {
  private boolean value;

  public SrswBooleanSafeRegister() {
    this.value = false;
  }

  public boolean read() {
    return this.value;
  }

  public void write(boolean value) {
    this.value = value;
  }
}
