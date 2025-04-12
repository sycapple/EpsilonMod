package EpsilonMod.relic;

import EpsilonMod.actions.allUnitAttackAction;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class epsilonEmblem extends abstractEpsilonModRelic {
    //遗物ID（此处的ModHelper在“04 - 本地化”中提到）
    public static final String ID = epsilonModHelper.makeID(epsilonEmblem.class.getSimpleName());
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public epsilonEmblem() {
        super(ID, true, RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

    }

    @Override
    public void atBattleStart() {

    }

    @Override
    public void onPlayerEndTurn() {
        this.addToBot(new allUnitAttackAction());
    }

    @Override
    public void atTurnStart() {

    }

    public AbstractRelic makeCopy() {
        return new epsilonEmblem();
    }
}