package com.ldyfortest.concurrent;

import com.restservice.fortest.People;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationLearn {


    private static class MInvoktionHandler implements InvocationHandler {

        private Object object;

        public MInvoktionHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before invoktion ");
            Object res = method.invoke(object, args);
            System.out.println("after invoktion");
            return res;
        }
    }

    static class Student implements People {

        @Override
        public String work() {
            System.out.println("I just study ");
            return "ok";
        }
    }

    public static void main(String[] args) {

        People student = new Student();
        MInvoktionHandler mInvoktionHandler = new MInvoktionHandler(student);
        System.out.println(student.getClass().getClassLoader());
        People people = (People) Proxy.newProxyInstance(student.getClass().getClassLoader(),
                student.getClass().getInterfaces(), mInvoktionHandler);
        System.out.println(people.work());

    }

}
