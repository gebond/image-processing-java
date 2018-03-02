package com.gebond.ip.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

/**
 * Created on 01/03/18.
 */
@DisplayName("Controllers")
public class ControllerTests {

    @Nested
    @DisplayName("Main Controller")
    public class MainControllerTests {
        private MainFormController mainFormController;

        @BeforeEach
        void init() {
            mainFormController = new MainFormController(new ResultsFormController());
        }

        @DisplayName("Renders buttons")
        void renderButtonsTest(){

        }
    }

    @Nested
    @DisplayName("Results Controller")
    public class ResultsControllerTests {
        private ResultsFormController resultsFormController;

        @BeforeEach
        void init() {
            resultsFormController = new ResultsFormController();
        }

        @DisplayName("Renders results")
        void renderResultsTest(){

        }
    }
}