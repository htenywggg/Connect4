
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Connect4GUI extends Application {

	private static int boardSize;
	private static int numToWin;
	int bufferHeight = 100;
	int boardImageWidth = 100;
	int chipImageWidth = 60;
	int gridSize;
	int sceneWidth;
	int sceneHeight;
	AnimationTimer timer;
	ArrayList<GUIGamePiece> gamePieces;
	ArrayList<GUIGameGrid> gameGrid;
	Pane dynamicMembers;
	Connect4Presenter presenter;
	boolean isPlayer1 =true;
	boolean animating = false;
	boolean wasAnimating= false;
	boolean gameOver = false;
	StackPane winPane;
        
	public void init(){
		gridSize = boardSize;
		
		Connect4Board board = new Connect4Board(boardSize, numToWin);

		Connect4Presenter present  = new Connect4Presenter(boardSize, numToWin);
		
		board.attach(present);
		attachPresenter(present);
	
		present.attachBoard(board);
		present.attachGUI(this);
		
		gamePieces = new ArrayList<GUIGamePiece>();
		sceneHeight = boardImageWidth*gridSize+ bufferHeight;
		sceneWidth = boardImageWidth*boardSize;
		
	}
	void attachPresenter(Connect4Presenter _presenter){
		presenter = _presenter;
		
		
	}
	public void Win(int i, int j, boolean _isPlayer1){
		gameOver = true;
		isPlayer1 = _isPlayer1;
	}
	
	
	
	
	public void start(Stage stage){
        
		BorderPane root = new BorderPane();

		 // Where all they gamePieces go
        dynamicMembers = new Pane();
        
        // all the game board pieces go here
        Pane staticMembers = new Pane();
        drawBoard(staticMembers);
        Pane layerPane = new Pane();
        layerPane.setBackground(new Background(new BackgroundFill(Color.web("#90EE90"), CornerRadii.EMPTY, Insets.EMPTY)));

        //just to handle when a winner is chosen
         winPane = new StackPane();
        
        
        layerPane.setOnMouseReleased(e -> {
        	handleClick((int) e.getX());
        	
        });
        
        layerPane.getChildren().addAll(dynamicMembers);
        layerPane.getChildren().addAll(staticMembers);
        layerPane.setCenterShape(true);
        layerPane.getChildren().addAll(winPane);
        root.setCenter(layerPane);

        Scene scene = new Scene(root, sceneWidth, sceneHeight);

        stage.setScene(scene);
       
        CreateNewGamePiece();
        		
        stage.show();
       
        
        runGame();
		
	}
	
	
	private void CreateNewGamePiece(){
		
		gamePieces.add(new GUIGamePiece(isPlayer1,0, 0));
		 dynamicMembers.getChildren().add(gamePieces.get(gamePieces.size()-1));
		 isPlayer1 = !isPlayer1;
		
	}
	private void drawBoard(Pane staticMembers) {
		gameGrid = new ArrayList<GUIGameGrid>();
		for (int i = 0; i<gridSize; i++){
			for(int j = 0; j<gridSize; j++){
				gameGrid.add(new GUIGameGrid(i*boardImageWidth,j*boardImageWidth+bufferHeight));
			}
		}
		staticMembers.getChildren().addAll(gameGrid);
		
		
	}
	
	public void runGame(){
		  	timer = new AnimationTimer() {

	            @Override
	            public void handle(long now) {
	            	
	            	if(gamePieces.size()>0){
	            		animating = gamePieces.get(gamePieces.size()-1).draw();
	            		if(!animating&& wasAnimating){
	            			if(gameOver){
	            				Group gr = new Group();    
	            				Text text = new Text("Game Over!");  
	            				text.setFill(Color.web("fabbff"));
	            				text.setScaleX(3);
	            				text.setScaleY(3);
	            				text.setTranslateX(sceneWidth/2);
	            				gr.getChildren().add( text);
	            				winPane.getChildren().addAll(gr);
	            				
	            			}else{
	            				CreateNewGamePiece();
	            			}
	            		}
	            		wasAnimating = animating;
	            	}

	            }
	        };
	        timer.start();
	}
	
	public void handleClick(int xLocation){
		if(!animating && !gameOver){
			int newY =presenter.rowSelection(xLocation, isPlayer1)+8;
			if(newY >=0){
				int newX = xLocation - xLocation% boardImageWidth +8;
				gamePieces.get(gamePieces.size()-1).MoveToLocation(newX, newY);
				animating = true;
			}
		}
		
	}
	
	
	void launchApp(String[] args){
		Application.launch(args);
	}
	public static void main(String[] args){
		boardSize = 6;
		numToWin= 4;
		if(args.length==2){
			boardSize = Integer.parseInt(args[0]);
			numToWin= Integer.parseInt(args[1]);
		}
		
		Application.launch(args);
	}
	
	
}
