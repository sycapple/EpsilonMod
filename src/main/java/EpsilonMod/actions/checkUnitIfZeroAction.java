package EpsilonMod.actions;

import EpsilonMod.util.unitPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import java.util.ArrayList;

public class checkUnitIfZeroAction extends AbstractGameAction {
    public checkUnitIfZeroAction() {
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> waitingToRemove = new ArrayList<AbstractCard>();
        CardGroup gp = unitPileManager.getUnitPile();
        for (AbstractCard unit : gp.group) {
            if (unit.magicNumber <= 0)
                waitingToRemove.add(unit);
        }
        for (AbstractCard unit : waitingToRemove) {
            gp.moveToExhaustPile(unit);
        }
        this.isDone = true;
    }
}
