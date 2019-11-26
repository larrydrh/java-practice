package practice.concurrent;

public class ProducerConsumer {

    public static class Res {
        private String name;
        private Res(String name) {
            this.name = name;
        }
    }
    public static class Producer extends Thread{
        private Res res;
        private Producer(Res res){
            this.res = res;
        }
        @Override
        public void run() {
            System.out.println("start produce");
            while (true) {
                synchronized (res) {
                    try {
//                        System.out.println("start produce");
                        res.name = "zhuqian";
                        res.notify();
                        res.wait();
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        res.name = "renhuan";
                        res.notify();
                        res.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class Consumer extends Thread {
        private Res res;

        private Consumer(Res res) {
            this.res = res;
        }
        @Override
        public void run() {
            System.out.println("consumer");
            while (true) {
                synchronized (res) {
                    try {

//                        System.out.println("consumer");
                        System.out.println(res.name);
                        res.notify();
                        res.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Res res = new Res("renhuan");
        new Producer(res).start();
        new Consumer(res).start();
    }
}
