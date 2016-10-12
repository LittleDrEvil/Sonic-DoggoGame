/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KarnMenuTest.Screens;

import com.badlogic.gdx.graphics.Texture;

/*
 */
public class BlockClass {
    Texture tBlock;
    float fX, fY;
    
    public void BlockClass(Texture _tBlock, float _fX, float _fY){
        tBlock = _tBlock;
        fX= _fX;
        fY = _fY;
    }
   
}
