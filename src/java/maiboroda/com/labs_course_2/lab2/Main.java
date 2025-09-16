package maiboroda.com.labs_course_2.lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        MyString[] arr = {
                new MyString("Java"),
                new MyString("CSharp"),
                new MyString("Python"),
                new MyString("Go")
        };

        System.out.println("=== Масив ===");
        for (MyString s : arr) System.out.println(s);
        boolean found = arr[0].containsChar('J');
        System.out.println(arr[0] + " містить символ 'J'? : " + found);
        arr[2].append("Lang");
        System.out.println("Після оновлення: " + arr[2]);

        List<MyString> list = new ArrayList<>(Arrays.asList(arr));
        list.add(new MyString("Rust"));
        MyString js = new MyString("JavaScript");
        list.add(js);
        list.remove(1);
        System.out.println("\n=== ArrayList ===");
        System.out.println(js);
        js.reverse();
        System.out.println(js.getValue());
        for (MyString s : list) System.out.println(s);

        Vector raw = new Vector();
        raw.add(new MyString("Scala"));
        raw.add("Просто рядок");
        System.out.println("\n=== Vector (non-generic) ===");
        for (Object o : raw) System.out.println(o);

        BinaryTree<MyString> tree = new BinaryTree<>();
        tree.insert(new MyString("Dog"));
        tree.insert(new MyString("Elephant"));
        tree.insert(new MyString("Cat"));
        tree.insert(new MyString("Bear"));

        System.out.println("\n=== Бінарне дерево (postorder) ===");
        for (MyString s : tree) {
            System.out.println(s);
        }
    }
}
