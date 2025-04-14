package EpsilonMod.cards.abstracts;

import com.megacrit.cardcrawl.localization.CardStrings;

public abstract class abstractEpsilonUnit extends abstractEpsilonCard implements epsilonUnit {
    private static final CardStrings CARD_STRINGS = null;

    public abstractEpsilonUnit(String ID, boolean useTmpArt, CardStrings strings, CardTarget TARGET) {
        super(ID, useTmpArt, strings, 0, CardType.ATTACK, CardRarity.BASIC, TARGET);
    }

    protected void setupDamage(int amt) {
        super.setupDamage(amt);
    }

    // 初始化格挡点数
    protected void setupBlock(int amt) {
        super.setupBlock(amt);
    }

    // 初始化能力点数
    protected void setupMagicNumber(int amt) {
        super.setupMagicNumber(amt);
    }


    protected void setupSecondaryMagicNumber(int amt) {
        super.setupSecondaryMagicNumber(amt);
    }

    // 升级描述修改
    protected void upgradeDescription(CardStrings cardStrings) {
        super.upgradeDescription(cardStrings);
    }

    protected void upgradeSecondaryM(int amount) {
        super.upgradeSecondaryM(amount);
    }

    public void attack() {
        this.magicNumber--;
    }
}
