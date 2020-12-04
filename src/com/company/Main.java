package com.company;


import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;


//      1
//class MyTh implements Runnable{
//
//    @Override
//    public void run() {
//    for (int i = 0; i < 5; i++){
//        try {
//            System.out.println("TEST");
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        Thread th = new Thread(new MyTh());
//        th.start();
//    }
//}

//          2
//public class Main {
//    public static void main(String[] args) {
//        Runnable r = ()->{
//            for (int i = 0; i < 5; i++) {
//                try {
//                    System.out.println("TEST");
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        Thread th = new Thread(r);
//        th.start();
//    }
//}


//  3 (stop thread)
//class MyTh extends Thread {
//
//    MyTh(String name){
//        super(name);
//    }
//    @Override
//    public void run() {
//        System.out.printf("%s start", Thread.currentThread().getName());
//        int count = 1 ;
//        while (!isInterrupted()){
//            System.out.println("TEST\t" + ++count);
//        }
//        System.out.printf("%s start", Thread.currentThread().getName());
//
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        MyTh th = new MyTh("TEST_THREAD");
//
//        try {
//            Thread.sleep(150);
//            th.start();
//            Thread.sleep(150);
//            th.interrupt();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//}

//  4 (sync threads)
//class CommonResource{
//    int x = 0;
//}
//
//class CountThread implements Runnable{
//    CommonResource res;
//    CountThread(CommonResource com){
//        res = com;
//    }
//    @Override
//    public void run() {
//        synchronized (res){
//            res.x = 1;
//            for (int i = 0; i < 5; i++){
//                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
//                res.x ++;
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        CommonResource resource = new CommonResource();
//        for (int i = 1; i < 6; i++){
//            Thread t = new Thread(new CountThread(resource));
//            t.setName("Thread_" + i);
//            t.start();
//        }
//    }
//}

//  full sync of threads

///class CommonResource
//{
//    int x = 0;
//    synchronized void inc()
//    {
//        for (int i = 0; i < 1; i++) // один поток(а)
//        {
//            System.out.printf(Thread.currentThread().getName() + "\t" + x + "\n" );
//            x++;
//            try
//            {
//                Thread.sleep(100);
//            }
//            catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
//class CountThread implements Runnable
//{
//    CommonResource res;
//    CountThread(CommonResource com)
//    {
//        res = com;
//    }
//    @Override
//    public void run()
//    {
//        synchronized (res)
//        {
//            res.inc();
//        }
//    }
//}
//
//public class Main
//{
//    public static void main(String[] args)
//    {
//        CommonResource resource = new CommonResource();
//        for (int i = 1; i < 6; i++)
//        {
//            Thread t = new Thread(new CountThread(resource));
//            t.setName("Thread_" + i);
//            t.start();
//        }
//    }
//}




/*class CommonResource
{
    String a = "a";
    int x = 0;
    synchronized void inc()
    {
        for (int i = 0; i < 1; i++)
        {
            for(int i1 = 1; i1 < 5; i1++)
            {
                a += "a";
                System.out.printf(Thread.currentThread().getName() + "\t" + x + "\t" + a + "\n" );
            }
            x++;
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class CountThread implements Runnable
{
    CommonResource res;
    CountThread(CommonResource com)
    {
        res = com;
    }
    @Override
    public void run()
    {
        synchronized (res)
        {
            res.inc();
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        CommonResource resource = new CommonResource();
        for (int i = 1; i < 6; i++)
        {
            Thread t = new Thread(new CountThread(resource));
            t.setName("Thread_" + i);
            t.start();
        }
    }
}*/



class CommonResource
{
    String str = "1a2b3c4d5f";
    int Letter = 0;
    int Digit = 0;
    synchronized void inc1()
    {
        for(char a : str.toCharArray())
        {
            if(Character.isLetter(a) == true)
            {
                Letter++;
            }
        }
        System.out.printf("Букв:" + "\t" + Letter + "\n" );
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    synchronized void inc2()
    {
        for(char a : str.toCharArray())
        {
            if(Character.isDigit(a) == true)
            {
                Digit++;
            }
        }
        System.out.printf("Цифр:" + "\t" + Digit + "\n" );
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

class CountThread implements Runnable
{
    CommonResource resource;
    CountThread(CommonResource com)
    {
        resource = com;
    }
    @Override
    public void run()
    {
        synchronized (resource)
        {
            resource.inc1();
            resource.inc2();
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        CommonResource resource = new CommonResource();
        Thread thread = new Thread(new CountThread(resource));
        thread.start();
    }
}