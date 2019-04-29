package lesson2;

public class Main {
  public static void main(String[] args) {
    MyList<Integer> list = new MyList<>();

//    list.print();
//    System.out.println("Длина списка: " + list.length() + " элементов");
//    System.out.println("------------------------");
//
//    System.out.println("Добавляем элементы в список");
    list.add(32);
    list.add(17);
    list.add(1014);
    list.add(345);
    list.add(-89);
    list.add(247);
    list.print();
    list.sortInsertion(Integer::compareTo);
    list.print();
//    System.out.println("Длина списка: " + list.length() + " элементов");
//    System.out.println("Элемент 345 на " + list.indexOf(345) + " позиции");
//    System.out.println("Элемент 123 на " + list.indexOf(123) + " позиции");
//    System.out.println("------------------------");
//
//    System.out.println("Заменяем элемент с индексом 2");
//    list.set(2,123);
//    System.out.println("Удаляем элемент -89");
//    list.delete(-89);
//    list.print();
//    System.out.println("Длина списка: " + list.length() + " элементов");
//    System.out.println("Элемент 123 на " + list.indexOf(123) + " позиции");
//    System.out.println("Элемент с индексом 3: " + list.get(3));
//    System.out.println("------------------------");
  }
}
