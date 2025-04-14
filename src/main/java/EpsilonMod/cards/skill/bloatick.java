package EpsilonMod.cards.skill;

import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import EpsilonMod.powers.virusPower;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class bloatick extends abstractEpsilonCard {
    public static final String ID = epsilonModHelper.makeID(bloatick.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public bloatick() {
        super(ID, true, CARD_STRINGS, COST, TYPE, RARITY, TARGET);
        this.setupMagicNumber(10);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToAllEnemies(new virusPower(null, AbstractDungeon.player, this.magicNumber), this.magicNumber);
    }
}