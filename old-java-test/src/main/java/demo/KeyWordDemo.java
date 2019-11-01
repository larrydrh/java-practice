package demo;

/**
 * @author rhding
 */
public class KeyWordDemo {
    static {
        System.out.println("this is a static block");
    }
    public void test() {
        System.out.println("im in func test");
    }

    public static void main(String[] args) {
        KeyWordDemo keyWordDemo = new KeyWordDemo();
        keyWordDemo.test();
        KeyWordDemo keyWordDemo1 = new KeyWordDemo();
        keyWordDemo1.test();
        System.out.println("inside main");
    }
}
