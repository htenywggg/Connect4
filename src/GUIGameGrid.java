import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIGameGrid extends ImageView {
	
   /**
    Constructor for GUIGameGrid.
    
    @param x x-position of image
    @param y y-position of image
    */
   public GUIGameGrid(int x, int y)
   {
      super("Connect4Board.png");
      this.setX(x);
      this.setY(y);	
   }
}