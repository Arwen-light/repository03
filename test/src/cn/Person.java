package cn.itcast;

public class Person implements Human {
    @Override
    public void sleep() {
        System.out.println("睡觉中.....");
    }

    @Override
    public void play() {
        System.out.println("玩游戏中......");
    }
}
