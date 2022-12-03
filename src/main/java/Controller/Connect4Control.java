package Controller;

import Model.Board;
import Model.Piece;

import java.util.Random;

public class Connect4Control {

        private Board board;
        private final String color1;
        private final String color2;

    // verdadero si es el turno del jugador1
    // falso si es el turno del jugador2
        private boolean isPlayer;

        public boolean isPlayer() {
            return isPlayer;
        }

        public Connect4Control(String color1, String color2, int rows, int columns) {
            this.board = new Board(rows, columns);
            this.color1 = color1;
            this.color2 = color2;

            isPlayer = (new Random()).nextBoolean();
        }
        public int round(int col) {
            String color = isPlayer ? color1 : color2;

            int row = board.addPiece(col, color);

            if(row != -1) isPlayer = !isPlayer;

            return row;
        }

        public boolean checkForWinnerInGUI(int column) {
            String winningColor;

            // inversión debido a la información tardía
            if(!isPlayer) {
                winningColor = color1;
            } else {
                winningColor = color2;
            }

            return checkForWinner(column, winningColor);
        }

        private boolean checkDiagonal(int row, int col, String winningColor, boolean rightDiagonal) {
            int winningStreak = 4;
            int reverser = rightDiagonal ? 1 : -1;
            int rows = board.getRows();
            int columns = board.getColumns();
            Piece[][] ourBoard = board.getTheBoard();

            for(int winRow = row - 3, winCol = col - (3 * reverser); winRow <= row + 3; winRow++, winCol += reverser) {
                if (!rightDiagonal) {
                    if (winRow < 0 || winCol < 0) continue;
                    if (winRow >= rows || winCol >= columns) break;
                } else {
                    if(winRow < 0 || winCol >= columns) continue;
                    if(winRow >= rows || winCol < 0) break;
                }

                if(ourBoard[winRow][winCol] != null && ourBoard[winRow][winCol].getColor().equals(winningColor)) {
                    if (--winningStreak == 0) return true;
                } else winningStreak = 4;
            }
            return false;
        }

        public boolean checkForWinner(int col, String winningColor) {
            int rows = board.getRows();
            int columns = board.getColumns();
            Piece[][] ourBoard = board.getTheBoard();

            for(int row = 0; row < rows; row++) {
                if(ourBoard[row][col] != null) {
                    // si esto llega a 0, hemos ganado
                    int winningStreak = 3;

                    // comprobar hacia abajo
                    for(int winRow = row + 1; winRow < rows; winRow++) {
                        if(ourBoard[winRow][col].getColor().equals(winningColor)) {
                            winningStreak--;
                            if(winningStreak == 0) return true;
                        } else winningStreak = 3;
                    }

                    winningStreak = 4;
                    // mira la horizontal
                    for(int winCol = col - 3; winCol <= col + 3; winCol++) {
                        if(winCol < 0) continue;
                        if(winCol >= columns) break;

                        if(ourBoard[row][winCol] != null && ourBoard[row][winCol].getColor().equals(winningColor)) {
                            winningStreak--;
                            if(winningStreak == 0) return true;
                        } else winningStreak = 4;
                    }

                    if(checkDiagonal(row, col, winningColor, false)) return true;
                    if(checkDiagonal(row, col, winningColor, true)) return true;

                    return false;
                }
            }
            return false;
        }

        public void reset(int rows, int columns) {
            this.board = new Board(rows, columns);
            isPlayer = (new Random()).nextBoolean();
        }

    }
