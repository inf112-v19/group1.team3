package inf112.skeleton.app.UI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.Card.Card;
import inf112.skeleton.app.Deck.Deck;
import java.util.*;

public class UI extends ApplicationAdapter {

    //one Sprite for each card type
    private Sprite sprite1;
    private Sprite sprite2;
    private Sprite sprite3;
    private Sprite sprite4;
    private Sprite sprite5;
    private Sprite sprite6;
    private Sprite sprite7;
    private Sprite sprite8;

    private BitmapFont font;

    public UI() {
        sprite1 = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move1.png")));
        sprite2 = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move2.png")));
        sprite3 = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move3.png")));
        sprite4 = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/again.png")));
        sprite5 = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/leftTurn.png")));
        sprite6 = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/rightTurn.png")));
        sprite7 = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/uTurn.png")));
        sprite8 = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/moveBack.png")));
    }

    public void startGameText(Batch batch, float parentAlpha) {

        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(2,2);
        font.draw(batch, "Press a key or touch the screen to start the game!", 200, 600);

    }

    public void drawNine(Batch batch, float parentAlpha, ArrayList<Card> nineCards) {
        int x = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(nineCards.get(x).toString().equals("Move 1")) {
                    batch.draw(sprite1, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Move 2")) {
                    batch.draw(sprite2, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Move 3")) {
                    batch.draw(sprite3, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Again")) {
                    batch.draw(sprite4, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Left turn")) {
                    batch.draw(sprite5, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Right turn")) {
                    batch.draw(sprite6, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("U turn")) {
                    batch.draw(sprite7, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Move back")) {
                    batch.draw(sprite8, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                x++;
            }
        }

    }

    public void drawFive(Batch batch, float parentAlpha, ArrayList<Card> fiveCards) {
        int x = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                //stop after 5 cards
                if(x==5) {
                    break;
                }
                if (fiveCards.get(x).toString().equals("Move 1")) {
                    batch.draw(sprite1, 768 + j * 100, 668 - i * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Move 2")) {
                    batch.draw(sprite2, 768 + j * 100, 668 - i * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Move 3")) {
                    batch.draw(sprite3, 768 + j * 100, 668 - i * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Again")) {
                    batch.draw(sprite4, 768 + j * 100, 668 - i * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Left turn")) {
                    batch.draw(sprite5, 768 + j * 100, 668 - i * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Right turn")) {
                    batch.draw(sprite6, 768 + j * 100, 668 - i * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("U turn")) {
                    batch.draw(sprite7, 768 + j * 100, 668 - i * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Move back")) {
                    batch.draw(sprite8, 768 + j * 100, 668 - i * 100, 100, 100);
                }
                x++;
            }
        }

    }
}
