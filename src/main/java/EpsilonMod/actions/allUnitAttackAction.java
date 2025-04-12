package EpsilonMod.actions;

import EpsilonMod.cards.abstracts.abstractEpsilonUnit;
import EpsilonMod.util.epsilonModHelper;
import EpsilonMod.util.unitPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class allUnitAttackAction extends AbstractGameAction {


    public allUnitAttackAction() {
    }

    public void update() {
        for (AbstractCard unit : unitPileManager.getUnitPile().group) {
            epsilonModHelper.logger.info("now " + unit.name + " attack, " + unit.magicNumber + " times remain");
            ((abstractEpsilonUnit) unit).attack();
            epsilonModHelper.logger.info(unit.name + " attack over, " + unit.magicNumber + " times remain");
        }
        this.addToBot(new checkUnitIfZeroAction());
        this.isDone = true;
    }
}
