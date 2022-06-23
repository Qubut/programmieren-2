package Praktikum_4.gamblinghall;

import java.util.Objects;
import java.util.SplittableRandom;

public class DoubleShot extends SlotMachine implements GamblingMachine {
    private int stake;
    private double jackpot;
    private int countJackpot;

    public DoubleShot(String name) {
        super(name);
        this.stake = 1;
        this.jackpot = 0;
    }

    public DoubleShot(String name, int stake) {
        super(name);
        if (!checkstake(stake))
            throw new IllegalArgumentException("stake must be between [1, 10]");
        this.stake = stake;
        this.jackpot = 0;
    }

    public int getStake() {
        return stake;
    }

    public double getJackpot() {
        return jackpot;
    }

    public int getCountJackpot() {
        return countJackpot;
    }

    @Override
    public double play(double stake) {
        /***
         * @param random:    a random number generator based on a given range
         * @param winchance: chance of winning a jackpot
         * @param versuch:   random number from @param random simulating probability
         * @param maywin:    a boolean calculating the chance of winning to the actual
         *                   odds
         */
        // check for valid stack values
        if (!checkstake(stake) || stake != this.stake)
            throw new IllegalArgumentException("stake must be between [1,10]");
        payIn(stake);
        // adding 10% from stake to the jackpot
        this.jackpot += (stake * 0.1);
        // simulating probability
        var random = new SplittableRandom();
        double winChance = 0.001;
        double versuch = random.nextDouble(0, 1);
        boolean maywin = versuch <= winChance;
        if (maywin) {
            payOut(jackpot);
            var tmp = jackpot;
            jackpot = 0;
            return tmp;
        } else if (versuch <= 0.41) {
            payOut(2 * stake);
            return 2 * stake;
        } else {

            return 0;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStake(), getJackpot(), getCountJackpot());
    }

    private boolean checkstake(double stake) {
        return stake > 0 && stake <= 10 ? true : false;
    }

    @Override
    public double getRealPayOutRate() {
        return 1 - (this.getProfit() / this.getRevenue());
    }

}
