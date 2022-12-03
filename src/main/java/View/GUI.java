package View;

import Controller.Connect4Control;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GUI extends JFrame{
        private final Connect4Control gameControl;
        private final Container cont;

        // 
        int rows;
        int columns;

        // TODO: Calcúlelos en su lugar
        int windowWidth = 750;
        int windowHeight = 650;

        // Preparar ImageIcons para su uso con JComponents
        private ImageIcon iconEmpty = null;
        private ImageIcon iconYellow = null;
        private ImageIcon iconRed = null;
        private ImageIcon iconCnc=null;


        private final String title = "Connect 4 Game - ";

        private void updateGUI(JButton button) {
            int row10plusCol = Integer.parseInt(button.getName());
            int col = row10plusCol % 10;

            boolean player1turn = gameControl.isPlayer();
            if(player1turn) setTitle(title + "Player 1");
            else setTitle(title + "Player 2");

            int addedRow = gameControl.round(col);

            if(addedRow != -1) {
                JButton buttonToUpdate = ((JButton)(cont.getComponent(columns * addedRow + col)));

                if(gameControl.isPlayer()) buttonToUpdate.setIcon(iconYellow);
                else buttonToUpdate.setIcon(iconRed);

                // comprobar el ganador
                if(gameControl.checkForWinnerInGUI(col)) {

                    JOptionPane.showMessageDialog(null, "YOU WON!");
                    int reply = JOptionPane.showConfirmDialog(null, "Do you want to Play again ?", null, JOptionPane.YES_NO_OPTION);
                    if(reply == JOptionPane.YES_OPTION) {
                        System.out.println("Loading...");
                        gameControl.reset(6, 7);
                        resetBoard();
                    } else {
                        System.exit(0);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Select a valid position.");
            }
        }

        public void resetBoard() {
            for(int row = 0; row < rows; row++)
                for (int col = 0; col < columns; col++)
                    ((JButton)(cont.getComponent(columns * row + col))).setIcon(iconEmpty);
        }

        //Constructor para configurar la GUI */

        public GUI(boolean player1turn, Connect4Control gamecontrol, int rows, int columns) {
            this.gameControl = gamecontrol;
            this.rows = rows;
            this.columns = columns;

            // Preparar los iconos
            //Ruta de la imagen relativa a la raíz del proyecto (es decir, bin)

            String imgEmptyFilename = "images/empty.png";
            URL imgURL = getClass().getClassLoader().getResource(imgEmptyFilename);
            if (imgURL != null) iconEmpty = new ImageIcon(imgURL);
            else System.err.println("File Not found: " + imgEmptyFilename);

            String imgRedFilename = "images/red.png";
            imgURL = getClass().getClassLoader().getResource(imgRedFilename);
            if (imgURL != null) iconRed = new ImageIcon(imgURL);
            else System.err.println("File Not found: " + imgRedFilename);


            String imgYellowFilename = "images/yellow.png";
            imgURL = getClass().getClassLoader().getResource(imgYellowFilename);
            if (imgURL != null) iconYellow = new ImageIcon(imgURL);
            else System.err.println("File Not found: " + imgYellowFilename);

            cont = getContentPane();
            cont.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

            for(int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    // utilizar el setter para establecer el texto y el icono
                    JButton button = new JButton();
                    button.setIcon(iconEmpty);
                    button.setPreferredSize(new Dimension(100, 100));

                    button.setName(Integer.toString((row * 10 + col)));

                    button.addActionListener(actionEvent -> updateGUI(((JButton) (actionEvent.getSource()))));
                    cont.add(button);
                }
            }
            String iconCncFilename = "images/cnc.png";
            URL imgCncURL = getClass().getClassLoader().getResource(iconCncFilename);
            Image icon = Toolkit.getDefaultToolkit().getImage(iconCncFilename);
            setIconImage(icon);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            if(!player1turn) setTitle(title + "Player 2");
            else setTitle(title + "Player 1");
            // Ventana central en la pantalla
            setLocationRelativeTo(null);
            setSize(windowWidth, windowHeight); // establecer la anchura y la altura manual
            setResizable(false);
            setVisible(true);
        }  }


