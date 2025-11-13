public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // Bishops move diagonally
        if (rowDiff != colDiff) {
            return false;
        }
        
        // Check if destination is occupied by own piece
        Piece destinationPiece = board[toRow][toCol];
        if (destinationPiece != null && destinationPiece.isWhite() == isWhite) {
            return false;
        }
        
        // Check for pieces in the diagonal path
        int rowStep = (toRow - fromRow) / rowDiff;
        int colStep = (toCol - fromCol) / colDiff;
        
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
        return isWhite ? "B" : "b";
    }
}
