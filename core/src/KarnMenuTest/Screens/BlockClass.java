/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KarnMenuTest.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BlockClass extends Sprite  {
    Texture tBlock;
    float fX, fY;
    Vector2 vB;
    CharClass charChar;
    SpriteBatch blockbatch;
    public void BlockClass(Texture _tBlock, Vector2 _vB){
        tBlock = _tBlock;
        vB = _vB;
    }
   
    CharClass update(CharClass chara, Vector2 vBlock, float fDist){
        
        System.out.println(chara.fDy);
        
        if(isHitBlockT(chara.vChar.x, chara.vChar.y , 30 ,vBlock.x - fDist, vBlock.y , 30)){
            
            chara.dSpeed = 0;
            chara.nJum = 0;
            chara.vChar.y = chara.fSY;
            chara.dGravity = 0.00;
            System.out.println("top");
            return chara;
            
        } else chara.dGravity = -0.01;
        
        if(isHitBlockLR(chara.vChar.x, chara.vChar.y, 30, vBlock.x - fDist, vBlock.y, 30)){
            chara.dSpeed = 0;
            chara.nJum = 0;
            chara.vChar.x = chara.fSX;
            if(chara.vChar.x < vBlock.x - fDist)chara.vChar.x = chara.fSX-3;
            
            if(chara.vChar.x > vBlock.x - fDist)chara.vChar.x = chara.fSX+3;
            
            chara.fSx = 0;
            chara.dGravity = 0;
            System.out.println("side");
            return chara;
        } 
        
        return chara;
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
    
    boolean isHitBlockTV(Vector2 v1, float nS1, Vector2 v2, float nS2) {

        if ((((v1.x <= v2.x+1) && (v1.x + nS1 >= v2.x-1))
                || ((v1.x >= v2.x-1) && (v1.x <= v2.x + nS2 + 1)))
                && ((v1.y >= v2.y + 5) && (v1.y <= v2.y + 5 + nS2))) {
            return true;
        }
        return false;
    }
}
