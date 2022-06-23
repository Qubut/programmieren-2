package Praktikum_4.gamblinghall;

public enum RouletteGameType {
    NUMBER("Plein",52),RED("Rouge",2),
    BLACK("Noir",2),EVEN("Pair",2),
    ODD("impair",2),LOW("Manque",2),
    HIGH("Passe",2),P12("Premiere douzaine",3),
    M12("Moyenne douzaine",3),D12("Derniere douzaine",3);
    private final String name;
    private final int auszahlungsfaktor;

    RouletteGameType(String name, int auszahlungsfaktor) {
        if(name==null||name.isEmpty()) 
            throw new IllegalArgumentException("name cann't be null or empty");
        if(!(auszahlungsfaktor>0))
            throw new IllegalArgumentException("Auszahlungfaktor <= 0");
        this.name =name;
        this.auszahlungsfaktor=auszahlungsfaktor;
    }
    public String getName(){
        return this.name;
    }
    public int getAuszahlungsfaktor(){
        return this.auszahlungsfaktor;
    }
}
