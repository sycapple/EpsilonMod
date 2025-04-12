package EpsilonMod.cards.unit;

import EpsilonMod.cards.abstracts.abstractEpsilonUnit;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class brute extends abstractEpsilonUnit {
    public static final String ID = epsilonModHelper.makeID(brute.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public brute() {
        super(ID, true, CARD_STRINGS, TARGET);
        this.setupDamage(5);
        this.setupMagicNumber(2);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeDamage(3);
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void attack() {
        super.attack();
        this.damageToRandomEnemies(null);
    }
}
