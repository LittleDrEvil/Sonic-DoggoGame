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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;



public class ScrPlay implements Screen {

    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    TbMenu tbMenu, tbGameover;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    CharClass charSonic = new CharClass();
    Sprite sprSonic;
    Animation[] aniChar;
    private float elapsedTime = 0;
    Texture imgFloor, imgBack, imgBlock,imgGif;
    int nJum, nDir = 0, nAniCurr, backX;
    float fSY, fSX, fBX = 50, fBY = 30, fX, fY, tempX;
    double dGravity, dSpeed;
    Vector2 vSonic;
    OrthographicCamera camera;

    public ScrPlay(GdxMenu _gdxMenu) {  //Referencing the main class.
        gdxMenu = _gdxMenu;
    }

    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.zoom = 21;
        camera.update();
        batch = new SpriteBatch();
         //imgGif = new Texture(Gdx.files.internal("200w.gif")); dosn't
        imgBack = new Texture(Gdx.files.internal("back.jpg"));
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


    }

    public void render(float delta) {
        charSonic.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        tempX = charSonic.x;

        elapsedTime += Gdx.graphics.getDeltaTime();
        
        // batch.draw(imgGif, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        batch.draw(imgBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, 0 + Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, 0 - Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.draw(imgFloor, backX, 0, Gdx.graphics.getWidth() - 30, 40);
        batch.draw(imgFloor, backX + Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth() - 30, 40);
        batch.draw(imgFloor, backX - Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth() - 30, 40);

        batch.draw(imgBlock, fBX, fBY, 30, 30);
        nDir = charSonic.Direction();
        batch.draw(charSonic.aniSonic[nDir].getKeyFrame(elapsedTime, true), charSonic.x, charSonic.y);


        //camera.setPosition(charSonic.x, charSonic.y);

        System.out.println("camera zoom "+ camera.zoom);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-2, 0);
            camera.update();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(2, 0);
            camera.update();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            camera.rotate(-2);
            camera.update();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.2;
            camera.update();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.2;
             camera.update();
        }
        
         if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        //camera.lookAt(fX, fY,0);
       
         }
        
 camera.zoom = MathUtils.clamp(camera.zoom, 0.01f, 21f);

      //  float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
       // float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        batch.end();
//    
//  if ( tempX <= 50) {
//            backX ++;
//            fBX ++;
//        }
//  if ( tempX >= 400) {
//            backX --;
//            fBX --;
//        }

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
        camera.viewportWidth = 30;
        camera.viewportHeight = 30 * height / width;
        camera.update();
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
}