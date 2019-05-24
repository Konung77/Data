package lesson6;

public class Program {
  public static void main(String[] args) {
    final int cnt = 1000000;
    MyTree<Integer,Integer>[] trees = new MyTree[cnt];
    boolean[] balance = new boolean[cnt];
    int balancedCount = 0;

    for (int i = 0; i < cnt; i++) {
      trees[i] = new MyTree<>();
      while (trees[i].height() < 6) {
        int current = (int)(Math.random()*1000);
        trees[i].put(current,i);
      }
      balance[i] = trees[i].isBalanced();
      if (balance[i]) {
//        System.out.println(i+"-е дерево сбалансировано");
        balancedCount++;
      }
//      else System.out.println(i+"-е дерево не сбалансировано");
    }
    int percent = (int)(1.0*balancedCount/cnt);
    System.out.println("Количество сбалансированных двоичных деревьев: "+balancedCount);
    System.out.println("Процент сбалансированных двоичных деревьев - "+percent+"%");
  }
}
