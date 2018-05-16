package com.gebond.ip.controller;

import static com.gebond.ip.util.ImageIconUtil.buildIconForDimension;

import com.gebond.ip.model.setting.ResultSetting;
import com.gebond.ip.view.ResultsForm;
import com.gebond.ip.view.component.ResultItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;

/**
 * Created on 01/03/18.
 */
public class ResultsFormController extends ResultsForm {

  private List<ResultItem> resultItemList = new ArrayList<>();

  public ResultsFormController() {
    initListeners();
  }

  public void showUp() {
    super.setVisible(true);
  }

  public void addResult(ResultSetting resultSetting) {
    lastProcessedImage.setIcon(buildIconForDimension(resultSetting.getResultImage(), 290, 290));
    resultContent.setLayout(new BoxLayout(resultContent, BoxLayout.Y_AXIS));
    resultContent.add(new ResultItem(resultSetting));
    pack();
    if (!isVisible()) {
      showUp();
    }
  }

  public ResultItem getSelected() {
    return null;
  }

  private void initListeners() {
    // change RGB/YCrCb color schema
    clearResultsButton.addActionListener(e -> {
      resultContent.removeAll();
      pack();
    });
  }
}
