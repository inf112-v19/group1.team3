package inf112.skeleton.app.Card;

import org.junit.Test;
import inf112.skeleton.app.Card.Card;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void createCard() {

        //check that expected cards gets created
        Card card1 = new Card(1);
        assertEquals(card1.toString(), "Move 1");
        Card card2 = new Card(2);
        assertEquals(card2.toString(), "Move 2");
        Card card3 = new Card(3);
        assertEquals(card3.toString(), "Move 3");
        Card card4 = new Card(4);
        assertEquals(card4.toString(), "Right turn");
        Card card5 = new Card(5);
        assertEquals(card5.toString(), "Left turn");
        Card card6 = new Card(6);
        assertEquals(card6.toString(), "U turn");
        Card card7 = new Card(7);
        assertEquals(card7.toString(), "Move back");
        Card card8 = new Card(8);
        assertEquals(card8.toString(), "Again");

        //check that out of index int returns proper feedback
        Card invalidCard = new Card(card1.getNumOfCards()+100);
        assertEquals(invalidCard.toString(), "Invalid card");

    }
}