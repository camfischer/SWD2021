public class Card {
    private Suit cardSuit;
    private int value;

    /**
     * Card constructor that sets Suit and value of Card.
     * @param suit
     * @param num
     */
    public Card(Suit suit, int num){
        this.cardSuit = suit;
        this.value = num;
    }

    public int getValue() {
        return value;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    /**
     * ToString method that allows card to be printed out.
     * @return
     */
    public String toString(){
        String val = "";
        switch(value){
            case 1:
                val = "A";
                break;
            case 2:
                val = "2";
                break;
            case 3:
                val = "3";
                break;
            case 4:
                val = "4";
                break;
            case 5:
                val = "5";
                break;
            case 6:
                val = "6";
                break;
            case 7:
                val = "7";
                break;
            case 8:
                val = "8";
                break;
            case 9:
                val = "9";
                break;
            case 10:
                val = "10";
                break;
            case 11:
                val = "Jack";
                break;
            case 12:
                val = "Queen";
                break;
            case 13:
                val = "King";
                break;
            default:
                System.out.println("No card");

        }
        return val;
    }


}
