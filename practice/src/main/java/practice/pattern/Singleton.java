package practice.pattern;



public class Singleton {
    private static Singleton instance;
    private Singleton() {};
    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
            return instance;

        }
        return instance;
    };
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        if (singleton1 == singleton2) {
            System.out.println("singleton1 and singleton2 equal");
        } else {
            System.out.println("not equal");
        }

    }
}
