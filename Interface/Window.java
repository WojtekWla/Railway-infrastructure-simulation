package GUI_Project.Interface;

import GUI_Project.Rail.Station;
import GUI_Project.TrainSet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window extends JFrame {


    public Window(){

        JPanel jPanel = new JPanel(new BorderLayout());

        JLabel intro_text = new JLabel("Train simulator");
        intro_text.setVerticalAlignment(SwingConstants.CENTER);
        intro_text.setHorizontalAlignment(SwingConstants.CENTER);

        TableModel tableModel = new TableModel(Station.stationsOnMap);
        MyTable myTable = new MyTable(tableModel);

        JScrollPane vertical_jScrollPane = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        //trains road panel




        JPanel trainRoadPanel = new JPanel();
        trainRoadPanel.setLayout(new BoxLayout(trainRoadPanel, BoxLayout.Y_AXIS));
//
        for (int i = 0; i < TrainSet.trainSets.size(); i++) {
            JPanel trainPanel = new JPanel(new BorderLayout());
            JLabel trainName = new JLabel(TrainSet.trainSets.get(i).get_id());
            trainName.setHorizontalAlignment(SwingConstants.CENTER);
            trainName.setVerticalAlignment(SwingConstants.CENTER);
            JLabel currentStation = new JLabel(TrainSet.trainSets.get(i).getCurrentStation().toString());
            JLabel nextStation = new JLabel(TrainSet.trainSets.get(i).getNextStation().toString());
//            JProgressBar jProgressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
            JLabel roadPercent = new JLabel(" distance travelled: 0% ");

            TrainSet.trainSets.get(i).setPanelController(new PanelController(currentStation, nextStation, null));

            trainPanel.add(trainName, BorderLayout.NORTH);
            trainPanel.add(currentStation, BorderLayout.LINE_START);
            trainPanel.add(nextStation, BorderLayout.LINE_END);
            trainPanel.add(roadPercent, BorderLayout.CENTER);
//
            trainRoadPanel.add(trainPanel);
        }



        jPanel.add(intro_text, BorderLayout.NORTH);
        jPanel.add(vertical_jScrollPane, BorderLayout.CENTER);
        jPanel.add(trainRoadPanel, BorderLayout.LINE_END);
        add(jPanel);
//        add(jPanel1);
        pack();

//        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
