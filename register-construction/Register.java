public interface Register<T> {
  T read(int id);
  void write(int id, T value);
}
