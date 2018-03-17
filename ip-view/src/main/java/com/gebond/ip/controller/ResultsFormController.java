package com.gebond.ip.controller;

import com.gebond.ip.model.setting.ResultSetting;
import com.gebond.ip.view.ResultsForm;
import com.gebond.ip.view.component.ResultItem;

import java.util.ArrayList;
import java.util.List;

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
        resultItemList.add(new ResultItem(resultSetting));
    }

    public ResultItem getSelected() {
        return null;
    }

    private void initListeners() {
    }
}
