package playground;

import java.util.ArrayList;
import java.util.List;

public class JavaPassBy {

    public void addElement(List<Integer> elements, int value) {
        elements.add(value);
    }

    public static void main(String[] args) {
        JavaPassBy passBy = new JavaPassBy();
        List<Integer> mainElements = new ArrayList<>();
        passBy.addElement(mainElements, 4);
        System.out.println(mainElements);
    }
}
