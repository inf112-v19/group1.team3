package inf112.skeleton.app.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionsCard {

    private String OptionsCardType;
    //Options Cards
    private String TractorBeam = "Pulls a target robot 1 space towards you";
    private String SuperiorArchive = "When reentering play robot does not receive damage";
    private String Recompile = "Once each turn, you may discard programming cards";
    private String RearFiringLaser = "Your robot gets a Rear-Firing-Laser";
    private String RammingGear = "Whenever your robot pushes or bumps a robot, it receives 1 damage";
    private String PressorBeam = "Moves target 1 space away from you";
    private String MechanicalArm = "Your robot can touch a flag or repair site 1 space away";
    private String FourthGear = "Move 4 spaces instead of 3";
    private String ExtraMemory = "You receive one extra program card each turn";
    private String DoubleBarredLaser = "Laser does 2 damage";
    private List<String> listOfOptonsCards = new ArrayList<>(Arrays.asList(TractorBeam,SuperiorArchive, Recompile, RearFiringLaser, RammingGear, PressorBeam,
            MechanicalArm,FourthGear,ExtraMemory,DoubleBarredLaser));

    private int numOfOptionsCards = listOfOptonsCards.size();

    public OptionsCard (int y) {

        switch (y) {
            case 1:
                OptionsCardType = TractorBeam;
                break;
            case 2:
                OptionsCardType = SuperiorArchive;
                break;
            case 3:
                OptionsCardType = Recompile;
                break;
            case 4:
                OptionsCardType = RearFiringLaser;
                break;
            case 5:
                OptionsCardType = RammingGear;
                break;
            case 6:
                OptionsCardType = PressorBeam;
                break;
            case 7:
                OptionsCardType = MechanicalArm;
                break;
            case 8:
                OptionsCardType = FourthGear;
                break;
            case 9:
                OptionsCardType = ExtraMemory;
                break;
            case 10:
                OptionsCardType = DoubleBarredLaser;
                break;
            default:
                OptionsCardType = "Invalid options card";
                break;
        }
    }
    public int getNumOfOptionsCards() {

        return listOfOptonsCards.size();
    }

    @Override
    public String toString() {
        return OptionsCardType.toString();
    }
}
