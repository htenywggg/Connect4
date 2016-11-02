
public class Connect4Board {
	Connect4Piece board[][];
	int winLength;
	Connect4Presenter presenter;
	
	Connect4Board(int boardSize, int numToWin){
		winLength = numToWin;
		board = new Connect4Piece[boardSize][boardSize];
		
	}
	
	public int addToColumn(int columnNumber, boolean isPlay1){
		for(int i=0; i<board.length;i++){
			if(board[i][columnNumber]==null){
				board[i][columnNumber] = new Connect4Piece(i, columnNumber, isPlay1);
				hasWinner();
				return board.length- i;
			}
		}
		System.out.println("nopee");
		return -1;
		
	}
	
	public int hasWinner(){
		for(int i =0; i<board.length; i++){
			for(int j =0; j<board.length; j++){
				if(board[i][j]!= null){
					int lenghth = checkPiece(i,j,board[i][j].isP1());
					
				}
				
			}
		}
		return 0;
	}

	private int checkPiece(int i, int j, boolean player) {
		if(i<board.length&& j<board.length && i>=0 &&j>=0){
			if( checkRow(i,j,player)>=winLength){
				presenter.winner(0, i, j, player);
			}
			if( checkCol(i,j,player)>=winLength){
				presenter.winner(1, i, j, player);

			}
			if( checkUpRight(i,j,player)>=winLength){
				presenter.winner(2, i, j, player);

			}
			if( checkUpLeft(i,j,player)>=winLength){
				presenter.winner(3, i, j, player);

			}
		}
		return 0;
		
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
