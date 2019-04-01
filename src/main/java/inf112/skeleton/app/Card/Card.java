package inf112.skeleton.app.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {

    private String cardType;
    //Programming cards
    private String move1 = "Move 1";
    private String move2 = "Move 2";
    private String move3 = "Move 3";
    private String rightTurn = "Right turn";
    private String leftTurn = "Left turn";
    private String uTurn = "U turn";
    private String moveBack = "Move back";
    private String again = "Again";
    private List<String> listOfCards = new ArrayList<>(Arrays.asList(move1, move2, move3, rightTurn,
            leftTurn, uTurn, moveBack, again));


    private int numOfCards = listOfCards.size();
    /*powerUp to be used later
    private String powerUp = "Power up";
    */


    public Card(int x) {

        switch (x) {
            case 1:
                cardType = move1;
                break;
            case 2:
                cardType = move2;
                break;
            case 3:
                cardType = move3;
                break;
            case 4:
                cardType = rightTurn;
                break;
            case 5:
                cardType = leftTurn;
                break;
            case 6:
                cardType = uTurn;
                break;
            case 7:
                cardType = moveBack;
                break;
            case 8:
                cardType = again;
                break;
            default:
                cardType = "Invalid card";
                break;
        }
    }


    //add item in list of cards
    public void addCardToList(String card) {

        listOfCards.add(card);
    }

    //remove item in list of cards
    public void removeCardFromList(int index) {

        listOfCards.remove(index);
    }

    public int getNumOfCards() {

        return listOfCards.size();
    }


    @Override
    public String toString() {
        return cardType.toString();
    }
}
