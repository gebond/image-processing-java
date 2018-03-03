package com.gebond.ip.view;

import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.TransformSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created on 03/03/18.
 */
@DisplayName("Main Form")
class MainFormTest {

    MainForm mainForm;

    @BeforeEach
    void setUp() {
        mainForm = new MainForm();
    }

    @Nested
    @DisplayName("Right Panel")
    class RightPanel {

        @Nested
        @DisplayName("Transform Settings")
        class TransformSettings {

            @Test
            @DisplayName("build transform setting")
            void selectBox() {
                JComboBox<TransformSetting.TransformationType> selectBox = mainForm.getSelectMethodBox();
                selectBox.addItem(TransformSetting.TransformationType.HAART_TRANSFORM);

                TransformSetting transformSettings = mainForm.buildTransformSetting();

                assertEquals(TransformSetting.TransformationType.HAART_TRANSFORM, transformSettings.getType());
            }

            @Test
            @DisplayName("radio button group, x8")
            void radioButtonGroups_x8() {
                JRadioButton selectX8 = mainForm.getSizeX8RadioButton();
                JRadioButton selectX16 = mainForm.getSizeX16RadioButton();
                JRadioButton selectX32 = mainForm.getSizeX32RadioButton();

                selectX8.setSelected(true);

                assertTrue(selectX8.isSelected());
                assertFalse(selectX16.isSelected());
                assertFalse(selectX32.isSelected());
            }

            @Test
            @DisplayName("radio button group, x16")
            void radioButtonGroups_x16() {
                JRadioButton selectX8 = mainForm.getSizeX8RadioButton();
                JRadioButton selectX16 = mainForm.getSizeX16RadioButton();
                JRadioButton selectX32 = mainForm.getSizeX32RadioButton();

                selectX16.setSelected(true);

                assertFalse(selectX8.isSelected());
                assertTrue(selectX16.isSelected());
                assertFalse(selectX32.isSelected());
            }

            @Test
            @DisplayName("radio button group, x32")
            void radioButtonGroups_x32() {
                JRadioButton selectX8 = mainForm.getSizeX8RadioButton();
                JRadioButton selectX16 = mainForm.getSizeX16RadioButton();
                JRadioButton selectX32 = mainForm.getSizeX32RadioButton();

                selectX32.setSelected(true);

                assertFalse(selectX8.isSelected());
                assertFalse(selectX16.isSelected());
                assertTrue(selectX32.isSelected());
            }
        }

        @Nested
        @DisplayName("Color Settings")
        class ColorSettings {

            @Test
            @DisplayName("build image settings")
            void buildImageSettings() {
                mainForm.getSizeX32RadioButton().setSelected(true);
                mainForm.getRgbRadioButton().setSelected(true);
                mainForm.getSlider1().setValue(10);
                mainForm.getSlider2().setValue(30);
                mainForm.getSlider3().setValue(90);

                ImageSetting imageSetting = mainForm.buildImageSetting(null);

                assertEquals(ImageSetting.ImageSchema.RGB, imageSetting.getImageSchema());
                assertEquals(ImageSetting.SegmentSize.X32, imageSetting.getSegmentSize());
                assertEquals(10.0, imageSetting.getImageValues().get(ImageSetting.RGB.RED.getOrder()).getCompressionRate());
                assertEquals(30.0, imageSetting.getImageValues().get(ImageSetting.RGB.GREEN.getOrder()).getCompressionRate());
                assertEquals(90.0, imageSetting.getImageValues().get(ImageSetting.RGB.BLUE.getOrder()).getCompressionRate());
            }

            @Test
            @DisplayName("build image settings 2")
            void buildImageSettings2() {
                mainForm.getSizeX16RadioButton().setSelected(true);
                mainForm.getYcrcbRadioButton().setSelected(true);
                mainForm.getSlider1().setValue(11);
                mainForm.getSlider2().setValue(31);
                mainForm.getSlider3().setValue(91);

                ImageSetting imageSetting = mainForm.buildImageSetting(null);

                assertEquals(ImageSetting.ImageSchema.YCRCB, imageSetting.getImageSchema());
                assertEquals(ImageSetting.SegmentSize.X16, imageSetting.getSegmentSize());
                assertEquals(11.0, imageSetting.getImageValues().get(ImageSetting.RGB.RED.getOrder()).getCompressionRate());
                assertEquals(31.0, imageSetting.getImageValues().get(ImageSetting.RGB.GREEN.getOrder()).getCompressionRate());
                assertEquals(91.0, imageSetting.getImageValues().get(ImageSetting.RGB.BLUE.getOrder()).getCompressionRate());
            }

            @Test
            @DisplayName("build image settings 3")
            void buildImageSettings3() {
                mainForm.getSizeX8RadioButton().setSelected(true);
                mainForm.getYcrcbRadioButton().setSelected(true);
                mainForm.getSlider1().setValue(12);
                mainForm.getSlider2().setValue(32);
                mainForm.getSlider3().setValue(92);

                ImageSetting imageSetting = mainForm.buildImageSetting(null);

                assertEquals(ImageSetting.ImageSchema.YCRCB, imageSetting.getImageSchema());
                assertEquals(ImageSetting.SegmentSize.X8, imageSetting.getSegmentSize());
                assertEquals(12.0, imageSetting.getImageValues().get(ImageSetting.RGB.RED.getOrder()).getCompressionRate());
                assertEquals(32.0, imageSetting.getImageValues().get(ImageSetting.RGB.GREEN.getOrder()).getCompressionRate());
                assertEquals(92.0, imageSetting.getImageValues().get(ImageSetting.RGB.BLUE.getOrder()).getCompressionRate());
            }

            @Test
            @DisplayName("radio button group, RGB")
            void radioButtonGroups_RGB() {
                JRadioButton rgb = mainForm.getRgbRadioButton();
                JRadioButton ycrcb = mainForm.getYcrcbRadioButton();

                rgb.setSelected(true);

                assertTrue(rgb.isSelected());
                assertFalse(ycrcb.isSelected());
            }

            @Test
            @DisplayName("radio button group, YCrCb")
            void radioButtonGroups_YCrCb() {
                JRadioButton rgb = mainForm.getRgbRadioButton();
                JRadioButton ycrcb = mainForm.getYcrcbRadioButton();

                ycrcb.setSelected(true);

                assertTrue(ycrcb.isSelected());
                assertFalse(rgb.isSelected());
            }
        }
    }
}