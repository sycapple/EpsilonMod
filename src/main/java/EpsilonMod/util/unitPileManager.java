package EpsilonMod.util;


import EpsilonMod.patches.playerHasUnitPilePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;


public class unitPileManager {
    public unitPileManager() {
    }


    public static void sayIsFull() {
        AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(epsilonModHelper.makeID("AddCardToUnitPileAction")).TEXT[0], true));
    }

    public static void addMaxUnit(int a) {
        playerHasUnitPilePatch.unitPileField.maxUnit.set(AbstractDungeon.player, Math.min(getMaxUnit() + a, playerHasUnitPilePatch.maxUnitAvailable));
    }

    public static void resetMaxUnit() {
        playerHasUnitPilePatch.unitPileField.maxUnit.set(AbstractDungeon.player, playerHasUnitPilePatch.maxUnitInitial);
    }

    public static CardGroup getUnitPile() {
        return (CardGroup) playerHasUnitPilePatch.unitPileField.unitPile.get(AbstractDungeon.player);
    }


    public static Integer getMaxUnit() {
        return (Integer) playerHasUnitPilePatch.unitPileField.maxUnit.get(AbstractDungeon.player);
    }


    public static void addUnit(AbstractCard c) {
        CardGroup mp = unitPileManager.getUnitPile();
        if (mp.size() >= unitPileManager.getMaxUnit()) {
            AbstractCard lc = mp.group.get(unitPileManager.getMaxUnit() - 1);
            mp.moveToExhaustPile(lc);
            System.out.println(c.name + "has add");
        }
        mp.group.add(0, c);
    }

    public static Integer getUnitCnt() {
        return getUnitPile().size();
    }
}
