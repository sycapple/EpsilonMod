package EpsilonMod.actions;

import EpsilonMod.util.unitPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;


public class addUnitToPileAction extends AbstractGameAction {
    private AbstractCard c;
    private int amt;

    public addUnitToPileAction(AbstractCard C, int amt) {
        this.c = C;
        this.amt = amt;
    }

    public void update() {
        for (int i = 0; i < amt; i++)
            unitPileManager.addUnit(this.c.makeStatEquivalentCopy());
        this.isDone = true;
    }
}
