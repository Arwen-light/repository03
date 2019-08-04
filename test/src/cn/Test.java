package cn.itcast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
       /* Human person = new Person();
        person.sleep();
        person.play();*/
        /**
         * 需求，在person的任意方法执行之前，输出  **************start*******************
         * 在person独享的任意方法执行之后，输出   **************end*******************
         *
         * 要求：
         *  使用动态代理完成
         */

        //1.被代理对象 :  接口的实现类对象
        Human person = new Person();
        //2. 接口：让代理对象和被代理对象实现同样的接口，有同样的方法；


        //3.代理对象：   也是接口的实现类对象
        /**
         * ClassLoader loader,类加载器，固定写法：被代理对象.getClass().getClassLoader()
         * Class<?>[] interfaces,接口数组，固定写法：被代理对象.getClass().getInterfaces();
         * InvocationHandler h,代理对象调用发发后的处理器，固定书写：new InvocationHandler(){//重写invoke方法}
         */
        Human proxy = (Human) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new InvocationHandler() {
            /**
             *
             * @param proxy 代理对象，一般不用
             * @param method 当前调用的是哪个方法啊，Method就描述的是哪个方法
             * @param args 当前调用方法是传递的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //对方法完成增强
                System.out.println("**************start*******************");
                //执行被代理对象的方法
                Object result = method.invoke(person, args);
                System.out.println("**************end*******************");
                return result;
            }
        });

        //4.通过代理对象，调用方法,都会执行invoke方法
        proxy.sleep();
        proxy.play();


    }
}
