package lesson3;

public class MyDeque<T> {
  private Object[] deque = new Object[1];
  private int size = 0;
  private int right = 0;
  private int left = 0;

  // Проверка дека на пустоту
  private boolean isEmpty ()
  {
    return size == 0;
  }

  // Изменение размера вспомогательного массива
  private void resize (int capacity)
  {
    int delta = capacity/4; // по столько свободного места оставляем справа и слева
    Object[] tmp = new Object[capacity];
    for (int i = 0; i < size; i++) {
      tmp[i+delta] = deque[(i+left+1)%deque.length];
    }
    deque = tmp;
    left = delta-1;
    if (left < 0) left += deque.length;
    right = (left + size + 1)%deque.length;
  }

  // Увеличение размера вспомогательного массива
  private void increase ()
  {
    resize(deque.length*2);
  }

  // Уменьшение размера вспомогательного массива
  private void decrease ()
  {
    resize(deque.length/2);
  }

  // Размер дека (кол-во элементов)
  public int length ()
  {
    return size;
  }

  // Добавление элемента справа
  public void insertRight (T item)
  {
    if (size == deque.length) increase();
      deque[right++] = item;
      right %= deque.length;
      size++;
  }

  // Добавление элемента слева
  public void insertLeft (T item)
  {
    if (size == deque.length) increase();
    deque[left--] = item;
    if (left < 0) left = left+deque.length;
    size++;
  }

  // Удаление элемента справа
  public T removeRight ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Deque is empty");
    T tmp = (T)deque[right-1];
    size--;
    deque[--right] = null;
    right %= deque.length;
    if ((size == deque.length/4) && (size > 0)) decrease();
    return tmp;
  }

  // Удаление элемента слева
  public T removeLeft ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Deque is empty");
    T tmp = (T)deque[left];
    size--;
    deque[left++] = null;
    left %= deque.length;
    if ((size == deque.length/4) && (size > 0)) decrease();
    return tmp;
  }

  // Печать дека
  public void print ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Deque is empty");
    for (int i = 0; i < size-1; i++) {
      System.out.print(deque[(i+left+1)%deque.length] + ", ");
    }
    System.out.println(deque[(left+size)%deque.length]);
  }
}
