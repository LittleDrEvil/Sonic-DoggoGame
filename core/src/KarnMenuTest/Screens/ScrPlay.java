package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import KarnMenuTest.GdxMenu;
import KarnMenuTest.TbMenu;
import KarnMenuTest.TbsMenu;
import KarnMenuTest.Screens.BlockClass;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * Created by Luke on 2016-04-05.
 */
public class ScrPlay implements Screen, InputProcessor {
    Vector2 vBlo;
    Vector2[] avB;
    BlockClass[] bBlock;
    ArrayList <BlockClass> alBlocks; 
    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    TbMenu tbMenu, tbGameover;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    CharClass charSonic = new CharClass();
    Animation[] aniChar;
    private float elapsedTime = 0;
    Texture imgFloor, imgBack, imgBlock;
    int nJum, nDir = 0, nAniCurr;
    float fSY, fSX, fBX=50, fBY=50, fX, fY, fBackX, fDist;
    double dGravity, dSpeed;
    Vector2 vSonic;
    boolean bPass=false;
    int nBlockSize=400;
    
    public ScrPlay(GdxMenu _gdxMenu) { 
//Referencing the main class.
        gdxMenu = _gdxMenu;
    }

    public void show() {
        alBlocks = new ArrayList<BlockClass>();
        bBlock = new BlockClass[nBlockSize];
        avB = new Vector2[nBlockSize];
        batch = new SpriteBatch();
        imgBack = new Texture(Gdx.files.internal("background.png"));
        imgFloor = new Texture(Gdx.files.internal("background1.png"));
        imgBlock = new Texture(Gdx.files.internal("block.png"));
        charSonic.charMain("Sonic");
        stage = new Stage();
        tbsMenu = new TbsMenu();
        tbMenu = new TbMenu("BACK", tbsMenu);
        tbGameover = new TbMenu("GAMEOVER", tbsMenu);
        tbMenu.setY(0);
        tbMenu.setX(0);
        tbGameover.setY(0);
        tbGameover.setX(440);
        stage.addActor(tbMenu);
        stage.addActor(tbGameover);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        btnGameoverListener();
        vBlo = new Vector2();
        
        
//        for (int i = 0; i < nBlockSize/4; i++) {
//            
//            avB[i] = new Vector2();
//            bBlock[i] = new BlockClass();
//            
//            vBlo.add(30*(i), 50);
//            avB[i].add(vBlo.x, vBlo.y);
//            bBlock[i].BlockClass();
//            vBlo.add(-vBlo.x, -vBlo.y);
//            alBlocks.add(bBlock[i]);
//        }
        for (int i = 0; i < nBlockSize; i++) {
            
            avB[i] = new Vector2();
            bBlock[i] = new BlockClass();
            
            vBlo.add(60*(i), 50);
            avB[i].add(vBlo.x, vBlo.y);
            bBlock[i].BlockClass();
            vBlo.add(-vBlo.x, -vBlo.y);
            alBlocks.add(bBlock[i]);
        }
//        for (int i = nBlockSize/2; i < nBlockSize; i++) {
//            avB[i] = new Vector2();
//            bBlock[i] = new BlockClass();
//            
//            vBlo.add(30*(i-nBlockSize/2), 200);
//            
//            avB[i].add(vBlo.x, vBlo.y);
//            bBlock[i].BlockClass();
//            vBlo.add(-vBlo.x, -vBlo.y);
//            alBlocks.add(bBlock[i]);
//        }
    }

    @Override
    public void render(float delta) {
        
        charSonic.update();
        for (int i = 0; i < nBlockSize; i++) {
            charSonic = bBlock[i].update(charSonic,avB[i], fDist);
        }
        batch.begin();
        nDir = charSonic.Direction();
        elapsedTime += Gdx.graphics.getDeltaTime();
        if((fBackX < -Gdx.graphics.getWidth() || fBackX > Gdx.graphics.getWidth())){
            fBackX=0;
        }
        
        batch.draw(imgBack, fBackX, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, fBackX-Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, fBackX+Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgFloor, fBackX, 0, Gdx.graphics.getWidth(), 40);
        batch.draw(imgFloor, fBackX-Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), 40);
        batch.draw(imgFloor, fBackX+Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), 40);
        batch.draw(charSonic.aniChar[nDir].getKeyFrame(elapsedTime, true), charSonic.vChar.x, charSonic.vChar.y);
        for (int i = 0; i < nBlockSize; i++) {
            batch.draw(imgBlock, avB[i].x - fDist, avB[i].y, 30, 30);
//            System.out.println(avB[i].toString());
        }
        batch.end();
        
        //System.out.println(fDist);
        
        if(fDist > 0) {
            fBackX -= charSonic.fSx;
        } else if (fDist<0) {
            charSonic.fSx = 0;
            fDist = 0;
        }
        fDist += charSonic.fSx;
        
        if (charSonic.vChar.x < 0 && fDist <= 125) {
            charSonic.vChar.x += charSonic.fSx;
            charSonic.vChar.x = 1;
        } else if (charSonic.vChar.x < 125 && fDist > 125){
            charSonic.vChar.x += charSonic.fSx;
            charSonic.vChar.x = 126;
        }
            
        
    }

    public void btnGameoverListener() {
        tbGameover.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gdxMenu.currentState = gdxMenu.gameState.OVER;
                gdxMenu.updateState();
            }
        });
    }

    public void btnMenuListener() {
        tbMenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gdxMenu.currentState = gdxMenu.gameState.MENU;
                gdxMenu.updateState();
            }
        });
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}