package Praktikum_4.gamblinghall;

public class ArcadeFun extends SlotMachine{
    int stake;
    public ArcadeFun(String name){
        super(name);
        this.stake=1;
    }
    public ArcadeFun(String name, int stake){
        super(name);
        if(stake<1||stake>10) throw new IllegalArgumentException("stake must be betwenn [1,10]");
        this.stake = stake;
    }

    public int getStake() {
        return stake;
    }

    @Override
    public double play(double stake) {
        if(stake!=1) throw new IllegalArgumentException();
        payIn(stake);
        return 0;
    }
}
