package practice.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
    static interface Subject {
        void sayHi();
        void sayHello();
    }
    static class SubjectImpl implements Subject {
        @Override
        public void sayHi() {
            System.out.println("Hi");
        }
        @Override
        public void sayHello() {
            System.out.println("Hello");
        }
    }
    static class ProxyInvocationHandler implements InvocationHandler {
        private Object target;
        public ProxyInvocationHandler(Subject target) {
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.print("say:");
            return method.invoke(target, args);
        }
    }
    public static void main(String[] args) {
       Subject subject = new SubjectImpl();
       Subject subject1 = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new ProxyInvocationHandler(subject));
       subject1.sayHello();
       subject1.sayHi();
    }
}
