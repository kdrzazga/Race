package presentation;

import presentation.test.GameTest;

class MainGameScreenAdapter extends java.awt.event.WindowAdapter {

    private final GameTest introFrame;
    
    public MainGameScreenAdapter(GameTest introFrame)
    {
        this.introFrame = introFrame;
    }
    
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        this.introFrame.setVisible(true);
    }
}
 