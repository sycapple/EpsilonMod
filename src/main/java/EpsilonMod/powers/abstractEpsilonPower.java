package EpsilonMod.powers;

import EpsilonMod.util.epsilonModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.UUID;

public abstract class abstractEpsilonPower extends AbstractPower {
    public boolean upgraded = false;
    public UUID Cuuid;
    protected Color redColor = new Color(1.0F, 0.0F, 0.0F, 1.0F);
    protected Color greenColor = new Color(0.0F, 1.0F, 0.0F, 1.0F);


    public abstractEpsilonPower(PowerStrings powerStrings, String POWER_ID, boolean ifTmpArt, AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        String path128 = get128ImgPath(ifTmpArt, this.name);
        String path48 = get48ImgPath(ifTmpArt, this.name);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    private static String get128ImgPath(boolean ifTmpArt, String name) {
        if (ifTmpArt)
            return epsilonModHelper.assetPath("/img/powers/TestPowerB.png");
        else
            return String.format(epsilonModHelper.assetPath("/img/powers/%sB.png"), name.replace(epsilonModHelper.makeID(""), ""));
    }

    private static String get48ImgPath(boolean ifTmpArt, String name) {
        if (ifTmpArt)
            return epsilonModHelper.assetPath("/img/powers/TestPower.png");
        else
            return String.format(epsilonModHelper.assetPath("/img/powers/%s.png"), name.replace(epsilonModHelper.makeID(""), ""));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.limitedUpgrade();
            this.updateDescription();
        }

    }

    public void limitedUpgrade() {
    }

}
