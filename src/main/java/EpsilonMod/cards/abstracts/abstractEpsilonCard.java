package EpsilonMod.cards.abstracts;

import EpsilonMod.enums.abstractCardEnum;
import EpsilonMod.util.epsilonModHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class abstractEpsilonCard extends CustomCard {
    public boolean upgradesecondaryM;
    public int secondaryM;
    public AbstractGameAction.AttackEffect effect;

    public abstractEpsilonCard(String ID, boolean useTmpArt, CardStrings strings, int COST, AbstractCard.CardType TYPE, AbstractCard.CardRarity RARITY, AbstractCard.CardTarget TARGET) {
        super(ID, strings.NAME, useTmpArt ? getTmpImgPath(TYPE) : getImgPath(TYPE, ID), COST, strings.DESCRIPTION, TYPE, abstractCardEnum.EPSILON, RARITY, TARGET);
        effect = AbstractGameAction.AttackEffect.BLUNT_LIGHT;
    }

    private static String getTmpImgPath(AbstractCard.CardType t) {
        String type;
        switch (t) {
            case ATTACK:
                type = "attack";
                break;
            case POWER:
                type = "power";
                break;
            case STATUS:
            case CURSE:
            case SKILL:
                type = "skill";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + t);
        }

        return String.format(epsilonModHelper.assetPath("img/cards/test_%s.png"), type);
    }

    private static String getImgPath(AbstractCard.CardType t, String name) {
        String type;
        switch (t) {
            case ATTACK:
                type = "attack";
                break;
            case POWER:
                type = "power";
                break;
            case STATUS:
            default:
                type = "special";
                break;
            case CURSE:
                type = "curse";
                break;
            case SKILL:
                type = "skill";
        }
        return String.format(epsilonModHelper.assetPath("img/cards/%s/%s.png"), type, name.replace(epsilonModHelper.makeID(""), ""));
    }

    // 初始化伤害点数
    protected void setupDamage(int amt) {
        this.baseDamage = amt;
        this.damage = amt;
    }

    // 初始化格挡点数
    protected void setupBlock(int amt) {
        this.baseBlock = amt;
        this.block = amt;
    }

    // 初始化能力点数
    protected void setupMagicNumber(int amt) {
        this.baseMagicNumber = amt;
        this.magicNumber = amt;
    }


    protected void setupSecondaryMagicNumber(int amt) {
        this.secondaryM = amt;
    }

    // 升级描述修改
    protected void upgradeDescription(CardStrings cardStrings) {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    // 单体伤害
    public void damageToEnemy(AbstractMonster m, AbstractGameAction.AttackEffect effect) {
        if (effect != null)
            this.effect = effect;
        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage), this.effect));
    }

    // 群体伤害
    public void damageToAllEnemies(AbstractGameAction.AttackEffect effect) {
        if (effect != null)
            this.effect = effect;
        this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, this.effect));
    }

    public void damageToRandomEnemies(AbstractGameAction.AttackEffect effect) {
        if (effect != null)
            this.effect = effect;
        this.addToBot(new AttackDamageRandomEnemyAction(this, this.effect));
    }

    // 获得牌的定义格挡点数
    public void gainBlock() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
    }

    // 获得牌任意格挡点数
    public void gainBlock(int amt) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, amt));
    }

    // 抽特定牌数
    public void drawCards(int amt) {
        this.addToBot(new DrawCardAction(amt));
    }

    // 将能力应用给角色
    public void applyToPlayer(AbstractPower power) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }

    // 升级
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.limitedUpgrade();
        }
    }


    public void limitedUpgrade() {
    }


    protected void upgradeSecondaryM(int amount) {
        this.secondaryM += amount;
        this.upgradesecondaryM = true;
    }


    public void triggerOnEndOfPlayerTurn() {
        this.addToTop(new ExhaustAllEtherealAction());
    }
}
