import java.util.Objects;

/**
 * class representing the trading card game
 * 
 * @version 1.0
 */
public class Card {
    private final String name;
    private final int baseDefense;
    private final int baseAttack;
    private Category category;
    private int health;
    private Buff buff;

    /**
     * 
     * @param name        card name
     * @param baseDefense basic defense
     * @param baseAttack  basic attack
     * @param category
     * @param health      health cannot be less than zero
     */

    public Card(String name, int baseDefense, int baseAttack, Category category, int health) {
        if (name != null && !name.isEmpty())
            this.name = name;
        else
            throw new IllegalArgumentException("Name can't be null or empty");
        if (baseDefense >= 0)
            this.baseDefense = baseDefense;
        else
            throw new IllegalArgumentException("Base defense can not be less than 0");
        if (baseAttack >= 0)
            this.baseAttack = baseAttack;
        else
            throw new IllegalArgumentException("Attack defense can not be less than 0");
        if (category!=null)
        this.category = category;
        else
            throw new IllegalArgumentException("Attack defense can not be less than 0");
        if (health > 0)
            this.health = health;
        else
            throw new IllegalArgumentException("Health can must be more than 0");
        this.buff = null;
    }

    public String getName() {
        return name;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public Category getCategory() {
        return category;
    }

    public int getHealth() {
        return health;
    }

    public Buff getBuff() {
        return buff;
    }

    public boolean isAlive() {
        return (getHealth() > 0) ? true : false;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public int getDefense() {
        ;
        return (getBuff() != null) ? formatValue(getBaseDefense() + getBuff().getDefenseBuff()) : getBaseDefense();
    }

    public int getAttack() {

        return (getBuff() != null) ? formatValue(getBaseAttack() + getBuff().getAttackBuff()) : getBaseAttack();
    }

    /**
     * <p>
     * deducts from the other card if the defense less than the attack
     * </p>
     * <p>
     * deducts 1 point if the defense is equal to the attack
     * </p>
     * <p>
     * does nothing if the defense more than the attack
     * </p>
     * 
     * @param otherCard cannot be null and it's health must be more than 0
     */
    public void attack(Card otherCard) {
        if (otherCard != null) {
            if (otherCard.isAlive() && this.isAlive()) {
                if (otherCard.getDefense() < this.getAttack())
                    otherCard.health = formatValue(otherCard.health + otherCard.getDefense() - this.getAttack());
                else if (this.getAttack() == otherCard.getDefense())
                    otherCard.health = formatValue(otherCard.health - 1);
                else
                    return;

            } else
                throw new IllegalArgumentException("Creature must be alive");
        } else
            throw new IllegalArgumentException("card cannot be null");
    }

    @Override
    public String toString() {
        return "Card: \n" +
                "name: " + name +
                ", baseDefense: " + baseDefense +
                ", baseAttack: " + baseAttack +
                ", category: " + category +
                ", health: " + health +
                ", buff: " + buff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Card))
            return false;
        Card card = (Card) o;
        return getBaseDefense() == card.getBaseDefense() &&
                getBaseAttack() == card.getBaseAttack() &&
                getHealth() == card.getHealth() &&
                getName().equals(card.getName()) &&
                getCategory() == card.getCategory() &&
                Objects.equals(getBuff(),card.getBuff());
    }

    /**
     * turns negative values to zero
     * 
     * @param value
     * @return value
     */
    private static int formatValue(int value) {
        return (value > 0) ? value : 0;
    }
}
