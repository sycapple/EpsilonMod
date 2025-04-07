//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EpsilonMod.panels;

import basemod.BaseMod;
import basemod.ModButton;
import basemod.ModLabel;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.ModSlider;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import java.io.IOException;
import java.util.Properties;
import EpsilonMod.util.epsilonModHelper;
public class modConfig implements PostInitializeSubscriber {
    private ModLabeledToggleButton tooltipButton;
    private ModLabeledToggleButton dynamicButton;
    private ModLabeledToggleButton dynamicTextButton;
    private ModLabeledToggleButton frozenEyeButton;
    private ModSlider dhSlider;
    private ModSlider dwSlider;
    private ModSlider dtSlider;
    private ModSlider dishSlider;
    private ModSlider distSlider;
    private ModSlider diswSlider;
    private static SpireConfig config;

    public modConfig() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
    }

    private static SpireConfig makeConfig() {
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("extended-tooltip", Boolean.toString(false));
        defaultProperties.setProperty("dynamic-update", Boolean.toString(true));
        defaultProperties.setProperty("dynamic-text", Boolean.toString(true));
        defaultProperties.setProperty("frozen-eye", Boolean.toString(true));
        defaultProperties.setProperty("draw-width", Integer.toString(200));
        defaultProperties.setProperty("draw-height", Integer.toString(28));
        defaultProperties.setProperty("draw-text-size", Float.toString(0.7F));
        defaultProperties.setProperty("draw-x", Float.toString(0.0F));
        defaultProperties.setProperty("draw-y", Float.toString((float)Settings.HEIGHT - 140.0F * Settings.scale));
        defaultProperties.setProperty("discard-width", Integer.toString(130));
        defaultProperties.setProperty("discard-height", Integer.toString(28));
        defaultProperties.setProperty("discard-text-size", Float.toString(0.6F));
        defaultProperties.setProperty("discard-x", Float.toString(0.0F));
        defaultProperties.setProperty("discard-y", Float.toString((float)Settings.HEIGHT - 140.0F * Settings.scale));

        try {
            SpireConfig retConfig = new SpireConfig("StSDeckTracker", "StSDeckTracker-config", defaultProperties);
            return retConfig;
        } catch (IOException var2) {
            return null;
        }
    }

    private static Boolean getBoolean(String key) {
        return config.getBool(key);
    }

    static void setBoolean(String key, Boolean value) {
        config.setBool(key, value);

        try {
            config.save();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private static int getInt(String key) {
        return config.getInt(key);
    }

    static void setInt(String key, int value) {
        config.setInt(key, value);

        try {
            config.save();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private static Float getFloat(String key) {
        return config.getFloat(key);
    }

    static void setFloat(String key, float value) {
        config.setFloat(key, value);

        try {
            config.save();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private static void setProperties() {
        if (config != null) {
            Boolean entryB;
            try {
                entryB = getBoolean("extended-tooltip");
                allUnitPanel.extendedTooltips = entryB;
            } catch (Exception var18) {
                allUnitPanel.extendedTooltips = false;
            }

            try {
                entryB = getBoolean("dynamic-update");
                allUnitPanel.dynamicUpdate = entryB;
            } catch (Exception var17) {
                allUnitPanel.dynamicUpdate = true;
            }

            try {
                entryB = getBoolean("dynamic-text");
                allUnitPanel.dynamicText = entryB;
            } catch (Exception var16) {
                allUnitPanel.dynamicText = true;
            }

            try {
                entryB = getBoolean("frozen-eye");
                allUnitPanel.frozenEye = entryB;
            } catch (Exception var15) {
                allUnitPanel.frozenEye = true;
            }

            int entryI;
            try {
                entryI = getInt("draw-width");
                allUnitPanel.drawWidth = entryI;
            } catch (Exception var14) {
                allUnitPanel.drawWidth = 200;
            }

            try {
                entryI = getInt("draw-height");
                allUnitPanel.defaultDrawHeight = entryI;
            } catch (Exception var13) {
                allUnitPanel.defaultDrawHeight = 28;
            }

            float entryF;
            try {
                entryF = getFloat("draw-text-size");
                allUnitPanel.defaultDrawText = entryF;
            } catch (Exception var12) {
                allUnitPanel.defaultDrawText = 0.7F;
            }

            try {
                entryF = getFloat("draw-x");
                allUnitPanel.xloc = allUnitPanel.clamp(entryF, 0.0F, (float)Settings.WIDTH - (float)(allUnitPanel.drawWidth + allUnitPanel.drawHeight * 2) * Settings.scale);
                allUnitPanel.previousxloc = allUnitPanel.xloc;
            } catch (Exception var11) {
                allUnitPanel.xloc = 0.0F;
            }

            try {
                entryF = getFloat("draw-y");
                allUnitPanel.yOffset = allUnitPanel.clamp(entryF, 200.0F, (float)Settings.HEIGHT - 140.0F * Settings.scale);
                allUnitPanel.previousyOffset = allUnitPanel.yOffset;
            } catch (Exception var10) {
                allUnitPanel.yOffset = (float)Settings.HEIGHT - 140.0F * Settings.scale;
            }

            try {
                entryI = getInt("discard-width");
                allUnitPanel.discardWidth = entryI;
            } catch (Exception var9) {
                allUnitPanel.discardWidth = 130;
            }

            try {
                entryI = getInt("discard-height");
                allUnitPanel.defaultDiscardHeight = entryI;
            } catch (Exception var8) {
                allUnitPanel.defaultDiscardHeight = 28;
            }

            try {
                entryF = getFloat("discard-text-size");
                allUnitPanel.defaultDiscardText = entryF;
            } catch (Exception var7) {
                allUnitPanel.defaultDiscardText = 0.7F;
            }

            try {
                entryF = getFloat("discard-x");
                allUnitPanel.xlocDiscard = allUnitPanel.clamp(entryF, 0.0F, (float)Settings.WIDTH - (float)(allUnitPanel.drawWidth + allUnitPanel.drawHeight * 2) * Settings.scale);
                allUnitPanel.previousxlocDiscard = allUnitPanel.xlocDiscard;
            } catch (Exception var6) {
                allUnitPanel.xlocDiscard = 0.0F;
            }

            try {
                entryF = getFloat("discard-y");
                allUnitPanel.yOffsetDiscard = allUnitPanel.clamp(entryF, 200.0F, (float)Settings.HEIGHT - 140.0F * Settings.scale);
                allUnitPanel.previousyOffsetDiscard = allUnitPanel.yOffsetDiscard;
            } catch (Exception var5) {
                allUnitPanel.yOffsetDiscard = (float)Settings.HEIGHT - 140.0F * Settings.scale;
            }
        }

    }

    public void receivePostInitialize() {
        config = makeConfig();
        setProperties();
        ModPanel settingsPanel = new ModPanel();
        float y = 750.0F;
        float x = 375.0F;
        ModButton defaultButton = new ModButton(1400.0F, y - 70.0F * Settings.scale, ImageMaster.loadImage(epsilonModHelper.assetPath("img/UI/Config/DefaultButton.png")), settingsPanel, (button) -> {
            allUnitPanel.extendedTooltips = false;
            allUnitPanel.dynamicUpdate = true;
            allUnitPanel.dynamicText = true;
            allUnitPanel.frozenEye = true;
            allUnitPanel.drawWidth = 200;
            allUnitPanel.defaultDrawHeight = 28;
            allUnitPanel.defaultDrawText = 0.7F;
            allUnitPanel.xloc = 0.0F;
            allUnitPanel.yOffset = (float)Settings.HEIGHT - 140.0F * Settings.scale;
            allUnitPanel.defaultDiscardHeight = 28;
            allUnitPanel.discardWidth = 130;
            allUnitPanel.defaultDiscardText = 0.6F;
            allUnitPanel.xlocDiscard = 0.0F;
            allUnitPanel.yOffsetDiscard = (float)Settings.HEIGHT - 140.0F * Settings.scale;
            setBoolean("dynamic-update", false);
            setBoolean("extended-tooltip", true);
            setBoolean("dynamic-text", true);
            setBoolean("frozen-eye", true);
            setInt("draw-width", allUnitPanel.drawWidth);
            setInt("draw-height", allUnitPanel.defaultDrawHeight);
            setFloat("draw-text-size", allUnitPanel.defaultDrawText);
            setFloat("draw-x", allUnitPanel.xloc);
            setFloat("draw-y", allUnitPanel.yOffset);
            setInt("discard-height", allUnitPanel.defaultDiscardHeight);
            setInt("discard-width", allUnitPanel.discardWidth);
            setFloat("discard-text-size", allUnitPanel.defaultDiscardText);
            setFloat("discard-x", allUnitPanel.xlocDiscard);
            setFloat("discard-y", allUnitPanel.yOffsetDiscard);
            this.tooltipButton.toggle.enabled = false;
            this.dynamicButton.toggle.enabled = true;
            this.dynamicTextButton.toggle.enabled = true;
            this.frozenEyeButton.toggle.enabled = true;
            this.dwSlider.setValue((float)getInt("draw-width") / this.dwSlider.multiplier);
            this.dhSlider.setValue((float)getInt("draw-height") / this.dhSlider.multiplier);
            this.dtSlider.setValue(getFloat("draw-text-size"));
            this.diswSlider.setValue((float)getInt("discard-width") / this.diswSlider.multiplier);
            this.dishSlider.setValue((float)getInt("discard-height") / this.dishSlider.multiplier);
            this.distSlider.setValue(getFloat("discard-text-size"));
        });
        settingsPanel.addUIElement(defaultButton);
        this.tooltipButton = new ModLabeledToggleButton("Display full cards in tooltips.", x, y, Settings.CREAM_COLOR, FontHelper.charDescFont, allUnitPanel.extendedTooltips, settingsPanel, (label) -> {
        }, (button) -> {
            allUnitPanel.extendedTooltips = button.enabled;
            setBoolean("extended-tooltip", button.enabled);
        });
        settingsPanel.addUIElement(this.tooltipButton);
        y -= 40.0F;
        this.dynamicButton = new ModLabeledToggleButton("Dynamically change height based on decksize.", x, y, Settings.CREAM_COLOR, FontHelper.charDescFont, allUnitPanel.dynamicUpdate, settingsPanel, (label) -> {
        }, (button) -> {
            allUnitPanel.dynamicUpdate = button.enabled;
            setBoolean("dynamic-update", button.enabled);
        });
        settingsPanel.addUIElement(this.dynamicButton);
        y -= 40.0F;
        this.dynamicTextButton = new ModLabeledToggleButton("Change textsize based on height.", x, y, Settings.CREAM_COLOR, FontHelper.charDescFont, allUnitPanel.dynamicText, settingsPanel, (label) -> {
        }, (button) -> {
            allUnitPanel.dynamicText = button.enabled;
            setBoolean("dynamic-text", button.enabled);
        });
        settingsPanel.addUIElement(this.dynamicTextButton);
        y -= 40.0F;
        this.frozenEyeButton = new ModLabeledToggleButton("Frozen Eye Support", x, y, Settings.CREAM_COLOR, FontHelper.charDescFont, allUnitPanel.frozenEye, settingsPanel, (label) -> {
        }, (button) -> {
            allUnitPanel.frozenEye = button.enabled;
            setBoolean("frozen-eye", button.enabled);
        });
        settingsPanel.addUIElement(this.frozenEyeButton);
        y -= 60.0F;
        ModLabel configLabel = new ModLabel("Draw Deck", x, y, Settings.CREAM_COLOR, settingsPanel, (label) -> {
        });
        settingsPanel.addUIElement(configLabel);
        y -= 40.0F;
        configLabel = new ModLabel("Width", x + 100.0F, y, Settings.CREAM_COLOR, settingsPanel, (label) -> {
        });
        settingsPanel.addUIElement(configLabel);
        this.dwSlider = new ModSlider("", x * 1.5F + 100.0F, y, 250.0F, "", settingsPanel, (slider) -> {
            int val = Math.max(1, Math.round(slider.value * slider.multiplier));
            allUnitPanel.drawWidth = val;
            setInt("draw-width", val);
        });
        this.dwSlider.setValue((float)getInt("draw-width") / this.dwSlider.multiplier);
        settingsPanel.addUIElement(this.dwSlider);
        y -= 40.0F;
        configLabel = new ModLabel("Height", x + 100.0F, y, Settings.CREAM_COLOR, settingsPanel, (label) -> {
        });
        settingsPanel.addUIElement(configLabel);
        this.dhSlider = new ModSlider("", x * 1.5F + 100.0F, y, 50.0F, "", settingsPanel, (slider) -> {
            int val = Math.max(1, Math.round(slider.value * slider.multiplier));
            allUnitPanel.defaultDrawHeight = val;
            setInt("draw-height", val);
        });
        this.dhSlider.setValue((float)getInt("draw-height") / this.dhSlider.multiplier);
        settingsPanel.addUIElement(this.dhSlider);
        y -= 40.0F;
        configLabel = new ModLabel("Text Size", x + 100.0F, y, Settings.CREAM_COLOR, settingsPanel, (label) -> {
        });
        settingsPanel.addUIElement(configLabel);
        this.dtSlider = new ModSlider("", x * 1.5F + 100.0F, y, 10.0F, "", settingsPanel, (slider) -> {
            float val = Math.max(0.01F, slider.value);
            allUnitPanel.defaultDrawText = val;
            setFloat("draw-text-size", val);
        });
        this.dtSlider.setValue(getFloat("draw-text-size"));
        settingsPanel.addUIElement(this.dtSlider);
        y -= 60.0F;
        configLabel = new ModLabel("Discard Deck", x, y, Settings.CREAM_COLOR, settingsPanel, (label) -> {
        });
        settingsPanel.addUIElement(configLabel);
        y -= 40.0F;
        configLabel = new ModLabel("Width", x + 100.0F, y, Settings.CREAM_COLOR, settingsPanel, (label) -> {
        });
        settingsPanel.addUIElement(configLabel);
        this.diswSlider = new ModSlider("", x * 1.5F + 100.0F, y, 250.0F, "", settingsPanel, (slider) -> {
            int val = Math.max(1, Math.round(slider.value * slider.multiplier));
            allUnitPanel.discardWidth = val;
            setInt("discard-width", val);
        });
        this.diswSlider.setValue((float)getInt("discard-width") / this.diswSlider.multiplier);
        settingsPanel.addUIElement(this.diswSlider);
        y -= 40.0F;
        configLabel = new ModLabel("Height", x + 100.0F, y, Settings.CREAM_COLOR, settingsPanel, (label) -> {
        });
        settingsPanel.addUIElement(configLabel);
        this.dishSlider = new ModSlider("", x * 1.5F + 100.0F, y, 50.0F, "", settingsPanel, (slider) -> {
            int val = Math.max(1, Math.round(slider.value * slider.multiplier));
            allUnitPanel.defaultDiscardHeight = val;
            setInt("discard-height", val);
        });
        this.dishSlider.setValue((float)getInt("discard-height") / this.dishSlider.multiplier);
        settingsPanel.addUIElement(this.dishSlider);
        y -= 40.0F;
        configLabel = new ModLabel("Text Size", x + 100.0F, y, Settings.CREAM_COLOR, settingsPanel, (label) -> {
        });
        settingsPanel.addUIElement(configLabel);
        this.distSlider = new ModSlider("", x * 1.5F + 100.0F, y, 10.0F, "", settingsPanel, (slider) -> {
            float val = Math.max(0.01F, slider.value);
            allUnitPanel.defaultDiscardText = val;
            setFloat("discard-text-size", val);
        });
        this.distSlider.setValue(getFloat("discard-text-size"));
        settingsPanel.addUIElement(this.distSlider);
        Texture badgeTexture = ImageMaster.loadImage(epsilonModHelper.assetPath("img/UI/Config/Decktracker-ModBadge.png"));
        BaseMod.registerModBadge(badgeTexture, "StSDeckTracker", "Girogore", "Provides an in-game deck tracker", settingsPanel);
    }
}
