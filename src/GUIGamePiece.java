import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIGamePiece extends ImageView {
   int moveToX;
   int moveToY;
   int speed = 4;
   int gravity = 3;
   int yVel;
   boolean isBlue;
	
   public GUIGamePiece(boolean _isBlue, int x, int y)
   {
      super();
      isBlue = _isBlue;
      if(isBlue)
      {
         this.setImage(new Image("Connect4BluePiece.png"));
      }
      else
      {
         this.setImage(new Image("Connect4GreenPiece.png"));
      }
      moveToX = x;
      moveToY = y;
      this.setX(x);
      this.setY(y);	
   }
	
   boolean getIsAnimating()
   {
      return yVel==0;
   }

   public void MoveToLocation(int x, int y)
   {
      yVel = gravity;
      moveToX = x;
      moveToY = y;
      if(isBlue)
      {
         moveToY += 1;
      }
   }
	
   public boolean draw()
   {
      //this method takes care of the animation that moves the piece to the right then drops it
      boolean anim = false;
      if(this.getX() < moveToX)
      {
         this.setX(this.getX() + speed);			
         anim = true;
      }
      else if(Math.abs(this.getY() - moveToY) > 2 ||  Math.abs(yVel) > 1) 
      {
         if(this.getY() < moveToY)
         {
            yVel += gravity;
            this.setY(this.getY() + yVel);
            anim  = true;
         }
         else
         {
            //yVel+=gravity;
            yVel *= -.7;
            this.setY(moveToY + yVel);
            anim = true;
         }
      }
      return anim;
   }
}
