

/**
 * <h2>Buff increseas the Cards attack or defense</h2>
 * 
 */
public class Buff {

    private final String name;
    private final int defenseBuff;
    private final int attackBuff;

    /**
     * 
     * @param name        buffer name
     * @param defenseBuff defense's boost
     * @param attackBuff  attack's boost
     */
    public Buff(String name, int defenseBuff, int attackBuff) {
        if (name != null && !name.isEmpty())
            this.name = name;
        else
            throw new IllegalArgumentException("Name must not be null or empty");
        if (defenseBuff > 0 || attackBuff > 0) {
            this.defenseBuff = defenseBuff;
            this.attackBuff = attackBuff;
        } else
            throw new IllegalArgumentException("Defense or Attack buffer less than or equal to Zero was passed");
    }

    public String getName() {
        return name;
    }

    public int getDefenseBuff() {
        return defenseBuff;
    }

    public int getAttackBuff() {
        return attackBuff;
    }

    @Override
    public String toString() {
        return name + " (" +
                "D:" + defenseBuff +
                ", A:" + attackBuff +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Buff))
            return false;
        Buff buff = (Buff) o;
        return getDefenseBuff() == buff.getDefenseBuff() &&
                getAttackBuff() == buff.getAttackBuff() &&
                getName().equals(buff.getName());
    }
}
