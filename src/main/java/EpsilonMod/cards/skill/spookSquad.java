package EpsilonMod.cards.skill;


import EpsilonMod.actions.addUnitToPileAction;
import EpsilonMod.enums.abstractCardEnum;
import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class spookSquad extends abstractEpsilonCard {
    public static final String ID = epsilonModHelper.makeID(spookSquad.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardColor COLOR = abstractCardEnum.EPSILON;

    public spookSquad() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, true, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(3);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new addUnitToPileAction(this));
    }
}
