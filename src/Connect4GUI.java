import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Connect4GUI extends Application {
   private static int boardSize;
   private static int numToWin;
   private int bufferHeight = 100;
   private int boardImageWidth = 100;
   private int chipImageWidth = 60;
   private int gridSize;
   private int sceneWidth;
   private int sceneHeight;
   private AnimationTimer timer;
   private ArrayList<GUIGamePiece> gamePieces;
   private ArrayList<GUIGameGrid> gameGrid;
   private Pane dynamicMembers;
   private Connect4Presenter presenter;
   private boolean isPlayer1 = true;
   private boolean animating = false;
   private boolean wasAnimating = false;
   private boolean gameOver = false;
   private StackPane winPane;
   
   /**
   Defines the Connect4Board and Connect4Presenter with the proper size and the
   winning sequence condition. Attaches presenter to both the board and to the
   GUI and vice versa. Also creates an ArrayList of the GUI game pieces and 
   establishes scene height and width.
   */
   public void init()
   {
      gridSize = boardSize;
		
      Connect4Board board = new Connect4Board(boardSize, numToWin);
      Connect4Presenter present = new Connect4Presenter(boardSize, numToWin);
		
      board.attach(present);
      attachPresenter(present);
	
      present.attachBoard(board);
      present.attachGUI(this);
      
      gamePieces = new ArrayList<GUIGamePiece>();
      sceneHeight = boardImageWidth * gridSize + bufferHeight;
      sceneWidth = boardImageWidth * boardSize;	
   }

   /**
    Attaches the appropriate presenter to the GUI.
    
    @param _presenter Presenter to be attached to the GUI.
    */
   public void attachPresenter(Connect4Presenter _presenter)
   {
      presenter = _presenter;	
   }
   
   /**
    
    @param i
    @param j
    @param _isPlayer1 
    */
   public void Win(int i, int j, boolean _isPlayer1)
   {
      gameOver = true;
      isPlayer1 = _isPlayer1;
   }
	
   /**
    Sets the stage (or the GUI), drawing the appropriate size board (which is 
    given by the user as an argument).
    @param stage Stage to be set for the GUI
    */
   public void start(Stage stage)
   {     
      BorderPane root = new BorderPane();
      
      HBox hbox = new HBox();
      Button resetButton = new Button("Reset");
      
      resetButton.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e)
         {
            resetBoard();
         }
      });
      
      //hbox.getChildren().addAll(resetButton);
      //root.setTop(hbox);
      
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
	
   /**
   Adds a new game piece to the board in the GUI, and switches to the next
   player's turn. 
    */
   private void CreateNewGamePiece()
   {		
      gamePieces.add(new GUIGamePiece(isPlayer1, 0, 0));
      dynamicMembers.getChildren().add(gamePieces.get(gamePieces.size() - 1));
      isPlayer1 = !isPlayer1;	
   }
   
   /**
    Draws the appropriate size board onto the window.
    @param staticMembers Pane to be drawn on
    */
   private void drawBoard(Pane staticMembers) 
   {
      gameGrid = new ArrayList<GUIGameGrid>();
      for (int i = 0; i < gridSize; i++)
      {
         for(int j = 0; j < gridSize; j++)
         {
            gameGrid.add(new GUIGameGrid(i * boardImageWidth, j * boardImageWidth + bufferHeight));
         }
      }
      staticMembers.getChildren().addAll(gameGrid);	
   }
	
   private void resetBoard()
   {
      for(int i = 0; i < gamePieces.size(); i++)
      {
         //gamePieces.get(i).MoveToLocation(gamePieces.get(i).moveToX, boardSize*100+8);
      }
   }
   
   /**
    
    */
   public void runGame()
   {
      timer = new AnimationTimer() {
	 @Override
	 public void handle(long now) 
         {            	
	    if(gamePieces.size() > 0)
            {
	       animating = gamePieces.get(gamePieces.size() - 1).draw();
	       if(!animating && wasAnimating)
               {
	          if(gameOver)
                  {
	             Group gr = new Group();    
	             Text text = new Text("Game Over!");  
                     text.setFill(Color.web("fabbff"));
                     text.setScaleX(3);
	             text.setScaleY(3);
	             text.setTranslateX(sceneWidth / 2);
	             gr.getChildren().add(text);
	             winPane.getChildren().addAll(gr);		
	          }
                  else
                  {
                     CreateNewGamePiece();
	          }
	       }
	          wasAnimating = animating;
	    }
         }
      };
      timer.start();
   }
   
   /**
    
    @param xLocation 
    */
   public void handleClick(int xLocation)
   {
      if(!animating && !gameOver)
      {
         int newY = presenter.rowSelection(xLocation, isPlayer1) + 8;
         if(newY >= 0)
         {
            int newX = xLocation - xLocation % boardImageWidth + 8;
            gamePieces.get(gamePieces.size() - 1).MoveToLocation(newX, newY);
            animating = true;
         }
      }	
   }
	
   /**
    
    @param args 
    */
   void launchApp(String[] args)
   {
      Application.launch(args);
   }
   
   public static void main(String[] args)
   {
      boardSize = 6;
      numToWin = 4;
      if(args.length == 2)
      {
         boardSize = Integer.parseInt(args[0]);
         numToWin = Integer.parseInt(args[1]);
      }	
      Application.launch(args);
   }	
}
