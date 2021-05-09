package GUI;

public interface Dimensions {
    int Spacers            = 10;
    /*======GAME BOARD======*/
    int GameBoard_Width    = 600;
    int GameBoard_Height   = 600;
    /*======SIDE PANELS======*/
    int SIDE_PANEL_WIDTH   = 200;
    int SIDE_PANEL_HEIGHT  = GameBoard_Height;
    /*======FRAME======*/
    int Frame_Width        = GameBoard_Width+ SIDE_PANEL_WIDTH + SIDE_PANEL_WIDTH +5+(3*Spacers);
    int Frame_Height       = GameBoard_Height+(4*Spacers);
}
