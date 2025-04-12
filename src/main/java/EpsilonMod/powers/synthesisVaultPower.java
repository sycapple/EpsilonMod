package EpsilonMod.powers;

import EpsilonMod.util.epsilonModHelper;
import EpsilonMod.util.unitPileManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static EpsilonMod.colorSet.epsilonColorSet.epsilonColor;

public class synthesisVaultPower extends abstractEpsilonPower {
    public static Color synthesisVaultPowerColor = epsilonColor;
    public static final String POWER_ID = epsilonModHelper.makeID(synthesisVaultPower.class.getSimpleName());
    private final static PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public synthesisVaultPower(AbstractCreature owner, int amount) {
        super(powerStrings, POWER_ID, true, owner, amount, 40);
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        synthesisVaultPowerColor.a = c.a;
        c = synthesisVaultPowerColor;
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(unitPileManager.getMaxUnit()), x, y + 10, this.fontScale, c);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 40)
            this.amount = 40;
        unitPileManager.setMaxUnit(this.amount + unitPileManager.getInitialMaxUnit());
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        unitPileManager.setMaxUnit(this.amount + unitPileManager.getInitialMaxUnit());
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (unitPileManager.getInitialMaxUnit() + this.amount);
    }
}
