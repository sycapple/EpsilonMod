//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EpsilonMod.actions;

import EpsilonMod.powers.abstractEpsilonPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class applyPowerToAllEnemyAction extends AbstractGameAction {
    private AbstractPower powerToApply;
    private boolean isFast;
    private AbstractGameAction.AttackEffect effect;

    public applyPowerToAllEnemyAction(AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
        this.setValues((AbstractCreature) null, source, stackAmount);
        this.powerToApply = powerToApply;
        this.isFast = isFast;
        this.effect = effect;
    }

    public applyPowerToAllEnemyAction(AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast) {
        this(source, powerToApply, stackAmount, isFast, AttackEffect.NONE);
    }

    public applyPowerToAllEnemyAction(AbstractCreature source, AbstractPower powerToApply, int stackAmount) {
        this(source, powerToApply, stackAmount, false, AttackEffect.NONE);
    }

    public void update() {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            AbstractPower p = ((abstractEpsilonPower) powerToApply).copy();
            p.owner = monster;
            if (monster != null) {
                this.addToTop(new ApplyPowerAction(monster, this.source, p, this.amount, this.isFast, this.effect));
            }
        }
        this.isDone = true;
    }
}
