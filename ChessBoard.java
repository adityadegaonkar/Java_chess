public class ChessBoard {
    private Piece[][] board;
    private boolean whiteTurn;
    private boolean gameOver;
    private String winner;
    
    public ChessBoard() {
        board = new Piece[8][8];
        whiteTurn = true;
        gameOver = false;
        initializeBoard();
    }
    
    private void initializeBoard() {
        // Initialize empty board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
        
        // Place white pieces
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);
        
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(true);
        }
        
        // Place black pieces
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);
        
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
        }
    }
    
    public Piece getPiece(int row, int col) {
        if (isValidPosition(row, col)) {
            return board[row][col];
        }
        return null;
    }
    
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
    
    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidPosition(fromRow, fromCol) || !isValidPosition(toRow, toCol)) {
            return false;
        }
        
        Piece piece = board[fromRow][fromCol];
        if (piece == null) {
            return false;
        }
        
        // Check if it's the correct player's turn
        if (piece.isWhite() != whiteTurn) {
            return false;
        }
        
        // Check if the move is valid for the piece
        if (!piece.isValidMove(fromRow, fromCol, toRow, toCol, board)) {
            return false;
        }
        
        // Make the move
        Piece capturedPiece = board[toRow][toCol];
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = null;
        
        // Check for check/checkmate
        if (isInCheck(!whiteTurn)) {
            if (isCheckmate(!whiteTurn)) {
                gameOver = true;
                winner = whiteTurn ? "White" : "Black";
            }
        }
        
        // Switch turns
        whiteTurn = !whiteTurn;
        return true;
    }
    
    public boolean isInCheck(boolean isWhite) {
        // Find the king
        int kingRow = -1, kingCol = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece instanceof King && piece.isWhite() == isWhite) {
                    kingRow = i;
                    kingCol = j;
                    break;
                }
            }
        }
        
        if (kingRow == -1) return false;
        
        // Check if any opponent piece can attack the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.isWhite() != isWhite) {
                    if (piece.isValidMove(i, j, kingRow, kingCol, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isCheckmate(boolean isWhite) {
        if (!isInCheck(isWhite)) return false;
        
        // Try all possible moves for the player in check
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.isWhite() == isWhite) {
                    for (int toRow = 0; toRow < 8; toRow++) {
                        for (int toCol = 0; toCol < 8; toCol++) {
                            if (piece.isValidMove(i, j, toRow, toCol, board)) {
                                // Simulate the move
                                Piece originalPiece = board[toRow][toCol];
                                board[toRow][toCol] = piece;
                                board[i][j] = null;
                                
                                boolean stillInCheck = isInCheck(isWhite);
                                
                                // Restore the board
                                board[i][j] = piece;
                                board[toRow][toCol] = originalPiece;
                                
                                if (!stillInCheck) {
                                    return false; // Found a valid move
                                }
                            }
                        }
                    }
                }
            }
        }
        return true; // No valid moves found
    }
    
    public void printBoard() {
        System.out.println("   a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + "  ");
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(piece.getSymbol() + " ");
                }
            }
            System.out.println(" " + (8 - i));
        }
        System.out.println("   a b c d e f g h");
        System.out.println("Current turn: " + (whiteTurn ? "White" : "Black"));
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String getWinner() {
        return winner;
    }
    
    public boolean isWhiteTurn() {
        return whiteTurn;
    }
}
