package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CharClass {

    Vector2 vChar = new Vector2();
    Vector2 vFloor = new Vector2();
    Sprite sprChar;
    public Animation aniChar[] = new Animation[6];
    TextureAtlas textureAtlas1;
    int nDir = 0, nJum;
    float x, y = 100, fDy, fSY, fSX, fBX = 50, fBY = 50, fSx;
    double dSpeed, dGravity;

    public void charMain(String sCharacter) {
        vFloor.nor();
        vChar.add(x, y);
        dGravity = -0.01;
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "StillRight.pack"));
        aniChar[0] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "StillLeft.pack"));
        aniChar[1] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "RunLeft.pack"));
        aniChar[2] = new Animation(1 / 30f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "RunRight.pack"));
        aniChar[3] = new Animation(1 / 30f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "JumpLeft.pack"));
        aniChar[4] = new Animation(1 / 15f, textureAtlas1.getRegions());
        textureAtlas1 = new TextureAtlas(Gdx.files.internal(sCharacter + "JumpRight.pack"));
        aniChar[5] = new Animation(1 / 15f, textureAtlas1.getRegions());
    }

    public void update() {
        //Gravity and Movement {
        if (fSx < 0.01 && fSx > -0.01) {
            fSx = 0;
        }
        fSY = vChar.y;
        fSX = vChar.x;
        dSpeed += dGravity;
        fDy += dSpeed;
        vChar.add(fSx, fDy);
        
        if (fSx > 0) {
            fSx -= 0.1;
        }
        if (fSx < 0) {
            fSx += 0.1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            fSx += 0.2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            fSx -= 0.2;
        }

        if (nJum == 0) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                fDy = 4;
                nJum = 1;
            }
        }
        // }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            fSx = 199;
        }
        //Hit Testing {
//        if (isHit(vChar.x, vChar.y, 30, 40, 0, 0, Gdx.graphics.getWidth(), 30)) {
//            dSpeed = 0;
//            nJum = 0;
//            vChar.y = fSY;
//            dGravity = 0;
//        } else {
//            dGravity = -0.01;
//        }
        if (isHitV(vChar, 30, 40, vFloor, Gdx.graphics.getWidth(), 30)) {
            dSpeed = 0;
            nJum = 0;
            vChar.y = fSY;
            dGravity = 0;
        } else {
            dGravity = -0.01;
        }
//        if (isHitBlockT(vChar.x, vChar.y, 30, fBX-=fSx, fBY, 30)) {
//            dSpeed = 0;
//            nJum = 0;
//            vChar.y = fSY;
//           // System.out.println("top ");
//        }
//        if (isHitBlockLR(vChar.x, vChar.y, 30, fBX-=fSx, fBY, 30)) {
//            dSpeed = 0;
//            nJum = 0;
//            vChar.x = fSX;
//            fSx = 0;
//            dGravity = 0;
//            System.out.println("side");
//        }
        if (vChar.x > Gdx.graphics.getWidth()-150) {
            vChar.x -= fSx;
            vChar.x =  Gdx.graphics.getWidth()-149;
        }
        
        // }
    }

    Animation aniSpr(Animation aniSon) {


        return aniSon;
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

    boolean isHit(float nX1, float nY1, float nW1, float nH1, float nX2, float nY2, float nW2, float nH2) {

        if ((((nX1 <= nX2) && (nX1 + nW1 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nW2)))
                && (((nY1 <= nY2) && (nY1 + nH1 >= nY2))
                || ((nY1 >= nY2) && (nY1 <= nY2 + nH2)))) {
            return true;
        } else {
            return (false);
        }
    }
   
    boolean isHitV(Vector2 v1, float nW1, float nH1, Vector2 v2, float nW2, float nH2) {

        if ((((v1.x <= v2.x) && (v1.x + nW1 >= v2.x))
                || ((v1.x >= v2.x) && (v1.x <= v2.x + nW2)))
                && (((v1.y <= v2.y) && (v1.y + nH1 >= v2.y))
                || ((v1.y >= v2.y) && (v1.y <= v2.y + nH2)))) {
            return true;
        } else {
            return (false);
        }
    }
    

    boolean isHitBlockLR(float nX1, float nY1, float nS1, float nX2, float nY2, float nS2) {

        if ((((nX1 <= nX2) && (nX1 + nS1 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nS2)))
                && (((nY1 <= nY2 - 3) && (nY1 + nS1 >= nY2 - 3))
                || ((nY1 >= nY2 - 3) && (nY1 <= nY2 + nS2 - 3)))) {
            return true;
        }
        return false;



    }

    boolean isHitBlockT(float nX1, float nY1, float nS1, float nX2, float nY2, float nS2) {

        if ((((nX1 <= nX2+1) && (nX1 + nS1 >= nX2-1))
                || ((nX1 >= nX2-1) && (nX1 <= nX2 + nS2 + 1)))
                && ((nY1 >= nY2 + 5) && (nY1 <= nY2 + 5 + nS2))) {
            return true;
        }
        return false;
    }
}