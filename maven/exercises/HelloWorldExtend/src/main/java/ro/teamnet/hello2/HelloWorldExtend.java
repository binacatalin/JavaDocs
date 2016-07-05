package ro.teamnet.hello2;

import ro.teamnet.hello.HelloWorld;

/**
 * Created by user on 7/5/2016.
 */
public class HelloWorldExtend {

    /**
     * Some comments
     */
    public HelloWorldExtend() {
    }

    /**
     * Some comments2
     */
    public void extendSayHello() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
        System.out.println("The new Hello World");
    }

}

