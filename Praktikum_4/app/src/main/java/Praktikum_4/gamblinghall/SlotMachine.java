package Praktikum_4.gamblinghall;

import java.util.Objects;

public abstract class SlotMachine {
    private final String name;
    private double revenue;
    private int countGames;
    private int countWins;
    private double profit;
    public SlotMachine(String name) {
       if(name ==null || name.isEmpty()) throw new IllegalArgumentException("name cann't be null or empty");
        this.name=name;
        this.revenue=0;
        this.countGames=0;
        this.countWins=0;
        this.profit=0;
    }
    public String getName(){
        return this.name;
    };
    public double getRevenue(){
        return this.revenue;
    };
    public double getProfit(){
        return profit;
    };
    public int getCountGames(){
        return this.countGames;
    };
    public int getCountWins(){
        return this.countWins;
    };
    public  void payIn(double cash){
        if(!(cash > 0)) throw new IllegalArgumentException("cash not bigger than 0");
        this.revenue+=cash;
        this.profit+=cash;
    };

    @Override
    public String toString() {
        return "SlotMachine{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlotMachine)) return false;
        SlotMachine that = (SlotMachine) o;
        return Double.compare(that.getRevenue(), getRevenue()) == 0 &&
                getCountGames() == that.getCountGames() &&
                getCountWins() == that.getCountWins() &&
                Double.compare(that.getProfit(), getProfit()) == 0 &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRevenue(), getCountGames(), getCountWins(), getProfit());
    }

    public void payOut(double cash){
        if(!(cash > 0)) throw new IllegalArgumentException("cash not bigger than 0");
        this.profit-=cash;
    };
    public abstract double play(double stake);

}
