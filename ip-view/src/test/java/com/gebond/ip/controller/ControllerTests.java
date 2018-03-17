package com.gebond.ip.controller;

import com.gebond.ip.model.setting.TransformSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        @DisplayName("adjusting components")
        @Test
        void adjustingTest() {
            assertEquals(TransformSetting.TransformationType.values().length,
                    mainFormController.getSelectMethodBox().getModel().getSize());
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
        void renderResultsTest() {

        }
    }
}