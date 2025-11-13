public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // Knights move in L-shape: 2 squares in one direction, 1 in perpendicular
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
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
        return isWhite ? "N" : "n";
    }
}
