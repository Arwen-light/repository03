package cn.itcast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 即使没有被代理对象，也可以使用动态代理，生成接口的实现类对象
 */
public class MyTest {
    /**
     * 学习框架：
     *  框架是别人写好的代码；
     *   使用框架的时候，就可以告诉框架，你帮我生成哪个接口的实现类对象
     * @param args
     */
    public static void main(String[] args) {
        //使用动态代理生成一个Human接口的实现类对象；
        /**
         * ClassLoader loader,类加载器
         * Class<?>[] interfaces,接口数组
         * InvocationHandler h,代理对象调用发发后的处理器，固定书写：new InvocationHandler(){//重写invoke方法}
         */
        Class[] interfaces = {Human.class};
        Human proxy = (Human) Proxy.newProxyInstance(MyTest.class.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                if (methodName.equals("sleep")){
                    System.out.println("躺在柳岩的怀里美美的睡一觉");
                }else if (methodName.equals("play")){
                    System.out.println("美美的玩一把王者荣耀");
                }
                return null;
            }
        });
        proxy.sleep();


        Human human = new Human() {
            @Override
            public void sleep() {
                System.out.println("aaaa");
            }

            @Override
            public void play() {
                System.out.println("bbbbb");
            }
        };

        human.sleep();
    }
}
