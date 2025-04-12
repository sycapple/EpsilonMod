package EpsilonMod.cards.skill;

import EpsilonMod.actions.addAttackTimeAction;
import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class regenDrugs extends abstractEpsilonCard {
    public static final String ID = epsilonModHelper.makeID(regenDrugs.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public regenDrugs() {
        super(ID, true, CARD_STRINGS, COST, TYPE, RARITY, TARGET);
        setupMagicNumber(1);
    }

    @Override
    public void limitedUpgrade() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new addAttackTimeAction(magicNumber));
    }
}