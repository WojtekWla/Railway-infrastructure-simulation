package GUI_Project.Interface;

import GUI_Project.MapGenerator;
import GUI_Project.Rail.Station;
import GUI_Project.TrainSet;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TabExpander;
import java.awt.*;

public class MyTable extends JTable{
    TableModel tableModel;
    public MyTable(TableModel tableModel){
        super(tableModel);
        this.tableModel = tableModel;
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0,0));
        setCellSelectionEnabled(false);

        TrainSet.myTable = this;
        for(int i = 0; i < tableModel.getColumnCount(); i++){
            getColumnModel().getColumn(i).setCellRenderer(new CellRenderer());
        }

        setTableHeader(null);

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            getColumnModel().getColumn(i).setPreferredWidth(30);
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
//        System.out.println("Enter paint Component");
        for (int i = 0; i < TrainSet.trainSets.size(); i++) {
            Station currStation = TrainSet.trainSets.get(i).getCurrentStation();
            Station nextStation = TrainSet.trainSets.get(i).getNextStation();



            MapGenerator.Position currP = findStation(currStation);
            MapGenerator.Position nextP = findStation(nextStation);

//            System.out.println("\n" + currStation + " " + currP + " " + nextStation + " " + nextP );

            Point currPoint = getCellRect(currP.x, currP.y, false).getLocation();
            Point nextPoint = getCellRect(nextP.x, nextP.y, false).getLocation();

//            Rectangle currPoint = getCellRect(currP.x, currP.y, false);
//            Rectangle nextPoint = getCellRect(nextP.x, nextP.y, false);
//            System.out.println(currPoint.height + "  " + currPoint.width + " currPoint.x " + currPoint.x + " currPoint.y "+ currPoint.y);


            g.drawLine((currPoint.x+7), (currPoint.y+15), (nextPoint.x+7), (nextPoint.y+15));

        }
//        System.out.println("Exit paintComponent");

    }
    
    private MapGenerator.Position findStation(Station station){
        MapGenerator.Position p = new MapGenerator.Position(0,0);
        for (int i = 0; i < tableModel.getStations().length; i++) {
            for (int j = 0; j < tableModel.getStations()[i].length; j++) {
                if(tableModel.getStations()[i][j] == station){
                    p.x = i;
                    p.y = j;
                }
            }
        }

        return p;
        
        
    }


    @Override
    public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
        return super.getCellRect(row, column, includeSpacing);
    }
}
