package inf112.skeleton.app.Card;

public class Card {

    private String cardType;
    //Programming cards
    private String move1 = "Move 1";
    private String move2 = "Move 2";
    private String move3 = "Move 3";
    private String rightTurn = "Right Turn";
    private String leftTurn = "Left Turn";
    private String uTurn = "U turn";
    private String backUp = "Back up";
    private String again = "Again";

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
                cardType = backUp;
                break;
            case 8:
                cardType = again;
                break;
            default:
                cardType = "Invalid card";
                break;
        }
    }

    @Override
    public String toString() {
        return cardType.toString();
    }
}
