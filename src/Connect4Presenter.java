
public class Connect4Presenter {
	Connect4GUI GUI;
	Connect4Board board;
	int ImageWidth = 100;
	int boardSize;
	Connect4Presenter(int _boardSize, int numToWin){
		boardSize = _boardSize;
	}
	//this is the view
	public void attachGUI(Connect4GUI _GUI){
		GUI = _GUI;
	}
	
	//This is the model
	public void attachBoard(Connect4Board _board){
		board = _board;
	}
	
	public int rowSelection(int xPos, boolean isPlayer1){
		return board.addToColumn((xPos/ImageWidth), isPlayer1)*100;
	}
	public void winner(int type, int i, int j, boolean isPlayer1){
		
		GUI.Win(i, j, isPlayer1);
		/*type to be implemented later, basically if we want we can cross through the pieces that are connected to show the connection
		 if(type==0){
			
		}else if(type==1){
			
		}else if(type ==2){
			
		}else if(type == 3){
			
		}*/
	}
}

