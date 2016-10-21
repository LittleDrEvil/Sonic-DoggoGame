package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;



public class CharClass {

    public Animation aniSonic[] = new Animation[6];
    TextureAtlas textureAtlas1;
    int nDir = 0, nJum;
    float x, y = 100, fDy, fSY, fSX, fBX = 50, fBY = 30, fSx;
    double dSpeed, dGravity;
//pass a string from entry
//For picking character
//textureAtlas1 = new TextureAtlas(Gdx.files.internal("(String)" + "StillRight.pack"));
    public void charMain(String sCharacter) {
        dGravity = -0.01;
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "StillRight.pack"));
        aniSonic[0] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "StillLeft.pack"));
        aniSonic[1] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "RunLeft.pack"));
        aniSonic[2] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "RunRight.pack"));
        aniSonic[3] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "JumpLeft.pack"));
        aniSonic[4] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "JumpRight.pack"));
        aniSonic[5] = new Animation(1 / 15f, textureAtlas1.getRegions());
    }

    public void update() {
        fSY = y;
        fSX = x;
        dSpeed += dGravity;
        fDy += dSpeed;
        y += fDy;
        System.out.println(nDir);
        
       // x += fSx;
//        if(fSx>0) fSx-=0.1;
//        if(fSx<0) fSx+=0.1;
        
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                x+=3;
//                x+=fSx;
            }
             if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                x-=3;
//                x-=fSx;
            }
             System.out.println(fDy);
            if(fDy<0)
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                fDy = 4;
            }
            
            

        if (isHit(x, y, 30, 40, 0, 0, 90000, 30)) {
            dSpeed = 0;
            nJum = 0;
            y = fSY;
            dGravity = 0;
            
        } else dGravity = -0.01;
        if (isHitBlock(x, y, 30, fBX, fBY, 30)) {
            dSpeed = 0;
            nJum = 0;
            y = fSY;
        }
    }

    int Direction() {
        if (nDir == 2) {
            nDir = 1;
        }
        if (nDir == 3) {
            nDir = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            nDir = 2;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            nDir = 3;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                nJum = 1;
            }
        if (nJum == 0) {
            return nDir;
        }

        if (nJum == 1) {
            if (nDir == 2 || nDir == 1) {
                return 4;
            }
            if (nDir == 3 || nDir == 0) {
                return 5;
            }
        }
        return nDir;
    }

    boolean isHitBlock(float nX1, float nY1, float nS1, float nX2, float nY2, float nS2) {

        if ((((nX1 <= nX2) && (nX1 + nS1 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nS2)))
                && (((nY1 <= nY2) && (nY1 + nS1 >= nY2))
                || ((nY1 >= nY2) && (nY1 <= nY2 + nS2)))) {
            return (true);
        } else {
            return (false);
        }
    }

    boolean isHit(float nX1, float nY1, float nW1, float nH1, float nX2, float nY2, float nW2, float nH2) {

        if ((((nX1 <= nX2) && (nX1 + nW1 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nW2)))
                && (((nY1 <= nY2) && (nY1 + nH1 >= nY2))
                || ((nY1 >= nY2) && (nY1 <= nY2 + nH2)))) {
            return (true);
        } else {
            return (false);
        }
    }
}