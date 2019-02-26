package inf112.skeleton.app.Deck;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import inf112.skeleton.app.Card.Card;

public class Deck {

    private int size = 20;
    private ArrayList<Card> cards = new ArrayList<Card>(size);
    private int numOfCards = 8;


    public Deck() {

        for (int i=0; i<size; i++) {

            int randomNum = ThreadLocalRandom.current().nextInt(1, numOfCards + 1);
            Card card = new Card(randomNum);
            cards.add(i, card);
        }

    }

    public ArrayList<Card> showCards() {

        return cards;
    }



}
