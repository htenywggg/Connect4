import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIGamePiece extends ImageView {
   private int moveToX;
   private int moveToY;
   private int speed = 4;
   private int gravity = 3;
   private int yVel;
   private boolean isBlue;
	
   /**
    Constructor for GUIGamePiece, creates a game piece with the appropriate 
    color and position on the board.
    @param _isBlue true if blue piece, false if green piece
    @param x x-position of piece
    @param y y-position of piece
    */
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
	
   /**
    Checks if the piece is 
    * @return 
    */
   public boolean getIsAnimating()
   {
      return yVel == 0;
   }

   public void MoveToLocation(int x, int y)
   {
      System.out.println(x + " " + y);
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
