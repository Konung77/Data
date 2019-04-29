package lesson2;

import java.util.Arrays;
import java.util.Comparator;

public class MyList<T> {
  private int size = 0;
  private Object[] list = new Object[1];

  // Изменение длины вспомогательного массива
  private void resize (int new_size)
  {
    Object[] tmp = new Object[new_size];
//    for (int i = 0; i < list.length; i++) {
//      tmp[i] = list[i];
//    }
    tmp = Arrays.copyOf(list, size);
    list = tmp;
  }

  // Увеличение длины вспомогательного массива в 2 раза
  private void increase ()
  {
    int new_size = list.length*2; // Нужна проверка, чтобы не произошло переполнение
    resize(new_size);
  }

  // Уменьшение длины вспомогательного массива в 2 раза
  private void decrease ()
  {
    int new_size = list.length/2;
    resize(new_size);
  }

  // Установить по индексу position элемент равным item
  public void set (int position, T item)
  {
    if ((position > size-1) || (position < 0)) throw new IndexOutOfBoundsException();
    list[position] = item;
  }

  // Получить элемент с индексом position
  public T get (int position)
  {
    if ((position > size-1) || (position < 0)) throw new IndexOutOfBoundsException();
    return (T)list[position];
  }

  // Получение позиции в списке, на которой находится элемент, равный item
  public int indexOf (T item)
  {
    for (int i = 0; i < size; i++) {
      if (list[i].equals(item)) return i;
    }
    return -1;
  }

  // Добавление в конец списка элемента item
  public void add (T item)
  {
    int lastItem = size;
    if (++size > list.length) increase();
    list[lastItem] = item;
  }

  // Удаление первого элемента в списке, совпадающего с item
  public boolean delete (T item)
  {
    int position = indexOf(item);
    if (position < 0) return false;
    for (int i = position; i < size-1; i++) {
      list[i] = list[i+1];
    }
    list[size-1] = null;
    size--;
    if (size < list.length/4) decrease();
    return true;
  }

  // Узнать длину списка
  public int length ()
  {
    return size;
  }

  // Вывод списка на печать
  public void print ()
  {
    if (size > 0) {
      for (int i = 0; i < size - 1; i++) {
        System.out.print(list[i] + ", ");
      }
      System.out.println(list[size - 1]);
    }
  }

  // Поменять 2 элемента списка местами
  public void exchange (int pos1, int pos2)
  {
    T tmp = (T)list[pos1];
    list[pos1] = list[pos2];
    list[pos2] = tmp;
  }

  // Сортировка списка вставкой
  public void sortInsertion(Comparator<T> comp)
  {
    int j;

    for (int i = 1; i < size; i++) {
      j = i;
      while (j > 0)
      {
        if (comp.compare((T)list[j], (T)list[j-1]) < 0) exchange(j,j-1);
        else break;
        j--;
      }
    }
  }
}
