package EpsilonMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class playerHasUnitPilePatch {
    public static CardGroup.CardGroupType UNIT_PILE;
    public static Integer maxUnitAvailable = 50;
    public static Integer maxUnitInitial = 10;

    public playerHasUnitPilePatch() {
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "<class>"
    )
    public static class unitPileField {
        //ConstructionPile
        //maxConstruction
        //上面这两个东西是hashmap,键是AbstractDungeon.player,就是当前玩家
        //CardGroup
        //这个好像是用来管理一组卡组的
        //卡片都放在CardGroup.group里面,类型是ArrayList
        public static SpireField<CardGroup> unitPile = new SpireField(() -> {
            return new CardGroup(playerHasUnitPilePatch.UNIT_PILE);
        });
        public static SpireField<Integer> maxUnit = new SpireField(() -> {
            return playerHasUnitPilePatch.maxUnitInitial;
        });


        public unitPileField() {
        }

    }

}
