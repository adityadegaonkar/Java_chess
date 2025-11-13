import java.util.Scanner;

public class ChessGame {
    private ChessBoard board;
    private Scanner scanner;
    
    public ChessGame() {
        board = new ChessBoard();
        scanner = new Scanner(System.in);
    }
    
    public void play() {
        System.out.println("Welcome to Chess!");
        System.out.println("Enter moves in format: from to (e.g., e2 e4)");
        System.out.println("Type 'quit' to exit the game.");
        System.out.println();
        
        while (!board.isGameOver()) {
            board.printBoard();
            System.out.println();
            
            if (board.isInCheck(board.isWhiteTurn())) {
                System.out.println("CHECK!");
            }
            
            System.out.print("Enter your move: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing!");
                break;
            }
            
            if (processMove(input)) {
                System.out.println("Valid move!");
            } else {
                System.out.println("Invalid move! Please try again.");
            }
            System.out.println();
        }
        
        if (board.isGameOver()) {
            board.printBoard();
            System.out.println("Game Over! " + board.getWinner() + " wins!");
        }
        
        scanner.close();
    }
    
    private boolean processMove(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length != 2) {
            return false;
        }
        
        try {
            int[] from = parsePosition(parts[0]);
            int[] to = parsePosition(parts[1]);
            
            if (from == null || to == null) {
                return false;
            }
            
            return board.makeMove(from[0], from[1], to[0], to[1]);
        } catch (Exception e) {
            return false;
        }
    }
    
    private int[] parsePosition(String position) {
        if (position.length() != 2) {
            return null;
        }
        
        char col = position.charAt(0);
        char row = position.charAt(1);
        
        if (col < 'a' || col > 'h' || row < '1' || row > '8') {
            return null;
        }
        
        // Convert to array indices
        int colIndex = col - 'a';
        int rowIndex = 8 - (row - '0');
        
        return new int[]{rowIndex, colIndex};
    }
    
    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.play();
    }
}
