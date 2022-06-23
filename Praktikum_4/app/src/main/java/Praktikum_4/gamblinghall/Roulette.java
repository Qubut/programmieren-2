package Praktikum_4.gamblinghall;

import java.util.Arrays;

public class Roulette extends SlotMachine implements GamblingMachine {
    private RouletteGameType rouletteGameType;
    private int betNumber;

    public Roulette(String name) {
        super(name);
        this.rouletteGameType = RouletteGameType.BLACK;
    }

    public Roulette(String name, RouletteGameType rouletteGameType) {
        super(name);
        if (rouletteGameType == RouletteGameType.NUMBER || rouletteGameType == null)
            throw new IllegalArgumentException("roulette Game type cannot be NUMBER or null");
        this.rouletteGameType = rouletteGameType;
    }

    public Roulette(String name, int betNumber) {
        super(name);
        if (betNumber < 0 || betNumber > 36)
            throw new IllegalArgumentException("betNumber must be between [0,36]");
        this.rouletteGameType = RouletteGameType.NUMBER;
        this.betNumber = betNumber;
    }

    public RouletteGameType getRouletteGameType() {
        return rouletteGameType;
    }

    public void setRouletteGameType(RouletteGameType rouletteGameType) {
        if (rouletteGameType == null)
            throw new IllegalArgumentException("roulette Game type cannot be NUMBER or null");
        this.rouletteGameType = rouletteGameType;
    }

    public int getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(int betNumber) {
        if(!(betNumber>0)||betNumber>36) throw new IllegalArgumentException();
        this.betNumber = betNumber;
    }

    private int generateRandom() {
        return (int) Math.floor(Math.random() * 37);
    }

    @Override
    public double play(double stake) {
/**
 * @param num: generated random number simulating the game
 * @param amount: amount to be won 
 * @param black: certain number slots in the game
 * @param red: same as @param black
 */
        // check for valid stake amount
        if (!(stake > 0) || stake > 10)
            throw new IllegalArgumentException();
        payIn(stake);
        // amount to be won
        var amount = stake * this.rouletteGameType.getAuszahlungsfaktor();
        var num = generateRandom();
        int black[] = { 15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26 };
        int red[] = { 32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3 };
        // checking game type and comparing the generated random number accordingly
        if (this.rouletteGameType == RouletteGameType.EVEN && num % 2 == 0) {
            this.payOut(amount);
            return amount;
        } else if (this.rouletteGameType == RouletteGameType.ODD && num % 2 != 0) {
            this.payOut(amount);
            return amount;
        } else if (this.rouletteGameType == RouletteGameType.BLACK) {
            if (Arrays.stream(black).anyMatch((n) -> num == n)) {
                payOut(amount);
                return amount;
            }
        } else if (this.rouletteGameType == RouletteGameType.RED) {
            if (Arrays.stream(red).anyMatch((n) -> num == n)) {
                payOut(amount);
                return amount;
            }
        } else if (this.rouletteGameType == RouletteGameType.P12 && 0 < num && num < 13) {
            payOut(amount);
            return amount;
        } else if (this.rouletteGameType == RouletteGameType.M12 && 12 < num && num < 25) {
            payOut(amount);
            return amount;

        } else if (this.rouletteGameType == RouletteGameType.D12 && 24 < num && num < 37) {
            payOut(amount);
            return amount;
        } else if (this.rouletteGameType == RouletteGameType.NUMBER && num == this.betNumber) {
            payOut(amount);
            return amount;
        }
        // if no winning
        return 0;
    }

    @Override
    public String toString() {
        return "Roulette{" +
                "rouletteGameType=" + rouletteGameType +
                ", betNumber=" + betNumber +
                '}';
    }

    @Override
    public double getRealPayOutRate() {
        return 1 - this.getProfit() / this.getRevenue();
    }
}
