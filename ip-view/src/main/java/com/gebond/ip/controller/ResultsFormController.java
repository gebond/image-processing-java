package com.gebond.ip.controller;

import com.gebond.ip.view.ResultsForm;

/**
 * Created on 01/03/18.
 */
public class ResultsFormController {

    private ResultsForm resultsForm;

    public ResultsFormController() {
        initComponents();
        initListeners();
    }

    public void show() {
        resultsForm.setVisible(true);
    }

    private void initComponents() {
        resultsForm = new ResultsForm();
    }

    private void initListeners() {
    }
}
