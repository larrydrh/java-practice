package practice.pattern;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


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
    }
    public class MoneyImp implements Money{
        @Override
        public String printMoney() {
            System.out.println("5元");
            return "xx";
        }
    }

    static public class CglibUserDao {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void demand(String demandName) {
            System.out.println(name + " implemented demand:" + demandName);
        }
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        CglibUserDao money = (CglibUserDao)cglibProxy.CreatProxyedObj(CglibUserDao.class);
        money.setName("zhu");
        money.demand("xxxx");


    }
}