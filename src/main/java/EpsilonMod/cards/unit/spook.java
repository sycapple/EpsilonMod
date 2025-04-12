package EpsilonMod.cards.unit;


import EpsilonMod.cards.abstracts.abstractEpsilonUnit;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class spook extends abstractEpsilonUnit {
    public static final String ID = epsilonModHelper.makeID(spook.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 0;
    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    public static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;

    public spook() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, true, CARD_STRINGS, COST, TARGET);
        this.setupDamage(3);
        this.setupMagicNumber(1);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeDamage(2);
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
