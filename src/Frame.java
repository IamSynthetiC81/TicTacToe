import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener{
    /*=====================PANEL DIMENSIONS=====================*/
    public final static int Spacers            = 10;
    /*======GAME BOARD======*/
    public final static int GameBoard_Width    = 600;
    public final static int GameBoard_Height   = 600;
    /*======LEFT PANEL======*/
    public final static int LeftPanel_Width    = 200;
    public final static int LeftPanel_Height   = GameBoard_Height;
    /*======RIGHT PANEL======*/
    public final static int RightPanel_Width   = 200;
    public final static int RightPanel_Height  = GameBoard_Height;
    /*======TOP PANEL======*/
    public final static int TopPanel_Width     = GameBoard_Width+LeftPanel_Width+RightPanel_Width+(4*Spacers);
    public final static int TopPanel_Height    = 100;
    /*======BOTTOM PANEL======*/
    public final static int BottomPanel_Width  = GameBoard_Width+LeftPanel_Width+RightPanel_Width+(4*Spacers);
    public final static int BottomPanel_Height = 100;
    /*======FRAME======*/
    public final static int Frame_Width        = GameBoard_Width+LeftPanel_Width+RightPanel_Width+(4*Spacers);
    public final static int Frame_Height       = TopPanel_Height+BottomPanel_Height+GameBoard_Height+(6*Spacers);
    /*=====================COLORS=====================*/
    public final static Color DARK_GREY   = new Color(43,43,43);
    public final static Color BUTTON_GREY = new Color(160,160,160);
    public final static Color FRAME_GREY  = new Color(63,63,63);
    public final static Color GREY        = new Color(80,80,80);
    public final static Color HINT_RED    = new Color(120,80,80);
    public final static Color TINT        = new Color(0,0,0,20);
    /*=====================GAME=====================*/
    public static Game game = new Game();
    public static Board.Result winner = Board.Result.Unknown;
    /*=====================BUTTONS=====================*/
    public JButton undo = new JButton("UNDO");
    public JButton[] buttons = new JButton[9];
    public JButton replay = new JButton("REPLAY");
    public JButton back = new JButton("BACK");
    public JButton hint = new JButton("HINT");
    /*=====================PANEL INITIALIZATION=====================*/
    public final BottomPanel bottomPanel = new BottomPanel();;
    public final Header header = new Header();
    public final LeftPanel scoreboard = new LeftPanel();
    public final RightPanel settings = new RightPanel();
    public final JLayeredPane mainPanel = new JLayeredPane();
    public final JPanel winnerFrame = new JPanel();
    public final JPanel buttonPanel = new JPanel();
    public final JLabel winnerLabel = new JLabel();
    public final TicTacToeBoard board = new TicTacToeBoard();

    Frame(){
        /*=====================BUTTON INITIALIZATION=====================*/
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
        undo.addActionListener(this);
        undo.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        undo.setBackground(BUTTON_GREY);
        hint.addActionListener(this);
        hint.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        hint.setBackground(BUTTON_GREY);
        /*=====================WINNER PANEL=====================*/
        winnerFrame.setBounds(0,0,GameBoard_Width,GameBoard_Height);
        winnerFrame.setBackground(TINT);
        winnerFrame.setOpaque(true);
        winnerFrame.setFocusable(false);
        winnerFrame.setLayout(new BorderLayout(10,10));
        /*=====================WINNER LABEL=====================*/
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setVerticalAlignment(SwingConstants.CENTER);
        winnerLabel.setVerticalTextPosition(SwingConstants.CENTER);
        winnerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        winnerLabel.setFont(new Font("DialogInput",Font.BOLD,82));
        winnerLabel.setForeground(Color.WHITE);
        /*=====================WINNER BUTTON PANEL=====================*/
//        buttonPanel.setSize(GameBoard_Width,200);
        buttonPanel.setOpaque(false);
        buttonPanel.setFocusable(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        buttonPanel.setPreferredSize(new Dimension(GameBoard_Width,200));
        buttonPanel.setBounds(0,0,GameBoard_Width,200);
        /*=====================WINNER BUTTONS=====================*/
        replay.setBounds(5,250,200,100);
        replay.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        replay.setBackground(BUTTON_GREY);
        replay.addActionListener(this);
        replay.setOpaque(false);
        replay.setFocusable(false);

        back.setBounds(350,250,200,100);
        back.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        back.setBackground(BUTTON_GREY);
        back.addActionListener(this);
        back.setOpaque(false);
        back.setFocusable(false);
        /*=====================BUTTON PANEL ADDITIONS=====================*/
        buttonPanel.add(back);
        buttonPanel.add(replay);
        /*=====================FRAME ADDITIONS=====================*/
        winnerFrame.add(winnerLabel,BorderLayout.NORTH);
        winnerFrame.add(buttonPanel,BorderLayout.CENTER);
        /*=====================PANE SETTINGS=====================*/
        mainPanel.setBounds(0,0,GameBoard_Width,GameBoard_Height);
        mainPanel.add(winnerFrame,0);
        mainPanel.add(board,0);
        /*=====================FRAME SETTINGS=====================*/
        this.setPreferredSize(new Dimension(Frame_Width,Frame_Height));
        this.setSize(Frame_Width,Frame_Height); //sets the x-dimension and y-dimension
        this.setTitle("Tic Tac Toe");  //Sets title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Exit out of application
//        this.setResizable(false);  //prevents frame from being resized
        ImageIcon image = new ImageIcon("logo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(DARK_GREY);  //Change colour of background
        this.setLayout(new BorderLayout(Spacers,Spacers));
        /*=====================FRAME ADDITIONS=====================*/
        this.add(settings,BorderLayout.EAST);
        this.add(scoreboard,BorderLayout.WEST);
        this.add(header,BorderLayout.NORTH);
        this.add(bottomPanel,BorderLayout.SOUTH);
        this.add(mainPanel,BorderLayout.CENTER);

        this.setVisible(true); //make frame visible
    }

    public void updateBoard(){
        for(int i = 0 ; i < 3 ; i ++ ){
            for(int j = 0 ; j < 3 ; j++ ){
                if(game.board.board[i][j] == Board.Cell.O) {
                    buttons[(i * 3) + j].setText("O");
                    buttons[(i * 3) + j].setBackground(new Color(40, 40, 40));
                    buttons[(i * 3) + j].setEnabled(false);
                }else if(game.board.board[i][j] == Board.Cell.X){
                    buttons[(i * 3) + j].setText("X");
                    buttons[(i * 3) + j].setBackground(new Color(40, 40, 40));
                    buttons[(i * 3) + j].setEnabled(false);
                }else{
                    buttons[(i * 3) + j].setText(" ");
                    buttons[(i * 3) + j].setBackground(new Color(80, 80, 80));
                    buttons[(i * 3) + j].setEnabled(true);
                }
            }
        }
        winner = game.board.GetResult();
        if(winner != Board.Result.Unknown){
            for(int i = 0 ; i < 3 ; i ++ ) {
                for (int j = 0; j < 3; j++) {
                    buttons[(i * 3) + j].setEnabled(false);
                }
            }
            if(winner == Board.Result.XWins){
                winnerLabel.setText("X WINS");
            }else if(winner == Board.Result.OWins){
                winnerLabel.setText("O WINS");
            }else{
                winnerLabel.setText("TIE");
            }
            back.setOpaque(true);
            back.setEnabled(true);
            replay.setOpaque(true);
            replay.setEnabled(true);
            mainPanel.moveToBack(board);
        }else{
            back.setOpaque(false);
            back.setEnabled(false);
            replay.setOpaque(false);
            replay.setEnabled(false);
            mainPanel.moveToFront(board);
        }
    }

    private void Hint(Move hint){
        buttons[(hint.x*3) + hint.y].setBackground(HINT_RED);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                game.parseMove(new Move(i / 3, i % 3, Board.Cell.X));
            }
        }
        if (e.getSource() == replay) {
            game.newGame();
            updateBoard();
        }else if(e.getSource() == undo){
            game.undo();
            game.undo();
        }else if(e.getSource() == hint){
            Hint(game.giveHint());
            return;
        }
        updateBoard();
    }

    /*=====================PANELS=====================*/
    class LeftPanel extends JPanel{
        LeftPanel() {
            this.setPreferredSize(new Dimension(LeftPanel_Width,LeftPanel_Height));
            this.setBackground(FRAME_GREY);
            this.setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }

    class RightPanel extends JPanel{
        RightPanel() {
            this.setPreferredSize(new Dimension(RightPanel_Width, RightPanel_Height));
            this.setBackground(FRAME_GREY);
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setLayout(new GridBagLayout());
            this.add(undo);
            this.add(hint);
        }
    }

    class Header extends JPanel{
        Header() {
            this.setPreferredSize(new Dimension(TopPanel_Width,TopPanel_Height));
            this.setBackground(FRAME_GREY);
            this.setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }

    class BottomPanel extends JPanel{
        BottomPanel() {
            this.setPreferredSize(new Dimension(BottomPanel_Width,BottomPanel_Height));
            this.setBackground(FRAME_GREY);
            this.setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }

    class TicTacToeBoard extends JPanel{
        TicTacToeBoard() {
            /*=====================BOARD INITIALIZATION=====================*/
            this.setBackground(DARK_GREY);
            this.setBounds(0, 0, GameBoard_Width, GameBoard_Height);
            this.setName("Board");
            this.setLayout(new GridLayout(3, 3, Spacers, Spacers));
            /*=====================BUTTON INITIALIZATION=====================*/
            for (int i = 0 ; i < buttons.length ; i++) {
                buttons[i] = new JButton(" ");
                buttons[i].setBackground(BUTTON_GREY);
                buttons[i].setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 40));
                buttons[i].setFocusable(false);
                this.add(buttons[i]);
            }
        }
    }
}



