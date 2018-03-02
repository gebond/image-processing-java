package com.gebond.ip;

import com.gebond.ip.controller.MainFormController;
import com.gebond.ip.controller.ResultsFormController;

/**
 * Created on 28/02/18.
 */
public class Runner {

    public static void main(String[] args) {
        ResultsFormController resultsFormController = new ResultsFormController();
        MainFormController mainFormController = new MainFormController(resultsFormController);

        resultsFormController.show();
        mainFormController.show();
    }
}
