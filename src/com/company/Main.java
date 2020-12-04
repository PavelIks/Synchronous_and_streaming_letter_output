package com.company;

class CommonResource
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
}