public class Connect4Board {
   private Connect4Piece board[][];
   private int winLength;
   private Connect4Presenter presenter;
   
   /**
   Constructor for Connect4Board: creates an array of Connect4Piece of the given
   parameter size and sets the number of pieces in a sequence needed to win, 
   constructs a Connect4Board object
   
   @param boardSize size of board to be created
   
   @param numToWin number of pieces in a sequence necessary to win
   */
   Connect4Board(int boardSize, int numToWin)
   {
      winLength = numToWin;
      board = new Connect4Piece[boardSize][boardSize];	
   }

   /**
   Adds a Connect4Piece to the board in a certain column by iterating through 
   the board array in a certain column to check to see if the position is empty.
   If there is no empty position, it will return -1.
   
   @param columnNumber the column to put a new Connect4Piece in
   
   @param isPlay1 true if the piece is for player 1, false if for player 2
   
   @return position of the new Connect4Piece from the top (as opposed to the bottom) *************comeback
   */
   public int addToColumn(int columnNumber, boolean isPlay1)
   {
      for(int i = 0; i < board.length; i++)
      {
         if(board[i][columnNumber] == null)
         {
            board[i][columnNumber] = new Connect4Piece(i, columnNumber, isPlay1);
            hasWinner();
            return board.length - i;
         }
      }
      System.out.println("nopee");
      return -1;	
   }

   /**
   
   */
   public void hasWinner()
   {
      for(int i = 0; i < board.length; i++)
      {
         for(int j = 0; j < board.length; j++)
         {
            if(board[i][j] != null)
            {
               checkPiece(i, j, board[i][j].isP1());
            }			
         }
      }
   }

   private void checkPiece(int i, int j, boolean player) 
   {
      if(i<board.length&& j<board.length && i>=0 &&j>=0)
      {
         if(checkRow(i,j,player)>=winLength)
         {
            presenter.winner(0, i, j, player);
         }
         if( checkCol(i,j,player)>=winLength)
         {
            presenter.winner(1, i, j, player);
         }
         if( checkUpRight(i,j,player)>=winLength)
         {
            presenter.winner(2, i, j, player);
         }
         if( checkUpLeft(i,j,player)>=winLength){
            presenter.winner(3, i, j, player);

			}
		}		
	}

	private int checkRow(int i, int j, boolean player) {
		if(i<board.length&& j<board.length&&i>=0 &&j>=0){
			if(board[i][j]!=null && (board[i][j].isP1() ==player)){
				return checkRow(i,j+1,player)+1;
			}
		}
		return 0;
		
	}
	private int checkCol(int i, int j, boolean player) {
		if(i<board.length&& j<board.length&& i>=0 &&j>=0){
			if(board[i][j]!=null&& board[i][j].isP1()==player){
				return checkCol(i+1,j,player)+1;
			}
		}
		return 0;
		
	}
	private int checkUpRight(int i, int j, boolean player){
		if(i<board.length&& j<board.length&&i>=0 &&j>=0){
			if(board[i][j]!=null&& board[i][j].isP1()==player){
				return checkUpRight(i+1,j+1,player)+1;
			}
		}
		return 0;
	}
	private int checkUpLeft(int i, int j, boolean player){
		if(i<board.length&& j<board.length&& i>=0 &&j>=0){
			if(board[i][j]!=null&& board[i][j].isP1()==player){
				return checkUpLeft(i-1,j+1,player)+1;
			}
		}
		return 0;
	}

	public void attach(Connect4Presenter present) {
		presenter = present;
		
	}
	
}
