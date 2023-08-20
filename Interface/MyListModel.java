package GUI_Project.Interface;

import javax.swing.*;
import java.util.List;

public class MyListModel extends AbstractListModel<JPanel> {

    private List<JPanel> panelList;

    public MyListModel(List<JPanel> panelList) {
        this.panelList = panelList;
    }

    @Override
    public int getSize() {
        return panelList.size();
    }

    @Override
    public JPanel getElementAt(int index) {
        return panelList.get(index);
    }
}
