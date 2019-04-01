package ytpplusui;

import javax.swing.DefaultListSelectionModel;

public class MultiListSelectionModel extends DefaultListSelectionModel {

  @Override
  public void setSelectionInterval(int index0, int index1) {
    if (index0 != index1) {
      super.setSelectionInterval(index0, index1);
      return;
    }
    if (isSelectedIndex(index0)) {
      super.removeSelectionInterval(index0, index1);
    } else {
      super.addSelectionInterval(index0, index1);
    }
  }
}