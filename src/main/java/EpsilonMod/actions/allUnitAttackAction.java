package EpsilonMod.actions;

import EpsilonMod.modCore.epsilonMod;
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
            switch (unit.target) {
                case ALL_ENEMY:
                    unit.use(AbstractDungeon.player, null);
                    break;
                case SELF:
                    unit.use(AbstractDungeon.player, null);
                    break;
                case ENEMY:
                    unit.use(AbstractDungeon.player, null);
            }
            unit.magicNumber--;
            logger.info(unit.name + " attack over, " + unit.magicNumber + " times remain");
        }
        this.addToBot(new checkUnitIfZeroAction());
        this.isDone = true;
    }
}
