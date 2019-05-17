package com.example.cookiemonster;

import java.util.Random;

public class CookieMonster
{
    int cookie;
    int eaten;
    int timer;
    Random random=new Random();
    public CookieMonster()
    {
        cookie = 0;
        eaten = 0;
        Random random = new Random() ;
        timer = random.nextInt(5)+1;
    }
    public int getCookie()
    {
        return cookie;
    }

    public int getEaten()
    {
        return eaten;
    }

    public void EatSomeCookie(int clock)
    {
        if(timer==clock)
        {
            if(cookie!=0)
            {
                eaten+=this.cookie;
                this.cookie=0;
            }
            timer=random.nextInt(6)+(clock+1);
        }
        else timer=random.nextInt(6)+(clock+1);
    }
    @Override
    public String toString()
    {
        return "CookieMonster{" +
                "cookie=" + cookie +
                ", eaten=" + eaten +
                ", timer=" + timer +
                ", random=" + random +
                '}';
    }
}
