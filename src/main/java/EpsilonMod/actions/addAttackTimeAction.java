package EpsilonMod.actions;

import EpsilonMod.cards.unit.brute;
import EpsilonMod.util.unitPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;


public class addAttackTimeAction extends AbstractGameAction {
    private final int amt;

    public addAttackTimeAction(int amt) {
        this.amt = amt;
    }

    public void update() {
        CardGroup gp = unitPileManager.getUnitPile();
        for (AbstractCard unit : gp.group) {
            if (unit.cardID == brute.ID)
                unit.magicNumber += amt;
            unit.magicNumber += amt;
        }
        this.isDone = true;
    }
}
