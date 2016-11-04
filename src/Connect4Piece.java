public class Connect4Piece {
   private boolean isP1;
   private int i, j;
   
   /**
    Constructor for Connect4Piece, setting the position of the piece and storing
    which players piece it is.
    @param _i row position of the piece
    
    @param _j column position of the piece
    
    @param _isP1 true if player 1, false if player 2
    */
   public Connect4Piece(int _i, int _j, boolean _isP1)
   {
      i = _i;
      j = _j;
      isP1 = _isP1;
   }
	
   /**
    Checks which player's piece it is
    
    @return true if player 1
    */
   public boolean isP1()
   {
      return isP1;
   }
	
   /**
    Gets the row position of the piece
    
    @return row position of the piece
    */
   public int getI()
   {
      return i;
   }
	
   /**
    Gets the column position of the piece
    
    @return column position of the piece
    */
   public int getJ()
   {
      return j;
   }
}
