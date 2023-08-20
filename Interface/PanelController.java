package GUI_Project.Interface;

import GUI_Project.TrainSet;

import javax.swing.*;

public class PanelController {
    private JLabel currentStation;
    private JLabel nextStation;
    private JLabel roadPercent;

    public PanelController(JLabel currentStation, JLabel nextStation, JLabel roadPercent) {
        this.currentStation = currentStation;
        this.nextStation = nextStation;
        this.roadPercent = roadPercent;
    }

    public void updateLabel(TrainSet trainSet)
    {
        currentStation.setText(trainSet.getCurrentStation().toString());
        nextStation.setText(trainSet.getNextStation().toString());
    }

    public void updateRoad(double num){
        roadPercent.setText("distance travelled: " + num);
    }

}
