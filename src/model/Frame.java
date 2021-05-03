//package model;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Frame extends JFrame implements ActionListener{
//    /*=====================GAME=====================*/
//    public Game game = new Game();
//    Board.Result winner = Board.Result.Unknown;
//    /*=====================BUTTONS=====================*/
//    JButton undo = new JButton("UNDO");
//    JButton[] buttons = new JButton[9];
//    JButton replay = new JButton("REPLAY");
//    JButton back = new JButton("BACK");
//    JButton hint = new JButton("HINT");
//    /*=====================BOTTOM PANEL=====================*/
//    BottomPanel bottomPanel;
//    /*=====================HEADER=====================*/
//    Header header;
//    /*=====================LEFT PANEL=====================*/
//    LeftPanel scoreboard;
//    /*=====================RIGHT PANEL=====================*/
//    RightPanel settings;
//    /*=====================MAIN PANEL=====================*/
//    JLayeredPane mainPanel;
//        /*=====================WINNER PANEL=====================*/
//        JPanel winnerFrame;
//        JPanel buttonPanel;
//        JLabel winnerLabel;
//        /*=====================GAME BOARD=====================*/
//        TicTacToeBoard board;
//
//    Frame(){
//        /*=====================BUTTON INITIALIZATION=====================*/
//        for (int i = 0; i < buttons.length; i++) {
//            buttons[i]       = new JButton(" ");
//            buttons[i].addActionListener(this);
//        }
//        undo.addActionListener(this);
//        undo.setBorder(BorderFactory.createRaisedSoftBevelBorder());
//        undo.setBackground(new Color(160,160,160));
//
//        hint.addActionListener(this);
//        hint.setBorder(BorderFactory.createRaisedSoftBevelBorder());
//        hint.setBackground(new Color(160,160,160));
//        /*=====================PANELS INITIALIZATION=====================*/
//        scoreboard           = new LeftPanel();
//        settings             = new RightPanel();
//        header               = new Header();
//        bottomPanel          = new BottomPanel();
//        board                = new TicTacToeBoard();
//        /*=====================WINNER PANEL=====================*/
//        winnerFrame = new JPanel();
//        winnerFrame.setBounds(0,0,500,500);
//        winnerFrame.setBackground(new Color(0, 0, 0, 20));
//        winnerFrame.setOpaque(true);
//        winnerFrame.setFocusable(false);
//        winnerFrame.setLayout(new BorderLayout(10,10));
//        /*=====================WINNER LABEL=====================*/
//        winnerLabel = new JLabel();
//        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        winnerLabel.setVerticalAlignment(SwingConstants.CENTER);
//        winnerLabel.setVerticalTextPosition(SwingConstants.CENTER);
//        winnerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
//        winnerLabel.setFont(new Font("DialogInput",Font.BOLD,82));
//        winnerLabel.setForeground(Color.WHITE);
//        /*=====================WINNER BUTTON PANEL=====================*/
//        buttonPanel = new JPanel();
//        buttonPanel.setSize(500,200);
//        buttonPanel.setOpaque(false);
//        buttonPanel.setFocusable(false);
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        buttonPanel.setPreferredSize(new Dimension(500,200));
//        buttonPanel.setBounds(0,0,500,200);
//        /*=====================WINNER BUTTONS=====================*/
//        replay.setBounds(5,250,200,100);
//        replay.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
//        replay.setBackground(new Color(163, 163, 163));
//        replay.addActionListener(this);
//        replay.setOpaque(false);
//        replay.setFocusable(false);
//
//        back.setBounds(350,250,200,100);
//        back.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
//        back.setBackground(new Color(163, 163, 163));
//        back.addActionListener(this);
//        back.setOpaque(false);
//        back.setFocusable(false);
//        /*=====================BUTTON PANEL ADDITIONS=====================*/
//        buttonPanel.add(back);
//        buttonPanel.add(replay);
//        /*=====================FRAME ADDITIONS=====================*/
//        winnerFrame.add(winnerLabel,BorderLayout.NORTH);
//        winnerFrame.add(buttonPanel,BorderLayout.CENTER);
//        /*=====================PANE SETTINGS=====================*/
//        mainPanel = new JLayeredPane();
//        mainPanel.setBounds(0,0,500,500);
//        mainPanel.add(winnerFrame,0);
//        mainPanel.add(board,0);
//        /*=====================FRAME SETTINGS=====================*/
//        this.setSize(936,760); //sets the x-dimension and y-dimension
//        this.setTitle("Tic Tac Toe");  //Sets title
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Exit out of application
//        this.setResizable(false);  //prevents frame from being resized
//        ImageIcon image = new ImageIcon("logo.png");
//        this.setIconImage(image.getImage());
//        this.getContentPane().setBackground(new Color(44, 44,44));  //Change colour of background
//        this.setLayout(new BorderLayout(10,10));
//        /*=====================FRAME ADDITIONS=====================*/
//        this.add(settings,BorderLayout.EAST);
//        this.add(scoreboard,BorderLayout.WEST);
//        this.add(header,BorderLayout.NORTH);
//        this.add(bottomPanel,BorderLayout.SOUTH);
//        this.add(mainPanel,BorderLayout.CENTER);
//
//        this.setVisible(true); //make frame visible
//    }
//
//    public void updateBoard(){
//        for(int i = 0 ; i < 3 ; i ++ ){
//            for(int j = 0 ; j < 3 ; j++ ){
//                if(game.board.board[i][j] == Board.Cell.O) {
//                    buttons[(i * 3) + j].setText("O");
//                    buttons[(i * 3) + j].setBackground(new Color(40, 40, 40));
//                    buttons[(i * 3) + j].setEnabled(false);
//                }else if(game.board.board[i][j] == Board.Cell.X){
//                    buttons[(i * 3) + j].setText("X");
//                    buttons[(i * 3) + j].setBackground(new Color(40, 40, 40));
//                    buttons[(i * 3) + j].setEnabled(false);
//                }else{
//                    buttons[(i * 3) + j].setText(" ");
//                    buttons[(i * 3) + j].setBackground(new Color(80, 80, 80));
//                    buttons[(i * 3) + j].setEnabled(true);
//                }
//            }
//        }
//        winner = game.board.GetResult();
//        if(winner != Board.Result.Unknown){
//            for(int i = 0 ; i < 3 ; i ++ ) {
//                for (int j = 0; j < 3; j++) {
//                    buttons[(i * 3) + j].setEnabled(false);
//                }
//            }
//            if(winner == Board.Result.XWins){
//                winnerLabel.setText("X WINS");
//            }else if(winner == Board.Result.OWins){
//                winnerLabel.setText("O WINS");
//            }else{
//                winnerLabel.setText("TIE");
//            }
//            back.setOpaque(true);
//            back.setEnabled(true);
//            replay.setOpaque(true);
//            replay.setEnabled(true);
//            mainPanel.moveToBack(board);
//        }else{
//            back.setOpaque(false);
//            back.setEnabled(false);
//            replay.setOpaque(false);
//            replay.setEnabled(false);
//            mainPanel.moveToFront(board);
//        }
//    }
//
//    private void Hint(Move hint){
//        buttons[(hint.x*3) + hint.y].setBackground(new Color(120, 80, 80));
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        for (int i = 0; i < buttons.length; i++) {
//            if (e.getSource() == buttons[i]) {
//                game.parseMove(new Move(i / 3, i % 3, Board.Cell.X));
//            }
//        }
//        if (e.getSource() == replay) {
//            game.newGame();
//            updateBoard();
//        }else if(e.getSource() == undo){
//            game.undo();
//            game.undo();
//        }else if(e.getSource() == hint){
//            Hint(game.giveHint());
//            return;
//        }
//        updateBoard();
//    }
//
//    /*=====================PANELS=====================*/
//    class LeftPanel extends JPanel{
//        LeftPanel() {
//            this.setPreferredSize(new Dimension(200, 100));
//            this.setBackground(new Color(60, 63, 65));
//            this.setBounds(0, 0, 500, 500);
//            this.setBorder(BorderFactory.createRaisedBevelBorder());
//        }
//    }
//
//    class RightPanel extends JPanel{
//        RightPanel() {
//            this.setPreferredSize(new Dimension(200, 100));
//            this.setBackground(new Color(60, 63, 65));
//            this.setBounds(0, 0, 500, 500);
//            this.setBorder(BorderFactory.createRaisedBevelBorder());
//            this.setLayout(new GridBagLayout());
//            this.add(undo);
//            this.add(hint);
//        }
//    }
//
//    class Header extends JPanel{
//        Header() {
//            this.setPreferredSize(new Dimension(200,100));
//            this.setBackground(new Color(60, 63, 65));
//            this.setBounds(0,0,500,500);
//            this.setBorder(BorderFactory.createRaisedBevelBorder());
//        }
//    }
//
//    class BottomPanel extends JPanel{
//        BottomPanel() {
//            this.setPreferredSize(new Dimension(1050,100));
//            this.setMinimumSize(new Dimension(300,100));
//            this.setBackground(new Color(60, 63, 65));
//            this.setBounds(0,0,1050,100);
//            this.setBorder(BorderFactory.createRaisedBevelBorder());
//        }
//    }
//
//    class TicTacToeBoard extends JPanel {
//        TicTacToeBoard() {
//
//            /*=====================BOARD INITIALIZATION=====================*/
//            this.setPreferredSize(new Dimension(500, 500));
//            this.setMinimumSize(new Dimension(100, 100));
//            this.setBackground(new Color(43, 43, 43));
//            this.setBounds(0, 0, 500, 500);
//            this.setName("Board");
//            this.setLayout(new GridLayout(3, 3, 10, 10));
//            /*=====================BUTTON INITIALIZATION=====================*/
//            for (JButton button : buttons) {
//                button.setBackground(new Color(80, 80, 80));
//                button.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 40));
//                button.setFocusable(false);
//                this.add(button);
//            }
//        }
//    }
//}



