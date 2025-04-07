//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EpsilonMod.panels;

import basemod.BaseMod;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDeathSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import EpsilonMod.util.MutablePair;


public class allUnitPanel implements PostInitializeSubscriber, OnStartBattleSubscriber, PostBattleSubscriber, PostDeathSubscriber {
    public static final Logger logger = LogManager.getLogger(allUnitPanel.class.getName());
    static ArrayList<perUnitPanel> drawList;
    static ArrayList<perUnitPanel> discardList;
    public static float RELICLINE;
    public static boolean extendedTooltips;
    public static boolean dynamicUpdate;
    public static boolean dynamicText;
    public static boolean frozenEye;
    public static int drawHeight;
    public static int discardHeight;
    public static int defaultDrawHeight;
    public static int defaultDiscardHeight;
    public static int drawWidth;
    public static int discardWidth;
    public static float drawTextSize;
    public static float discardTextSize;
    public static float defaultDiscardText;
    public static float defaultDrawText;
    public static float xloc;
    public static float xlocDiscard;
    public static float yOffset;
    public static float yOffsetDiscard;
    public static final float screenArea = 650.0F;
    public static float previousxloc;
    public static float previousyOffset;
    public static float previousxlocDiscard;
    public static float previousyOffsetDiscard;

    public allUnitPanel() {
        drawList = new ArrayList();
        discardList = new ArrayList();
        BaseMod.subscribe(this);
    }

    public void receivePostInitialize() {
        RELICLINE = (float) Settings.HEIGHT - 140.0F * Settings.scale;
    }

    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        Update();
        if (xloc != previousxloc) {
            modConfig.setFloat("draw-x", xloc);
            previousxloc = xloc;
        }

        if (yOffset != previousyOffset) {
            modConfig.setFloat("draw-y", yOffset);
            previousyOffset = yOffset;
        }

        if (xlocDiscard != previousxlocDiscard) {
            modConfig.setFloat("discard-x", xlocDiscard);
            previousxlocDiscard = xlocDiscard;
        }

