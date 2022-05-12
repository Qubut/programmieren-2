package main;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Buff defenseBuff = new Buff("Defense", 2, 0);
        Buff rageBuff = new Buff("Rage", -2, 4);
        Arrays.stream(Category.values()).forEach((c) -> System.out.println(c.getName() + " " + c.getDescription()));
        Card wolf = new Card("Wolf",1,3,Category.ANIMAL,2);
        Card lawnMower = new Card("Lawn Mower",3,1,Category.MACHINE,4);
        Card unicorn= new Card("Unicorn",3,3,Category.MAGICAL_CREATURE,1);
        Card manchineelTree = new Card("Machineel Tree",1,4,Category.PLANT,5);
        System.out.println(wolf.toString());
        System.out.println(lawnMower.toString());
        System.out.println(unicorn.toString());
        System.out.println(manchineelTree.toString());
        wolf.attack(lawnMower);
        lawnMower.setBuff(defenseBuff);
        lawnMower.attack(wolf);
        unicorn.attack(manchineelTree);
        manchineelTree.setBuff(rageBuff);
        manchineelTree.attack(unicorn);
        manchineelTree.attack(unicorn);
    }
}
