package EpsilonMod.cards.skill;

import EpsilonMod.actions.addUnitToPileAction;
import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import EpsilonMod.cards.unit.brute;
import EpsilonMod.cards.unit.spook;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class bruteSquad extends abstractEpsilonCard {
    public static final String ID = epsilonModHelper.makeID(bruteSquad.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public bruteSquad() {
        super(ID, true, CARD_STRINGS, COST, TYPE, RARITY, TARGET);
        this.setupMagicNumber(3);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new addUnitToPileAction(new brute(), this.magicNumber));
    }
}