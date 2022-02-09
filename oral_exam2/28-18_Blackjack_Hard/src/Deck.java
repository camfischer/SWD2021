import java.util.*;

public class Deck {
    private Card[] deckCards;
    private int nextCard;

    /**
     * Generate new deck whenever constructor is called and populates it.
     */
    public Deck(){
        this.deckCards = new Card[52];
        int num =0;


        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                this.deckCards[num] = new Card(Suit.values()[i], j);
                num++;
            }

        }
        shuffle();
    }


    /**
     * Shuffles deck
     */
    public void shuffle(){
        Collections.shuffle(Arrays.asList(deckCards));
    }

    /**
     * return next card
     * @return
     */
    public Card hit(){
        if(nextCard < 52){
            nextCard++;
        }

        return deckCards[nextCard];
    }

}
