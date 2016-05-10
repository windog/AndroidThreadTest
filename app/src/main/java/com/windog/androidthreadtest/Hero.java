package com.windog.androidthreadtest;

import android.util.Log;

/**
 * Created by windog on 2016/5/10.
 */
public class Hero {

    //英雄名字
    private String name;
    //英雄自身血量
    private int hp;
    //英雄攻击力
    private int damage;

    //构造函数
    public Hero(int hp, int damage, String name) {
        this.hp = hp;
        this.damage = damage;
        this.name = name;
    }

    //每个英雄自带攻击的方法
    public void attack(Hero hero){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hero.hp = hero.hp - damage;
        Log.e("Attack",name + "正在攻击" + hero.name + "," + hero.name + "的HP变成了" + hero.hp);

        if (hero.isDead()) {
            Log.e("Dead", hero.name + "is dead!!");
        }
    }

    //英雄死亡的判断
    public boolean isDead(){
        if (0 >= hp) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
