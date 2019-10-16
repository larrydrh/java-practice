package demo;

public class ReturnDemo {
    class Return {
        Return (int a, int b){
            this.a = a;
            this.b = b;
        }
        int a;
        int b;
    }
    public Return[] return1() {
        Return[] ret = new Return[2];
        ret[0] = new Return(1,2);
        ret[1] = new Return(1,2);
        return ret;
    }

    public static void main(String[] args) {
        ReturnDemo demo = new ReturnDemo();
        Return[] ret = demo.return1();
        System.out.println(ret[0].a);
    }
}
