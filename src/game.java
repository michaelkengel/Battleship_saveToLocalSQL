import java.util.*;

public class game{ 
	
	// Present notes
	// Talk about SQL, wanted to learn
	// Display the game and play it
	// Show leaderboard saving, sorting
	// Show leaderboard class and functions
	// Talk about getting result set and iterating through it 
	
	
public static void main(String[] args) {
	int[][] board = new int[5][5];
	int[][] ships = new int[3][2];
	int[] shoot = new int[2];
	int attempts=0, shotHit=0;
	Scanner keyboard = new Scanner(System.in);
	leaderboard scores = new leaderboard();
	scores.infoBox();
	initBoard(board);
	initShips(ships);
	
	System.out.println("What is your name?");
	String name = keyboard.nextLine();
	System.out.println("Take your shot by typing the row then the column. Let's begin:");
	do{
		showBoard(board);
		shoot(shoot);
	    attempts++;       
	    if(hit(shoot,ships))
	    {
	    	hint(shoot,ships,attempts);
	                shotHit++;
	            }                
	    else
	      hint(shoot,ships,attempts);
	      changeboard(shoot,ships,board);
	 
	        }while(shotHit!=3);
	        
	        System.out.println("\n\n\nBattleship game finished! You hit 3 ships in "+attempts+" attempts, " + name );
	        showBoard(board);
	        scores.updateHighest(attempts, name);
	        scores.getHighest();
	        scores.showBoard();
	    }


public static void initBoard(int[][] board){
	        for(int row=0 ; row < 5 ; row++ )
	            for(int column=0 ; column < 5 ; column++ )
	                board[row][column]=-1;
	    }
	    
public static void showBoard(int[][] board){
			System.out.println("\n\n\n");
	        System.out.println("\t1 \t2 \t3 \t4 \t5");
	        System.out.println();
	        
	        for(int row=0 ; row < 5 ; row++ ){
	            System.out.print((row+1)+"");
	            for(int column=0 ; column < 5 ; column++ ){
	                if(board[row][column]==-1){
	                    System.out.print("\t"+"~");
	                }else if(board[row][column]==0){
	                    System.out.print("\t"+"*");
	                }else if(board[row][column]==1){
	                    System.out.print("\t"+"X");
	                } }
	            System.out.println();
	        }
	    }

public static void initShips(int[][] ships){
	        Random random = new Random();
	        
	        for(int ship=0 ; ship < 3 ; ship++){ // assign x, y to each ship (3)
	            ships[ship][0]=random.nextInt(5);
	            ships[ship][1]=random.nextInt(5);

	            for(int last=0 ; last < ship ; last++){ // loop checks if any ships are same x, y
	                if( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) )
	                    do{
	                        ships[ship][0]=random.nextInt(5);
	                        ships[ship][1]=random.nextInt(5);
	                    }while( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) );
	         }       
	       }
	    }

public static void shoot(int[] shoot){
	        Scanner input = new Scanner(System.in);
	        
	        System.out.print("Row: ");
	        shoot[0] = input.nextInt();
	        shoot[0]--; // Because stored as an array so val must be -1
	        
	        System.out.print("Column: ");
	        shoot[1] = input.nextInt();
	        shoot[1]--;        
	    }
	    
public static boolean hit(int[] shoot, int[][] ships){
	        
	        for(int ship=0 ; ship<ships.length ; ship++){
	            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
	                System.out.printf("You hit a ship located in (%d,%d)\n",shoot[0]+1,shoot[1]+1);
	                return true;
	            }
	        }
	        return false;
	    }

public static void hint(int[] shoot, int[][] ships, int attempt){
	        int row=0,
	            column=0;
	        
	        for(int x=0 ; x < ships.length ; x++){
	            if(ships[x][0]==shoot[0])
	                row++;
	            if(ships[x][1]==shoot[1])
	                column++;
	        }
	        
	        System.out.printf("\nHint %d: \nRow %d -> %d ships\n" +
	                                 "Column %d -> %d ships\n",attempt,shoot[0]+1,row,shoot[1]+1,column); /// %d takes the next integer and prints it, different style
	    }

public static void changeboard(int[] shoot, int[][] ships, int[][] board){
	        if(hit(shoot,ships))
	            board[shoot[0]][shoot[1]]=1;
	        else
	            board[shoot[0]][shoot[1]]=0;
	}
}