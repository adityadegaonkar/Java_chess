public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        // Rooks move horizontally or vertically
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        
        // Check if destination is occupied by own piece
        Piece destinationPiece = board[toRow][toCol];
        if (destinationPiece != null && destinationPiece.isWhite() == isWhite) {
            return false;
        }
        
        // Check for pieces in the path
        int rowStep = fromRow == toRow ? 0 : (toRow - fromRow) / Math.abs(toRow - fromRow);
        int colStep = fromCol == toCol ? 0 : (toCol - fromCol) / Math.abs(toCol - fromCol);
        
        int currentRow = fromRow + rowStep;
        int currentCol = fromCol + colStep;
        
        while (currentRow != toRow || currentCol != toCol) {
            if (board[currentRow][currentCol] != null) {
                return false; // Path is blocked
            }
            currentRow += rowStep;
            currentCol += colStep;
        }
        
        return true;
    }
    
    @Override
    public String getSymbol() {
        return isWhite ? "R" : "r";
    }
}
