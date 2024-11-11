package playground;

public class TestMutable {

    public static void main(String[] args) {
        String name1 = "test";
        String name2 = name1;
        name1 = "rename";
        System.out.println(name1);
        System.out.println(name2);

        Integer num1 = 1;
        Integer num2 = num1;
        num1 = 2;
        System.out.println(num1);
        System.out.println(num2);
    }
}
