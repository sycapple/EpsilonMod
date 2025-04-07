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
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
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
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import EpsilonMod.util.MutablePair;

@SpireInitializer
public class allUnitPanel implements PostInitializeSubscriber, OnStartBattleSubscriber, PostBattleSubscriber, PostDeathSubscriber {
    public static final Logger logger = LogManager.getLogger(allUnitPanel.class.getName());
    static ArrayList<perUnitPanel> unitList;
    public static float RELICLINE;
    public static boolean extendedTooltips;
    public static boolean dynamicUpdate;
    public static boolean dynamicText;
    public static boolean frozenEye;
    public static int unitPanelHeight;
    public static int defaultDrawHeight;
    public static int drawWidth;
    public static float unitTextSize;
    public static float defaultDrawText;
    public static float xloc;
    public static float yOffset;
    public static final float screenArea = 650.0F;
    public static float previousxloc;
    public static float previousyOffset;

    public allUnitPanel() {
        unitList = new ArrayList();
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new allUnitPanel();
        new modConfig();
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
    }

    public void receivePostBattle(AbstractRoom abstractRoom) {
        ResetList();
    }

    public void receivePostDeath() {
        ResetList();
    }

    private static void ResetList() {
        Iterator var1;
        perUnitPanel dt;
        var1 = unitList.iterator();
        while (var1.hasNext()) {
            dt = (perUnitPanel) var1.next();
            dt.Remove();
        }
        unitList.clear();
    }

    public static void Update() {
        try {
            AbstractDungeon.getCurrRoom();
        } catch (Exception var1) {
            logger.error("You played a card while not in combat?");
            return;
        }
        makeDeckList();
    }

    private static void makeDeckList() {
        ResetList(); //重置卡槽
        TextureAtlas.AtlasRegion energyOrbAR = AbstractDungeon.player.getOrb(); //获取能量材质
        int screenSpace;
        int currentHeight;
        float currentText;
        TreeMap ret;
        int cardTypes;
        //屏幕参数设置
        screenSpace = (int) (650.0F * Settings.scale - (RELICLINE - yOffset));
        currentHeight = defaultDrawHeight;
        currentText = defaultDrawText;
        ret = null;
        ret = GetCards(AbstractDungeon.player.drawPile.group); //todo 从卡槽列表拿牌
        cardTypes = ret.entrySet().size();
        if (dynamicUpdate) {
            while (cardTypes > screenSpace / currentHeight && currentHeight >= 18) {
                --currentHeight;
            }
        }

        if (dynamicText) {
            currentText = GetTextSize(currentHeight);
        }

        unitPanelHeight = currentHeight;
        unitTextSize = currentText;


        cardTypes = -1;
        int y = 0;
        TextureRegion TROrb;
        Iterator perUnitPanel = ret.entrySet().iterator();

        while (perUnitPanel.hasNext()) {
            Map.Entry<String, MutablePair<AbstractCard, Integer>> entry = (Map.Entry) perUnitPanel.next();
            ++cardTypes;
            if (cardTypes > screenSpace / currentHeight && cardTypes != 0) {
                break;
            }

            int amount = (Integer) ((MutablePair) entry.getValue()).getRight();
            --y;
            AbstractCard card = (AbstractCard) ((MutablePair) entry.getValue()).getLeft();

            // 设置能量贴图
            switch (card.color) {
                case CURSE:
                case COLORLESS:
                    TROrb = new TextureRegion(ImageMaster.CARD_GRAY_ORB_L, 0, 0, ImageMaster.CARD_GRAY_ORB_L.packedWidth, ImageMaster.CARD_GRAY_ORB_L.packedHeight);
                    break;
                default:
                    TROrb = new TextureRegion(energyOrbAR, 0, 0, energyOrbAR.packedWidth, energyOrbAR.packedHeight);
            }

            //添加卡牌面板
            perUnitPanel dtCard;
            float yloc;
            yloc = (float) (y * unitPanelHeight) * 1.15F * Settings.scale + yOffset;
            dtCard = new perUnitPanel(card, TROrb, xloc, yloc, amount);
            unitList.add(dtCard);
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

    public static void MoveAll() {
        int y = 0;
        Iterator var2;
        perUnitPanel dtCard;
        var2 = unitList.iterator();
        while (var2.hasNext()) {
            dtCard = (perUnitPanel) var2.next();
            --y;
            dtCard.Move(xloc, (float) (y * unitPanelHeight) * 1.15F * Settings.scale + yOffset);
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
