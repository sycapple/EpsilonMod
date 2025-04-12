package EpsilonMod.cards.power;

import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import EpsilonMod.powers.synthesisVaultPower;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class synthesisVault extends abstractEpsilonCard {
    public static final String ID = epsilonModHelper.makeID(synthesisVault.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = CardType.POWER;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    public synthesisVault() {
        super(ID, true, CARD_STRINGS, COST, TYPE, RARITY, TARGET);
        this.setupMagicNumber(5);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToPlayer(new synthesisVaultPower(p, this.magicNumber));
    }
}