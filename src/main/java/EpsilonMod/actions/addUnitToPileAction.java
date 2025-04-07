package EpsilonMod.actions;

import EpsilonMod.util.unitPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class addUnitToPileAction extends AbstractGameAction {
    private AbstractCard c;

    public addUnitToPileAction(AbstractCard C) {
        this.c = C.makeStatEquivalentCopy();
    }

    public void update() {
        unitPileManager.addUnit(this.c);
        this.isDone = true;
    }
}
