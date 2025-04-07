//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EpsilonMod.panels;

import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.interfaces.PreUpdateSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class perUnitPanel implements RenderSubscriber, PreUpdateSubscriber {
    private AbstractCard card;
    private Hitbox hb;
    boolean extendedTooltips;
    float yloc;
    float cardSizeWidth;
    float cardSizeHeight;
    int amount;
    private float width;
    private float height;
    private float xloc;
    private TextureRegion orbTexture;
    private String cost;
    private String name;
    private String description;
    private BitmapFont titleFont;
    private float textSize;
    private boolean canRender = true;
    private TextureRegion portrait;
    private float startLocX;
    private float startLocY;
    public static final Logger logger = LogManager.getLogger(allUnitPanel.class.getName());

    public perUnitPanel(AbstractCard card, TextureRegion orbTR, float x, float y, int amount) {
        this.card = card.makeStatEquivalentCopy();
        this.card.drawScale = 0.7F;
        this.amount = amount;
        this.orbTexture = orbTR;
        if (card.cost == -1) {
            this.cost = "X";
        } else if (card.cost < 0) {
            this.cost = "-";
        } else {
            this.cost = Integer.toString(card.cost);
        }

        this.titleFont = FontHelper.cardTitleFont;
        this.extendedTooltips = allUnitPanel.extendedTooltips;
        this.width = (float) allUnitPanel.drawWidth * Settings.scale;
        this.height = (float) allUnitPanel.unitPanelHeight * Settings.scale;
        this.textSize = allUnitPanel.unitTextSize;


        this.name = card.name;
        if (this.name.length() > (int) (this.width / (this.textSize * 10.0F)) / 2) {
            this.name = this.name.substring(0, (int) (this.width / (this.textSize * 10.0F)) / 2);
            if (card.name.endsWith("+") && !this.name.endsWith("+")) {
                this.name = this.name + "+";
            }
        }

        this.xloc = x;
        this.yloc = y;
        this.hb = new Hitbox(this.xloc, this.yloc - this.height * 0.25F, this.width, this.height);
        this.cardSizeWidth = this.card.hb.width / this.card.drawScale;
        this.cardSizeHeight = this.card.hb.height / this.card.drawScale;

        try {
            TextureAtlas.AtlasRegion AR = !UnlockTracker.betaCardPref.getBoolean(card.cardID, false) && !Settings.PLAYTESTER_ART_MODE ? card.portrait : card.jokePortrait;
            this.portrait = new TextureRegion(AR, 0, AR.packedHeight / 3, AR.packedWidth, AR.packedHeight / 3);
        } catch (Exception var8) {
            this.portrait = new TextureRegion(ImageMaster.CARD_LOCKED_ATTACK);
        }

        BaseMod.subscribe(this);
    }

    public void Remove() {
        BaseMod.unsubscribe(this);
    }

    public void receivePreUpdate() {
        this.hb.update();
        if (this.hb.justHovered) {
            try {
                this.UpdateDescription();
            } catch (Exception var5) {
                this.description = this.card.rawDescription;
            }
        }

        if (this.hb.hovered && InputHelper.justClickedLeft) {
            this.startLocX = (float) InputHelper.mX;
            this.startLocY = (float) InputHelper.mY;
            this.hb.clickStarted = true;
        }

        if (this.hb.clickStarted) {
            float translateX = this.startLocX - (float) InputHelper.mX;
            float translateY = this.startLocY - (float) InputHelper.mY;
            this.startLocX = (float) InputHelper.mX;
            this.startLocY = (float) InputHelper.mY;
            float newValueX;
            float newValueY;
            newValueX = this.xloc - translateX;
            newValueY = allUnitPanel.yOffset - translateY;
            newValueX = allUnitPanel.clamp(newValueX, 0.0F, (float) Settings.WIDTH - (this.width + this.height * 2.0F) * Settings.scale);
            newValueY = allUnitPanel.clamp(newValueY, allUnitPanel.RELICLINE - 650.0F * Settings.scale, allUnitPanel.RELICLINE);
            allUnitPanel.xloc = newValueX;
            allUnitPanel.yOffset = newValueY;
            allUnitPanel.MoveAll();
        }

    }

    public void receiveRender(SpriteBatch sb) {
        try {
            AbstractDungeon.getCurrRoom();
        } catch (Exception var4) {
            BaseMod.unsubscribeLater(this);
            return;
        }
        if (this.hb.hovered && !this.hb.clickStarted) {
            float tooltipX;
            tooltipX = this.xloc + this.width + this.height / 2.0F + 15.0F;
            if (this.extendedTooltips) {
                this.card.current_x = tooltipX + this.card.drawScale * this.cardSizeWidth / 2.0F;
                this.card.current_y = this.yloc - this.card.drawScale * this.cardSizeHeight / 2.0F + this.height;
                this.card.render(sb);
            } else {
                TipHelper.renderGenericTip(tooltipX, this.yloc, this.card.name, this.description);
            }
        }

        sb.setColor(Color.WHITE.cpy());

        try {
            sb.draw(this.orbTexture, this.xloc + this.width, this.yloc, this.height, this.height);
            sb.draw(this.portrait, this.xloc, this.yloc, this.width, this.height);
        } catch (Exception var3) {
            this.canRender = false;
        }

        this.titleFont.getData().setScale(this.textSize + 0.2F);
        if (this.amount != -1) {
            FontHelper.renderFont(sb, this.titleFont, Integer.toString(this.amount), this.xloc + 3.0F, this.yloc + this.height * 0.8F, Color.GOLD);
        }

        this.titleFont.getData().setScale(this.textSize);
        Color nameColor = Color.WHITE;
        if (this.name.endsWith("+")) {
            nameColor = Color.GREEN;
        }

        FontHelper.renderFont(sb, this.titleFont, this.name, this.xloc + 30.0F * this.textSize, this.yloc + this.height * 0.8F, nameColor);
        this.titleFont.getData().setScale(this.textSize + 0.1F);
        if (this.card.cost == 1) {
            FontHelper.renderFont(sb, this.titleFont, this.cost, this.xloc + this.width + this.height * 0.35F, this.yloc + this.height * 0.8F, Color.WHITE);
        } else {
            FontHelper.renderFont(sb, this.titleFont, this.cost, this.xloc + this.width + this.height * 0.35F - 2.0F, this.yloc + this.height * 0.8F, Color.WHITE);
        }

    }

    public void Move(float newX, float newY) {
        this.xloc = newX;
        this.yloc = newY;
        this.hb.translate(newX, newY - this.height * 0.25F);
    }

    private String getDynamicValue(String key) {
        String value = null;
        if (key.length() == 1) {
            switch (key.charAt(0)) {
                case 'B':
                    if (!this.card.isBlockModified) {
                        return Integer.toString(this.card.baseBlock);
                    } else {
                        if (this.card.block >= this.card.baseBlock) {
                            return "[#7fff00]" + Integer.toString(this.card.block) + "[]";
                        }

                        return "[#ff6563]" + Integer.toString(this.card.block) + "[]";
                    }
                case 'D':
                    if (!this.card.isDamageModified) {
                        return Integer.toString(this.card.baseDamage);
                    } else {
                        if (this.card.damage >= this.card.baseDamage) {
                            return "[#7fff00]" + Integer.toString(this.card.damage) + "[]";
                        }

                        return "[#ff6563]" + Integer.toString(this.card.damage) + "[]";
                    }
                case 'M':
                    if (!this.card.isMagicNumberModified) {
                        return Integer.toString(this.card.baseMagicNumber);
                    } else {
                        if (this.card.magicNumber >= this.card.baseMagicNumber) {
                            return "[#7fff00]" + Integer.toString(this.card.magicNumber) + "[]";
                        }

                        return "[#ff6563]" + Integer.toString(this.card.magicNumber) + "[]";
                    }
                default:
//                    logger.info("KEY: " + key);
                    return Integer.toString(-99);
            }
        } else {
            DynamicVariable dv = (DynamicVariable) BaseMod.cardDynamicVariableMap.get(key);
            if (dv != null) {
                if (dv.isModified(this.card)) {
                    if (dv.value(this.card) >= dv.baseValue(this.card)) {
                        value = "[#" + dv.getIncreasedValueColor().toString() + "]" + Integer.toString(dv.value(this.card)) + "[]";
                    } else {
                        value = "[#" + dv.getDecreasedValueColor().toString() + "]" + Integer.toString(dv.value(this.card)) + "[]";
                    }
                } else {
                    value = Integer.toString(dv.baseValue(this.card));
                }
            }

//            logger.info(key + " is " + value);
            return value;
        }
    }

    public void UpdateDescription() {
        this.description = "";
        this.card.initializeDescription();

        for (int i = 0; i < this.card.description.size(); ++i) {
            String descriptionFragment = ((DescriptionLine) this.card.description.get(i)).getText();
//            logger.info("descriptionFragment is " + descriptionFragment);
            descriptionFragment = descriptionFragment.replace('!', ' ');
            String[] words = descriptionFragment.split(" ");
            for (String word : words) {
//                logger.info("word is " + word);
                if (word.length() == 1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') {
                    // 替换为动态值
                    this.description += this.getDynamicValue(word);
                } else {
                    // 直接添加单词
                    this.description += word;
                }
                // 添加空格分隔单词（最后一个单词除外）
                if (i < words.length - 1) {
                    this.description += " ";
                }
            }
        }
    }
}
