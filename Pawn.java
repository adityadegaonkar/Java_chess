public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);
        
        // Pawns can only move forward
        if (isWhite) {
            if (rowDiff >= 0) return false; // White pawns move up (decreasing row)
        } else {
            if (rowDiff <= 0) return false; // Black pawns move down (increasing row)
        }
        
        // Check if destination is occupied by own piece
        Piece destinationPiece = board[toRow][toCol];
        if (destinationPiece != null && destinationPiece.isWhite() == isWhite) {
            return false;
        }
        
        // Forward move (one or two squares)
        if (colDiff == 0) {
            if (destinationPiece != null) return false; // Can't capture forward
            
            if (Math.abs(rowDiff) == 1) {
                return true; // Normal forward move
            } else if (Math.abs(rowDiff) == 2) {
                // Two-square move only from starting position
                if ((isWhite && fromRow == 6) || (!isWhite && fromRow == 1)) {
                    return true;
                }
            }
        }
        // Diagonal capture
        else if (colDiff == 1 && Math.abs(rowDiff) == 1) {
            if (destinationPiece != null && destinationPiece.isWhite() != isWhite) {
                return true; // Can capture diagonally
            }
        }
        
        return false;
    }
    
    @Override
    public String getSymbol() {
        return isWhite ? "P" : "p";
    }
}
