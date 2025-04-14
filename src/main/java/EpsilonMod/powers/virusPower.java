package EpsilonMod.powers;

import EpsilonMod.actions.virusLoseHpAction;
import EpsilonMod.util.epsilonModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class virusPower extends abstractEpsilonPower implements HealthBarRenderPower {
    public Color virusColor = new Color(0x009432ff);
    public static final String POWER_ID = epsilonModHelper.makeID(virusPower.class.getSimpleName());
    private final AbstractCreature source;
    private final static PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public virusPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(powerStrings, POWER_ID, true, owner, amount, -1);
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.source = source;
        this.updateDescription();
    }

    public int getHealthBarAmount() {
        return this.amount;
    }

    public Color getColor() {
        return virusColor.cpy();
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            this.addToBot(new virusLoseHpAction(this.owner, this.source, this.amount));
        }
    }


    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (this.amount >= this.owner.currentHealth) {
            this.virusColor.a = c.a;
            c = this.virusColor;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString((int) (this.amount / 2)), x, y + 10, this.fontScale, c);
        }
    }

    public void onDeath() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && this.owner.currentHealth <= 0) {
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
                if (!monster.isDead)
                    this.addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, this, (int) (this.amount * 0.5)));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (int) (this.amount * 0.5) + DESCRIPTIONS[2];
    }

    public abstractEpsilonPower copy() {
        return new virusPower(this.owner, this.source, this.amount);
    }
}
