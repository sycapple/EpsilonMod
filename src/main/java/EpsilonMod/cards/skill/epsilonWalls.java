package EpsilonMod.cards.skill;


import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import EpsilonMod.enums.abstractCardEnum;
import EpsilonMod.util.epsilonModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class epsilonWalls extends abstractEpsilonCard {
    public static final String ID = epsilonModHelper.makeID(epsilonWalls.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public epsilonWalls() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, true, CARD_STRINGS, COST, TYPE, RARITY, TARGET);
        this.tags.add(CardTags.STARTER_DEFEND);
        this.setupBlock(5);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.gainBlock();
    }
}
