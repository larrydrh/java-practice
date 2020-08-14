package practice.pattern;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.TreeSet;


public class CglibProxy implements MethodInterceptor
{
    public Object CreatProxyedObj(Class<?> clazz)
    {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);

        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        // 这里增强
        System.out.println("收钱");

        return arg3.invokeSuper(arg0, arg2);
    }
    public interface Money {
        public String printMoney();
        public String printMoney1();
    }
    public class MoneyImp implements Money{
        @Override
        public String printMoney() {
            System.out.println("5元");
//            printMoney1();

            return "xx";
        }

        @Override
        public String printMoney1() {
            System.out.println("15元");
            return "xx";
        }
    }

    static public class CglibUserDao {
        private String name;

        public String getName() {

            System.out.println("get name");
            setName("xxx");
            return name;
        }

        public void setName(String name) {
            System.out.println("set name");
            this.name = name;
        }

        public void demand(String demandName) {
            System.out.println(name + " implemented demand:" + demandName);
        }
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        CglibUserDao money = (CglibUserDao)cglibProxy.CreatProxyedObj(CglibUserDao.class);
        money.getName();
//        money.setName("zhu");
//        money.demand("xxxx");
        TreeSet<Integer> a;

    }
}