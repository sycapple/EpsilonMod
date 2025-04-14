package EpsilonMod.cards.unit;

import EpsilonMod.cards.abstracts.abstractEpsilonUnit;
import EpsilonMod.powers.virusPower;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class virusSniperUnit extends abstractEpsilonUnit {
    public static final String ID = epsilonModHelper.makeID(virusSniperUnit.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;

    public virusSniperUnit() {
        super(ID, true, CARD_STRINGS, TARGET);
        this.setupMagicNumber(2);
        this.setupSecondaryMagicNumber(5);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeSecondaryM(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void attack() {
        super.attack();
        this.applyToRandomEnemies(new virusPower(null, AbstractDungeon.player, this.secondaryM), this.secondaryM);
    }
}
