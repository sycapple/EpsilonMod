package EpsilonMod.actions;

import EpsilonMod.cards.abstracts.abstractEpsilonUnit;
import EpsilonMod.util.unitPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class allUnitAttackAction extends AbstractGameAction {

    public static final Logger logger = LogManager.getLogger(allUnitAttackAction.class.getSimpleName());

    public allUnitAttackAction() {
    }

    public void update() {
        for (AbstractCard unit : unitPileManager.getUnitPile().group) {
            logger.info("now " + unit.name + " attack, " + unit.magicNumber + " times remain");
            ((abstractEpsilonUnit) unit).attack();
            logger.info(unit.name + " attack over, " + unit.magicNumber + " times remain");
        }
        this.addToBot(new checkUnitIfZeroAction());
        this.isDone = true;
    }
}
