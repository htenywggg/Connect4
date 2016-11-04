
public class Connect4Presenter {
   private Connect4GUI GUI;
   private Connect4Board board;
   private int ImageWidth = 100;
   private int boardSize;
	
   /**
    Constructor for Connect4Presenter, sets the board size
    
    @param _boardSize size of the board
    @param numToWin number that is required for a win sequence
    */
   Connect4Presenter(int _boardSize, int numToWin)
   {
      boardSize = _boardSize;
   }

   /**
    Attaches GUI to the presenter
    
    @param _GUI GUI to be attached
    */
   public void attachGUI(Connect4GUI _GUI)
   {
      GUI = _GUI;
   }
	
   /**
    Attaches the model to the presenter
     
    @param _board model (or board) to be attached
    */
   public void attachBoard(Connect4Board _board)
   {
      board = _board;
   }
	
   /**
    
    @param xPos
    @param isPlayer1
    
    @return 
    */
   public int rowSelection(int xPos, boolean isPlayer1)
   {
      return board.addToColumn((xPos / ImageWidth), isPlayer1) * 100;
   }
	
   /**
    
    * @param type
    * @param i
    * @param j
    * @param isPlayer1 
    */
   public void winner(int type, int i, int j, boolean isPlayer1)
   {
      GUI.Win(i, j, isPlayer1);
      /*type to be implemented later, basically if we want we can cross through the pieces that are connected to show the connection
      if(type==0)
      {		
      }
      else if(type == 1)
      {			
      }
      else if(type == 2)
      {		
      }
      else if(type == 3)
      {		
      }*/
   }
}

