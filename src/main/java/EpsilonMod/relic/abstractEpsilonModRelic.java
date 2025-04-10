package EpsilonMod.relic;

import EpsilonMod.colorSet.epsilonColorSet;
import EpsilonMod.util.epsilonModHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class abstractEpsilonModRelic extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(abstractEpsilonModRelic.class.getSimpleName());

    public abstractEpsilonModRelic(String ID, boolean useTmpArt, RelicTier RELIC_TIER, LandingSound LANDING_SOUND) {
        super(ID, ImageMaster.loadImage(getImgPath(useTmpArt, ID)),
                ImageMaster.loadImage(getImg0Path(useTmpArt, ID)),
                RELIC_TIER,
                LANDING_SOUND
        );
    }

    private static String getImgPath(boolean useTmpArt, String ID) {
        String imgPath;
        if (useTmpArt)
            imgPath = epsilonModHelper.assetPath("img/relics/testRelic.png");
        else
            imgPath = String.format(epsilonModHelper.assetPath("img/relics/%s.png"), ID.replace(epsilonModHelper.makeID(""), ""));
        if (ImageMaster.loadImage(imgPath) == null) {
            logger.info("Failed to load relic images: " + imgPath);
        }
        logger.info("imgPath: " + imgPath);
        logger.info("ID: " + ID);
        return imgPath;
    }

    private static String getImg0Path(boolean useTmpArt, String ID) {
        String img0Path;
        if (useTmpArt)
            img0Path = epsilonModHelper.assetPath("img/relics/outline/testRelic.png");
        else
            img0Path = String.format(epsilonModHelper.assetPath("img/relics/outline/%s.png"), ID.replace(epsilonModHelper.makeID(""), ""));
        if (ImageMaster.loadImage(img0Path) == null) {
            logger.info("Failed to load relic images: " + img0Path);
        }
        logger.info("img0Path: " + img0Path);
        return img0Path;
    }
}