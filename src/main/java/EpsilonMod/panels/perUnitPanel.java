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
    private boolean discardDeck;
    private float textSize;
    private boolean canRender = true;
    private TextureRegion portrait;
    private float startLocX;
    private float startLocY;
    public static final Logger logger = LogManager.getLogger(allUnitPanel.class.getName());

    public perUnitPanel(AbstractCard card, TextureRegion orbTR, float x, float y, int amount, boolean discard) {
        this.card = card.makeStatEquivalentCopy();
        this.card.drawScale = 0.7F;
        this.discardDeck = discard;
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
        if (this.discardDeck) {
            this.width = (float)allUnitPanel.discardWidth * Settings.scale;
            this.height = (float)allUnitPanel.discardHeight * Settings.scale;
            this.textSize = allUnitPanel.discardTextSize;
        } else {
            this.width = (float)allUnitPanel.drawWidth * Settings.scale;
            this.height = (float)allUnitPanel.drawHeight * Settings.scale;
            this.textSize = allUnitPanel.drawTextSize;
        }

        this.name = card.name;
        if (this.name.length() > (int)(this.width / (this.textSize * 10.0F)) / 2) {
            this.name = this.name.substring(0, (int)(this.width / (this.textSize * 10.0F)) / 2);
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
            this.startLocX = (float)InputHelper.mX;
            this.startLocY = (float)InputHelper.mY;
            this.hb.clickStarted = true;
        }

        if (this.hb.clickStarted) {
            float translateX = this.startLocX - (float)InputHelper.mX;
            float translateY = this.startLocY - (float)InputHelper.mY;
            this.startLocX = (float)InputHelper.mX;
            this.startLocY = (float)InputHelper.mY;
            float newValueX;
            float newValueY;
            if (this.discardDeck) {
                newValueX = allUnitPanel.xlocDiscard + translateX;
                newValueY = allUnitPanel.yOffsetDiscard - translateY;
                newValueX = allUnitPanel.clamp(newValueX, 0.0F, (float)Settings.WIDTH - (this.width + this.height * 2.0F) * Settings.scale);
                newValueY = allUnitPanel.clamp(newValueY, allUnitPanel.RELICLINE - 650.0F * Settings.scale, allUnitPanel.RELICLINE);
                allUnitPanel.xlocDiscard = newValueX;
                allUnitPanel.yOffsetDiscard = newValueY;
            } else {
                newValueX = this.xloc - translateX;
                newValueY = allUnitPanel.yOffset - translateY;
                newValueX = allUnitPanel.clamp(newValueX, 0.0F, (float)Settings.WIDTH - (this.width + this.height * 2.0F) * Settings.scale);
                newValueY = allUnitPanel.clamp(newValueY, allUnitPanel.RELICLINE - 650.0F * Settings.scale, allUnitPanel.RELICLINE);
                allUnitPanel.xloc = newValueX;
                allUnitPanel.yOffset = newValueY;
            }

            allUnitPanel.MoveAll(this.discardDeck);
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
            if (this.discardDeck) {
                tooltipX = this.xloc - (190.0F + this.height + this.width) * Settings.scale;
            } else {
                tooltipX = this.xloc + this.width + this.height / 2.0F + 15.0F;
            }

            if (this.extendedTooltips) {
                if (this.discardDeck) {
                    this.card.current_x = this.xloc - (this.card.drawScale * this.cardSizeWidth / 2.0F + this.height / 2.0F);
                } else {
                    this.card.current_x = tooltipX + this.card.drawScale * this.cardSizeWidth / 2.0F;
                }

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
                    logger.info("KEY: " + key);
                    return Integer.toString(-99);
            }
        } else {
            DynamicVariable dv = (DynamicVariable)BaseMod.cardDynamicVariableMap.get(key);
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

            logger.info(key + " is " + value);
            return value;
        }
    }

    public void UpdateDescription() {
        this.description = "";
        boolean firstWord = true;
        this.card.initializeDescription();
        String descriptionFragment = "";

        for(int i = 0; i < this.card.description.size(); ++i) {
            descriptionFragment = ((DescriptionLine)this.card.description.get(i)).getText();
            String[] var4 = descriptionFragment.split(" ");
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String word = var4[var6];
                if (firstWord) {
                    firstWord = false;
                } else {
                    this.description = this.description + " ";
                }

                String key;
                if (word.length() > 0 && word.charAt(0) == '*') {
                    word = word.substring(1);
                    key = "";
                    if (word.length() > 1 && !Character.isLetter(word.charAt(word.length() - 2))) {
                        key = key + word.charAt(word.length() - 2);
                        word = word.substring(0, word.length() - 2);
                        key = key + ' ';
                    }

                    this.description = this.description + word;
                    this.description = this.description + key;
                } else if (word.length() > 0 && word.charAt(0) == '!') {
                    key = "";

                    for(int j = 1; j < word.length(); ++j) {
                        if (word.charAt(j) == '!') {
                            this.description = this.description + this.getDynamicValue(key);
                            this.description = this.description + word.substring(j + 1);
                        } else {
                            key = key + word.charAt(j);
                        }
                    }
                } else {
                    this.description = this.description + word;
                }
            }
        }

    }
}
