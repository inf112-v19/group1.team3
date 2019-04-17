package inf112.skeleton.app.Deck;

import inf112.skeleton.app.Card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {

    private int size = 20;
    private ArrayList<Card> cards = new ArrayList<Card>(size);
    private int numOfCards = 8;


    public Deck() {

        for (int i = 0; i < size; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, numOfCards + 1);
            Card card = new Card(randomNum);
            cards.add(i, card);
        }

    }

    //show cards from deck
    public ArrayList<Card> showCards() {

        return cards;
    }

    //shuffle deck
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    //select nine cards from deck
    public ArrayList<Card> selectNine() {
        ArrayList<Card> nineCards = new ArrayList<Card>(9);
        for (int i = 0; i < 9; i++) {
            nineCards.add(i, cards.get(i));
        }
        return nineCards;
    }

    //select five random cards from deck
    public ArrayList<Card> selectFiveRandom() {
        //use shuffleDeck for randomization
        shuffleDeck();
        ArrayList<Card> fiveCards = new ArrayList<Card>(5);
        for (int i = 0; i < 5; i++) {
            fiveCards.add(i, cards.get(i));
        }
        return fiveCards;
    }

    //select five cards from deck
    public ArrayList<Card> selectFive(int one, int two, int three, int four, int five) {
        ArrayList<Card> fiveCards = new ArrayList<Card>(5);

        fiveCards.add(0, cards.get(one));
        fiveCards.add(1, cards.get(two));
        fiveCards.add(2, cards.get(three));
        fiveCards.add(3, cards.get(four));
        fiveCards.add(4, cards.get(five));

        return fiveCards;
    }


}
