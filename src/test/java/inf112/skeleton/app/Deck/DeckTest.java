package inf112.skeleton.app.Deck;

import inf112.skeleton.app.Card.Card;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeckTest {


    @Test
    public void createDeck() {
        Deck deck = new Deck();
        assertEquals(deck.showCards().size(), 20);
        //system.out.println(deck.showCards());

        //check that all elements in deck are Card objects
        for (int i = 0; i < deck.showCards().size(); i++){
            assertTrue(deck.showCards().get(i) instanceof Card);
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
    public void selectFiveRandom() {
        Deck deck = new Deck();
        assertEquals(deck.selectFiveRandom().size(), 5);
        //system.out.println(deck.selectFiveRandom());
    }

    @Test
    public void selectFive() {
        Deck deck = new Deck();
        assertEquals(deck.selectFive(1,3,4,3,7).size(), 5);
        //system.out.println(deck.selectFive(1,3,4,3,7));
    }


    @Test
    public void selectNine() {
        Deck deck = new Deck();
        assertEquals(deck.selectNine().size(), 9);
        //system.out.println(deck.selectNine());

    }


}