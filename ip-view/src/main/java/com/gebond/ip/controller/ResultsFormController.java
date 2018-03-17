package com.gebond.ip.controller;

import com.gebond.ip.model.setting.ResultSetting;
import com.gebond.ip.view.ResultsForm;
import com.gebond.ip.view.component.ResultItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 01/03/18.
 */
public class ResultsFormController {

    private ResultsForm resultsForm;
    private List<ResultItem> resultItemList = new ArrayList<>();

    public ResultsFormController() {
        initComponents();
        initListeners();
    }

    public void show() {
        resultsForm.setVisible(true);
    }

    public void addResult(ResultSetting resultSetting) {
        resultItemList.add(new ResultItem(resultSetting));
    }

    public ResultItem getSelected() {
        return null;
    }

    private void initComponents() {
        resultsForm = new ResultsForm();
    }

    private void initListeners() {
    }
}
