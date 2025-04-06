package EpsilonMod.cards.attack;

import EpsilonMod.enums.abstractCardEnum;
import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class yuriRecruit extends abstractEpsilonCard {
    public static final String ID = epsilonModHelper.makeID(yuriRecruit.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardColor COLOR = abstractCardEnum.EPSILON;

    public yuriRecruit() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupDamage(6);
        this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

}
