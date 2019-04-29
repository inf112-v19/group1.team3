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


    private Sprite sprite;
    private BitmapFont font;

    public UI() {

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
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move1.png")));
                    batch.draw(sprite, 768 + i * 100, 668 - j * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Move 2")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move2.png")));
                    batch.draw(sprite, 768 + i * 100, 668 - j * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Move 3")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move3.png")));
                    batch.draw(sprite, 768 + i * 100, 668 - j * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Again")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/again.png")));
                    batch.draw(sprite, 768 + i * 100, 668 - j * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Left turn")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/leftTurn.png")));
                    batch.draw(sprite, 768 + i * 100, 668 - j * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Right turn")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/rightTurn.png")));
                    batch.draw(sprite, 768 + i * 100, 668 - j * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("U turn")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/uTurn.png")));
                    batch.draw(sprite, 768 + i * 100, 668 - j * 100, 100, 100);
                }
                else if(nineCards.get(x).toString().equals("Move back")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/moveBack.png")));
                    batch.draw(sprite, 768 + i * 100, 668 - j * 100, 100, 100);
                }
                x++;
            }
        }

    }

    //TODO: draw in correct order
    public void drawFive(Batch batch, float parentAlpha, ArrayList<Card> fiveCards) {
        int x = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 2; j++) {
                if (fiveCards.get(x).toString().equals("Move 1")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move1.png")));
                    batch.draw(sprite, 768 + i * 100, 268 - j * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Move 2")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move2.png")));
                    batch.draw(sprite, 768 + i * 100, 268 - j * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Move 3")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/move3.png")));
                    batch.draw(sprite, 768 + i * 100, 268 - j * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Again")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/again.png")));
                    batch.draw(sprite, 768 + i * 100, 268 - j * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Left turn")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/leftTurn.png")));
                    batch.draw(sprite, 768 + i * 100, 268 - j * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Right turn")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/rightTurn.png")));
                    batch.draw(sprite, 768 + i * 100, 268 - j * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("U turn")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/uTurn.png")));
                    batch.draw(sprite, 768 + i * 100, 268 - j * 100, 100, 100);
                } else if (fiveCards.get(x).toString().equals("Move back")) {
                    sprite = new Sprite(new Texture(Gdx.files.internal("textures/Programming cards/moveBack.png")));
                    batch.draw(sprite, 768 + i * 100, 268 - j * 100, 100, 100);
                }
                x++;
                //stop after 5 cards
                if(x==5) {
                    break;
                }
            }
        }

    }

}
