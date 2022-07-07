package Quartet;

public class CardDeckException extends RuntimeException{
    public CardDeckException(String errMssg,Throwable e){
        super(errMssg,e);
    }
}
