package EpsilonMod.actions;

import EpsilonMod.cards.unit.brute;
import EpsilonMod.util.unitPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.CardGroup;


public class transformUnitToBrute extends AbstractGameAction {

    public transformUnitToBrute() {

    }

    public void update() {
        CardGroup gp = unitPileManager.getUnitPile();
        int unitCnt = unitPileManager.getUnitCnt();
        gp.clear();
        this.addToBot(new addUnitToPileAction(new brute(), unitCnt));
        this.isDone = true;
    }
}
