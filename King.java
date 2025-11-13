public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // King moves one square in any direction
        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }
        
        // Check if destination is occupied by own piece
        Piece destinationPiece = board[toRow][toCol];
        if (destinationPiece != null && destinationPiece.isWhite() == isWhite) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getSymbol() {
        return isWhite ? "K" : "k";
    }
}
