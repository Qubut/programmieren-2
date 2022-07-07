package Quartet;

import java.util.Objects;
/**
@param name: card's name of a specific car
@param cylinderCapacity: size of the engine
@param cylinderCount: number of cylineders in the engine
@param horsePower: engine's horsePower
@param acceleration: engine's acc. abilitiy in seconds,
to go from 0-100 km/h
*/
public class Card implements Comparable<Card> {
    private String name;
    private final int cylinderCapacity;
    private final int cylinderCount;
    private final int horsePower;
    private final int acceleration;

    /**
    @exception name empty or null or any other parameter <= 0
    @throws IllegalArgumentException
    */
    public Card(String name, int cylinderCapacity, int cylinderCount, int horsePower, int acceleration) {
        if (name==null||name.isEmpty() || cylinderCapacity <= 0 || cylinderCount <= 0 || horsePower <= 0
                || acceleration <= 0)
            throw new IllegalArgumentException(
                    "Name cann't be Empty and other values cannot be less than or equal to 0\nacceleration cannot me more than 100");
        this.name = name;
        this.cylinderCapacity = cylinderCapacity;
        this.cylinderCount = cylinderCount;
        this.horsePower = horsePower;
        this.acceleration = acceleration;
    }
    // getters
    public String getName() {
        return name;
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    public int getCylinderCount() {
        return cylinderCount;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getAcceleration() {
        return acceleration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Card))
            return false;
        Card card = (Card) o;
        return getCylinderCapacity() == card.getCylinderCapacity() &&
                getCylinderCount() == card.getCylinderCount() &&
                getHorsePower() == card.getHorsePower() &&
                getAcceleration() == card.getAcceleration() &&
                Objects.equals(getName(), card.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCylinderCapacity(), getCylinderCount(), getHorsePower(), getAcceleration());
    }
    /**
    compare based on acceleration -> horsePower ->
    cylinder capacity -> cylinder count -> name of car's brand
    */
    @Override
    public int compareTo(Card otherCard) {
        int result = Integer.compare(this.getAcceleration(), otherCard.getAcceleration());
        if (result == 0)
            result = -Integer.compare(this.getHorsePower(), otherCard.getHorsePower());
        if (result == 0)
            result = -Integer.compare(this.getCylinderCapacity(), otherCard.getCylinderCapacity());
        if (result == 0)
            result = -Integer.compare(this.getCylinderCount(), otherCard.getCylinderCount());
        if(result==0)
            result = this.getName().compareTo(otherCard.getName());
        return result;
    }

    @Override
    public String toString() {

                return "+++ " +this.name+ "+++\n" +
                "Hubraum:       " +this.cylinderCapacity+"\n" +
                "Zylinder:      " +this.cylinderCount+"\n"+
                "PS:            " +this.horsePower+"\n"+
                "0 - 100 km/h:  " +this.acceleration+"\n";

    }
}
