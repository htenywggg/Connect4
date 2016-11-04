import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIGameGrid extends ImageView {
	
   public GUIGameGrid(int x, int y)
   {
      super("Connect4Board.png");
      this.setX(x);
      this.setY(y);	
   }
}