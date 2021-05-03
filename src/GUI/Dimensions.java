package GUI;

public interface Dimensions {
    int Spacers            = 10;
    /*======GAME BOARD======*/
    int GameBoard_Width    = 600;
    int GameBoard_Height   = 600;
    /*======LEFT PANEL======*/
    int LeftPanel_Width    = 200;
    int LeftPanel_Height   = GameBoard_Height;
    /*======RIGHT PANEL======*/
    int RightPanel_Width   = 200;
    int RightPanel_Height  = GameBoard_Height;
    /*======TOP PANEL======*/
    int TopPanel_Width     = GameBoard_Width+LeftPanel_Width+RightPanel_Width+(4*Spacers);
    int TopPanel_Height    = 100;
    /*======BOTTOM PANEL======*/
    int BottomPanel_Width  = GameBoard_Width+LeftPanel_Width+RightPanel_Width+(4*Spacers);
    int BottomPanel_Height = 100;
    /*======FRAME======*/
    int Frame_Width        = GameBoard_Width+LeftPanel_Width+RightPanel_Width+5+(3*Spacers);
    int Frame_Height       = TopPanel_Height+BottomPanel_Height+GameBoard_Height+(6*Spacers);
}
