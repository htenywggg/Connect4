public class Connect4Piece {
   boolean isP1;
   int i, j;
   
   public Connect4Piece(int _i, int _j, boolean _isP1)
   {
      i = _i;
      j = _j;
      isP1 = _isP1;
   }
	
   public boolean isP1()
   {
      return isP1;
   }
	
   public int getI()
   {
      return i;
   }
	
   public int getJ()
   {
      return j;
   }
}
