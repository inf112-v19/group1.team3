package inf112.skeleton.app.Deck;

import org.junit.Test;
import inf112.skeleton.app.Card.Card;

import static org.junit.Assert.*;

public class DeckTest {


    @Test
    public void createDeck() {
        Deck deck = new Deck();
        assertEquals(deck.showCards().size(), 20);
        System.out.println(deck.showCards());

        //check that all elements in deck are Card objects
        for (int i = 0; i < deck.showCards().size(); i++){
            assertTrue(deck.showCards().get(i) instanceof Card, true);
        }
    }

    @Test
    public void shuffleDeck() {
        Deck deck = new Deck();
        int sizeBeforeShuffle = deck.showCards().size();
        deck.shuffleDeck();

        //check that size of deck is the same before and after shuffle
        assertEquals(deck.showCards().size(), sizeBeforeShuffle);

    }

    @Test
    public void selectFive() {
        Deck deck = new Deck();
        assertEquals(deck.selectFive().size(), 5);
        System.out.println(deck.selectFive());
    }

    @Test
    public void selectNine() {
        Deck deck = new Deck();
        assertEquals(deck.selectNine().size(), 9);
        System.out.println(deck.selectNine());

    }


}