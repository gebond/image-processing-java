package com.gebond.ip;

import static gebond.ip.domain.manager.LogManager.log;

import com.gebond.ip.controller.MainFormController;
import com.gebond.ip.controller.ResultsFormController;

/**
 * Created on 28/02/18.
 */
public class Runner {

  public static void main(String[] args) {
    log("Runner started!");
    ResultsFormController resultsFormController = new ResultsFormController();
    MainFormController mainFormController = new MainFormController(resultsFormController);

//        resultsFormController.showUp();
    mainFormController.showUp();
  }
}