        if (yOffsetDiscard != previousyOffsetDiscard) {
            modConfig.setFloat("discard-y", yOffsetDiscard);
            previousyOffsetDiscard = yOffsetDiscard;
        }

    }

    public void receivePostBattle(AbstractRoom abstractRoom) {
        ResetList(true);
        ResetList(false);
    }

    public void receivePostDeath() {
        ResetList(true);
        ResetList(false);
    }

    private static void ResetList(boolean discardDeck) {
        Iterator var1;
        perUnitPanel dt;
        if (discardDeck) {
            var1 = discardList.iterator();

            while (var1.hasNext()) {
                dt = (perUnitPanel) var1.next();
                dt.Remove();
            }

            discardList.clear();
        } else {
            var1 = drawList.iterator();

            while (var1.hasNext()) {
                dt = (perUnitPanel) var1.next();
                dt.Remove();
            }

            drawList.clear();
        }

    }

    public static void Update() {
        try {
            AbstractDungeon.getCurrRoom();
        } catch (Exception var1) {
            logger.error("You played a card while not in combat?");
            return;
        }

        makeDeckList(false);
        makeDeckList(true);
    }

    private static void makeDeckList(boolean discardDeck) {
        ResetList(discardDeck);
        TextureAtlas.AtlasRegion energyOrbAR = AbstractDungeon.player.getOrb();
        int screenSpace;
        if (discardDeck) {
            screenSpace = (int) (650.0F * Settings.scale - (RELICLINE - yOffsetDiscard));
        } else {
            screenSpace = (int) (650.0F * Settings.scale - (RELICLINE - yOffset));
        }

        int currentHeight;
        float currentText;
        if (discardDeck) {
            currentHeight = defaultDiscardHeight;
            currentText = defaultDiscardText;
        } else {
            currentHeight = defaultDrawHeight;
            currentText = defaultDrawText;
        }

        TreeMap ret;
        int cardTypes;
        if (discardDeck) {
            ret = GetCards(AbstractDungeon.player.discardPile.group);
            cardTypes = ret.entrySet().size();
        } else {
            cardTypes = AbstractDungeon.player.drawPile.group.size();
            ret = null;
            if (!AbstractDungeon.player.hasRelic("Frozen Eye") || !frozenEye) {
                ret = GetCards(AbstractDungeon.player.drawPile.group);
                cardTypes = ret.entrySet().size();
            }
        }

        if (dynamicUpdate) {
            while (cardTypes > screenSpace / currentHeight && currentHeight >= 18) {
                --currentHeight;
            }
        }

        if (dynamicText) {
            currentText = GetTextSize(currentHeight);
        }

        if (discardDeck) {
            discardHeight = currentHeight;
            discardTextSize = currentText;
        } else {
            drawHeight = currentHeight;
            drawTextSize = currentText;
        }

        cardTypes = -1;
        int y = 0;
        TextureRegion TROrb;
        if (AbstractDungeon.player.hasRelic("Frozen Eye") && !discardDeck && frozenEye) {
            ArrayList<AbstractCard> deck = (ArrayList) AbstractDungeon.player.drawPile.group.clone();
            Collections.reverse(deck);
            Iterator var17 = deck.iterator();

            while (var17.hasNext()) {
                AbstractCard card = (AbstractCard) var17.next();
                ++cardTypes;
                if (cardTypes > screenSpace / currentHeight && cardTypes != 0) {
                    break;
                }

                --y;
                switch (card.color) {
                    case CURSE:
                    case COLORLESS:
                        TROrb = new TextureRegion(ImageMaster.CARD_GRAY_ORB_L, 0, 0, ImageMaster.CARD_GRAY_ORB_L.packedWidth, ImageMaster.CARD_GRAY_ORB_L.packedHeight);
                        break;
                    default:
                        TROrb = new TextureRegion(energyOrbAR, 0, 0, energyOrbAR.packedWidth, energyOrbAR.packedHeight);
                }

                float yloc = (float) (y * drawHeight) * 1.15F * Settings.scale + yOffset;
                perUnitPanel dtCard = new perUnitPanel(card, TROrb, xloc, yloc, -1, discardDeck);
                drawList.add(dtCard);
            }
        } else {
            Iterator var9 = ret.entrySet().iterator();

            while (var9.hasNext()) {
                Map.Entry<String, MutablePair<AbstractCard, Integer>> entry = (Map.Entry) var9.next();
                ++cardTypes;
                if (cardTypes > screenSpace / currentHeight && cardTypes != 0) {
                    break;
                }

                int amount = (Integer) ((MutablePair) entry.getValue()).getRight();
                --y;
                AbstractCard card = (AbstractCard) ((MutablePair) entry.getValue()).getLeft();
                switch (card.color) {
                    case CURSE:
                    case COLORLESS:
                        TROrb = new TextureRegion(ImageMaster.CARD_GRAY_ORB_L, 0, 0, ImageMaster.CARD_GRAY_ORB_L.packedWidth, ImageMaster.CARD_GRAY_ORB_L.packedHeight);
                        break;
                    default:
                        TROrb = new TextureRegion(energyOrbAR, 0, 0, energyOrbAR.packedWidth, energyOrbAR.packedHeight);
                }

                perUnitPanel dtCard;
                float yloc;
                if (discardDeck) {
                    yloc = (float) (y * discardHeight) * 1.15F * Settings.scale + yOffsetDiscard;
                    float x = (float) Settings.WIDTH - (float) (discardWidth + discardHeight) * Settings.scale - xlocDiscard;
                    dtCard = new perUnitPanel(card, TROrb, x, yloc, amount, discardDeck);
                    discardList.add(dtCard);
                } else {
                    yloc = (float) (y * drawHeight) * 1.15F * Settings.scale + yOffset;
                    dtCard = new perUnitPanel(card, TROrb, xloc, yloc, amount, discardDeck);
                    drawList.add(dtCard);
                }
            }
        }

    }

    static TreeMap<String, MutablePair<AbstractCard, Integer>> GetCards(ArrayList<AbstractCard> deck) {
        TreeMap<String, MutablePair<AbstractCard, Integer>> ret = new TreeMap();
        Iterator var2 = deck.iterator();

        while (var2.hasNext()) {
            AbstractCard card = (AbstractCard) var2.next();
            String name = card.name;
            if (ret.containsKey(name)) {
                ((MutablePair) ret.get(name)).setRight((Integer) ((MutablePair) ret.get(name)).getRight() + 1);
            } else {
                ret.put(name, new MutablePair(card, 1));
            }
        }

        return ret;
    }

    public static void MoveAll(boolean discard) {
        int y = 0;
        Iterator var2;
        perUnitPanel dtCard;
        if (discard) {
            var2 = discardList.iterator();

            while (var2.hasNext()) {
                dtCard = (perUnitPanel) var2.next();
                --y;
                float x = (float) Settings.WIDTH - (float) (discardWidth + discardHeight) * Settings.scale - xlocDiscard;
                dtCard.Move(x, (float) (y * discardHeight) * 1.15F * Settings.scale + yOffsetDiscard);
            }
        } else {
            var2 = drawList.iterator();

            while (var2.hasNext()) {
                dtCard = (perUnitPanel) var2.next();
                --y;
                dtCard.Move(xloc, (float) (y * drawHeight) * 1.15F * Settings.scale + yOffset);
            }
        }

    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    static float GetTextSize(int currentHeight) {
        if (currentHeight < 10) {
            return 0.2F;
        } else if (currentHeight < 13) {
            return 0.3F;
        } else if (currentHeight < 14) {
            return 0.4F;
        } else if (currentHeight < 16) {
            return 0.5F;
        } else if (currentHeight < 18) {
            return 0.6F;
        } else if (currentHeight < 26) {
            return 0.7F;
        } else if (currentHeight < 31) {
            return 0.8F;
        } else {
            return currentHeight < 40 ? 0.9F : 1.0F;
        }
    }

    @SpirePatch(
            clz = GameActionManager.class,
            method = "update"
    )
    public static class PostActionResolve {
        public PostActionResolve() {
        }

        @SpireInsertPatch(
                rloc = 9,
                localvars = {}
        )
        public static void Insert(GameActionManager __instance) {
            allUnitPanel.Update();
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "onCardDrawOrDiscard"
    )
    public static class PostCardResolve {
        public PostCardResolve() {
        }

        public static void Postfix(AbstractPlayer __instance) {
            allUnitPanel.Update();
        }
    }
}
