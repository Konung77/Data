package lesson2;

public class Main {
  static long timeSelectionSorting = 0;
  static long timeInsertionSorting = 0;
  static long summaTimeSelections = 0;
  static long summaTimeInsertions = 0;

  private static void sorting ()
  {
    MyList<Integer> list1 = new MyList<>();
    MyList<Integer> list2 = new MyList<>();
    int size = 100000;
    long beginTime;
    long endTime;

    for (int i = 0; i < size; i++) {
      list1.add((int)(Math.random()*size));
    }
    list2 = list1.copy();

    beginTime = System.currentTimeMillis();
    list1.sortSelection(Integer::compareTo);
    endTime = System.currentTimeMillis();
    timeSelectionSorting = endTime - beginTime;
    summaTimeSelections += timeSelectionSorting;

    beginTime = System.currentTimeMillis();
    list2.sortInsertion(Integer::compareTo);
    endTime = System.currentTimeMillis();
    timeInsertionSorting = endTime - beginTime;
    summaTimeInsertions += timeInsertionSorting;
  }

  public static void main(String[] args) {
    System.out.printf("%1$-15s %2$-15s %3$-15s", "Номер", "Сортировка", "Сортировка");
    System.out.println();
    System.out.printf("%1$-15s %2$-15s %3$-15s", "эксперимента", "вставками", "выбором");
    System.out.println();
    sorting();
    for (int i = 0; i < 20; i++) {
      sorting();
      System.out.printf("%1$-15d %2$-15.3f %3$-15.3f", i+1, (float)timeInsertionSorting/1000.0f, (float)timeSelectionSorting/1000.0f);
      System.out.println();
    }
    System.out.printf("%1$-15s %2$-15.3f %3$-15.3f", "Среднее", (float)summaTimeInsertions/20000.0f, (float)summaTimeSelections/20000.0f);
    System.out.println();
  }
}
