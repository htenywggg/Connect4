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
   
   public int getBoardSize()
   {
      return board.length;
   }

   /**
   Adds a Connect4Piece to the board in a certain column by iterating through 
   the board array in a certain column to check to see if the position is empty.
   If there is no empty position, it will return -1.
   
   @param columnNumber the column to put a new Connect4Piece in
   
   @param isPlay1 true if the piece is for player 1, false if for player 2
   
   @return position of the new Connect4Piece
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
   Calls checkPiece() for each non-empty position on the board, determining 
   whether or not there is a winning sequence.
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

   /**
   Checks to see if there is a winning sequence (either by row, by column, or
   by diagonal. If there is a winning sequence, it will invoke the winner() in
   presenter.
   
   @param i row position of the board
   
   @param j column position of the board
   
   @param player true if player 1, false if player 2
   */
   private void checkPiece(int i, int j, boolean player) 
   {
      if(i < board.length && j < board.length && i >= 0 && j >= 0)
      {
         if(checkRow(i, j, player) >= winLength)
         {
            presenter.winner(0, i, j, player);
         }
         else if(checkCol(i, j, player) >= winLength)
         {
            presenter.winner(1, i, j, player);
         }
         else if(checkUpRight(i, j, player) >= winLength)
         {
            presenter.winner(2, i, j, player);
         }
         else if(checkUpLeft(i, j, player) >= winLength)
         {
            presenter.winner(3, i, j, player);

         }
      }		
   }

   /**
   By using recursion, it calculates the number of one player's pieces in a 
   given row i, and returns it.
   
   @param i row position of the board
   
   @param j column position of the board
   
   @param player true if player 1, false if player 2
   
   @return number of the given player's pieces that are in a row sequence
   */
   private int checkRow(int i, int j, boolean player) 
   {
      if(i < board.length && j < board.length && i >= 0 && j >= 0)
      {
         if(board[i][j] != null && (board[i][j].isP1() == player))
         {
            return checkRow(i, j + 1, player) + 1;
         }
      }
      return 0;	
   }

   /**
   By using recursion, it calculates the number of one player's pieces in a 
   given column j, and returns it.
   
   @param i row position of the board
   
   @param j column position of the board
   
   @param player true if player 1, false if player 2
   
   @return number of the given player's pieces that are in a column sequence
   */
   private int checkCol(int i, int j, boolean player) 
   {
      if(i < board.length && j < board.length && i >= 0 && j >= 0)
      {
         if(board[i][j] != null && board[i][j].isP1() == player)
         {
            return checkCol(i + 1, j, player) + 1;
         }
      }
      return 0;		
   }

   /**
   By using recursion, it calculates the number of one player's pieces in a 
   right diagonal, and returns it.
   
   @param i row position of the board
   
   @param j column position of the board
   
   @param player true if player 1, false if player 2
   
   @return number of the given player's pieces that are in a right diagonal 
   sequence
   */
   private int checkUpRight(int i, int j, boolean player)
   {
      if(i < board.length && j < board.length && i >= 0 && j >= 0)
      {
         if(board[i][j] != null && board[i][j].isP1() == player)
         {
            return checkUpRight(i + 1, j + 1, player) + 1;
         }
      }
      return 0;
   }
   
   /**
   By using recursion, it calculates the number of one player's pieces in a 
   left diagonal, and returns it.
   
   @param i row position of the board
   
   @param j column position of the board
   
   @param player true if player 1, false if player 2
   
   @return number of the given player's pieces that are in a left diagonal
   sequence
   */
   private int checkUpLeft(int i, int j, boolean player)
   {
      if(i < board.length && j < board.length && i >= 0 && j >= 0)
      {
         if(board[i][j] != null && board[i][j].isP1() == player)
         {
            return checkUpLeft(i - 1, j + 1, player) + 1;
         }
      }
      return 0;
   }

   /**
    Attaches the presenter to this model
    
    @param present presenter to be attached
    */
   public void attach(Connect4Presenter present) 
   {
      presenter = present;
   }
	
}
