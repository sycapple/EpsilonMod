package EpsilonMod.cards.skill;

import EpsilonMod.actions.transformUnitToBrute;
import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class geneburst extends abstractEpsilonCard {
    public static final String ID = epsilonModHelper.makeID(geneburst.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public geneburst() {
        super(ID, true, CARD_STRINGS, COST, TYPE, RARITY, TARGET);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new transformUnitToBrute());
    }
}