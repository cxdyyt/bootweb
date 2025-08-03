package my.springboot.start.bootstudy;

public class StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    static void sayHello(Human guy) {
        System.out.println("hello,guy！");
    }

    static void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    static void sayHello(Woman guy) {
        System.out.println("hello,lady！");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello((Woman) woman);
    }
}