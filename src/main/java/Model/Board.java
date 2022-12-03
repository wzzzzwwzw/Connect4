package Model;

public class Board {
    private int rows;
    private int columns;

    Piece [][]TheBoard;

    public Piece[][] getTheBoard() {
        return TheBoard;
    }
    public void setTheBoard(Piece[][] TheBoard)
    {
        this.TheBoard = TheBoard;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int addPiece(int colToAdd, String color) {
        // within normal range
        if(colToAdd >= 0 && colToAdd < columns) {
            // we can add
            if(TheBoard[0][colToAdd] == null) {
                boolean addedThePiece = false;
                int addedRow = -1;
                for(int row = rows - 1; row >= 0; row--)
                    if(TheBoard[row][colToAdd] == null) {
                        TheBoard[row][colToAdd] = new Piece();
                        TheBoard[row][colToAdd].setColor(color);
                        addedThePiece = true;
                        addedRow = row;
                        break;
                    }
                return addedRow;
            } else {
                // esa fila está llena
                System.err.println("This column is full, please choose another.");
                return -1;
            }
        } else {
            // fuera de los límites normales
            System.err.println("You are trying to add somewhere that is not supported.");
            return -1;
        }
    }
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        TheBoard = new Piece[rows][columns];
        for(int row = 0; row < rows; row++)
            for(int col = 0; col < columns; col++)
                TheBoard[row][col] = null;
    }
}