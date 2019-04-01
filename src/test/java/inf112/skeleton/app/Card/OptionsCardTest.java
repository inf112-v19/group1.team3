package inf112.skeleton.app.Card;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsCardTest {


    @Test
    public void CreateOptionsCard() {
        //check that expected cards gets created
        OptionsCard optCard1 = new OptionsCard(1);
        assertEquals(optCard1.toString(), "Pulls a target robot 1 space towards you");
        OptionsCard optCard2 = new OptionsCard(2);
        assertEquals(optCard2.toString(), "When reentering play robot does not receive damage");
        OptionsCard optCard3 = new OptionsCard(3);
        assertEquals(optCard3.toString(), "Once each turn, you may discard programming cards");
        OptionsCard optCard4 = new OptionsCard(4);
        assertEquals(optCard4.toString(), "Your robot gets a Rear-Firing-Laser");
        OptionsCard optCard5 = new OptionsCard(5);
        assertEquals(optCard5.toString(), "Whenever your robot pushes or bumps a robot, it receives 1 damage");
        OptionsCard optCard6 = new OptionsCard(6);
        assertEquals(optCard6.toString(), "Moves target 1 space away from you");
        OptionsCard optCard7 = new OptionsCard(7);
        assertEquals(optCard7.toString(), "Your robot can touch a flag or repair site 1 space away");
        OptionsCard optCard8 = new OptionsCard(8);
        assertEquals(optCard8.toString(), "Move 4 spaces instead of 3");
        OptionsCard optCard9 = new OptionsCard(9);
        assertEquals(optCard9.toString(), "You receive one extra program card each turn");
        OptionsCard optCard10 = new OptionsCard(10);
        assertEquals(optCard10.toString(), "Laser does 2 damage");

        //check that out of index int returns proper feedback
        OptionsCard invalidOptCard = new OptionsCard(optCard1.getNumOfOptionsCards()+100);
        assertEquals(invalidOptCard.toString(), "Invalid options card");

    }

}