package com.gebond.ip.controller;

import com.gebond.ip.model.setting.ResultSetting;
import com.gebond.ip.view.ResultsForm;
import com.gebond.ip.view.component.ResultItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.gebond.ip.util.UIUtills.buildIconForDimension;

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
//        resultItemList.add(new ResultItem(resultSetting));   // we set the max height to 75 and the max width to (almost) unlimited
        resultContent.setLayout(new BoxLayout(resultContent, BoxLayout.Y_AXIS));
        resultContent.add(new ResultItem(resultSetting));
        pack();
    }

    public ResultItem getSelected() {
        return null;
    }

    private void initListeners() {
    }
}
