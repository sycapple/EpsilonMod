package EpsilonMod.modCore;


import EpsilonMod.cards.attack.initiate;
import EpsilonMod.cards.skill.epsilonWalls;
import EpsilonMod.cards.skill.spookSquad;
import EpsilonMod.cards.unit.spook;
import EpsilonMod.characters.Epsilon;
import EpsilonMod.colorSet.epsilonColorSet;
import EpsilonMod.enums.abstractCardEnum;
import EpsilonMod.enums.abstractCharacterEnum;
import EpsilonMod.enums.abstractEpsilonRewardsEnum;
import EpsilonMod.panels.allUnitPanel;
import EpsilonMod.panels.modConfig;
import EpsilonMod.relic.epsilonEmblem;
import EpsilonMod.rewards.epsilonCardReward;
import EpsilonMod.util.epsilonModHelper;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rewards.RewardSave;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class epsilonMod implements EditCardsSubscriber, EditCharactersSubscriber, EditStringsSubscriber, EditRelicsSubscriber, AddAudioSubscriber, StartGameSubscriber, PostInitializeSubscriber, EditKeywordsSubscriber { // 实现接口
    public static final Logger logger = LogManager.getLogger(epsilonMod.class.getSimpleName());


    public epsilonMod() {
        logger.debug("Constructor started.");
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        BaseMod.addColor(abstractCardEnum.EPSILON,
                epsilonColorSet.epsilonColor, epsilonColorSet.epsilonColor, epsilonColorSet.epsilonColor, epsilonColorSet.epsilonColor, epsilonColorSet.epsilonColor, epsilonColorSet.epsilonColor, epsilonColorSet.epsilonColor,
                epsilonColorSet.attackBg,
                epsilonColorSet.skillBg,
                epsilonColorSet.powerBg,
                epsilonColorSet.energyOrb,
                epsilonColorSet.attackBgPortrait,
                epsilonColorSet.skillBgPortrait,
                epsilonColorSet.powerBgPortrait,
                epsilonColorSet.energyOrbPortrait,
                epsilonColorSet.cardEnergyOrb);
        logger.debug("Constructor finished.");
    }

    public void receivePostInitialize() {
        BaseMod.registerCustomReward(abstractEpsilonRewardsEnum.EPSILON_CARD_REWARD, (rewardSave) -> {
            epsilonCardReward db = new epsilonCardReward();
            return db;
        }, (customReward) -> new RewardSave(customReward.type.toString(), (String) null));
//        BaseMod.addEvent(SelfExplosiveCowEvent.ID, SelfExplosiveCowEvent.class);
    }

    public static void initialize() {

        logger.info("========================= 开始初始化 =========================");
        new epsilonMod();
        new allUnitPanel();
        new modConfig();
        logger.info("========================= 初始化完成 =========================");
    }

    public void receiveAddAudio() {
        logger.info("========================= 开始加载音效 =========================");
        BaseMod.addAudio("SOVIET_SELECT", epsilonModHelper.assetPath("sound/SovietSelect.ogg"));
        BaseMod.addAudio("MIG_BOMBING", epsilonModHelper.assetPath("sound/MigBombing.ogg"));
        logger.info("========================= 音效加载完毕 =========================");
    }

    public void receiveStartGame() {
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // TODO 这里写添加你卡牌的代码
        logger.info("========================= 开始加载卡牌 =========================");
        BaseMod.addCard(new initiate());
        BaseMod.addCard(new epsilonWalls());
        BaseMod.addCard(new spookSquad());
        BaseMod.addCard(new spook());
        logger.info("========================= 卡牌加载完毕 =========================");
    }

    public void receiveEditRelics() {
        logger.info("========================= 开始加载遗物 =========================");
        BaseMod.addRelicToCustomPool(new epsilonEmblem(), abstractCardEnum.EPSILON);
        logger.info("========================= 遗物加载完毕 =========================");
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        logger.info("========================= 开始加载人物 =========================");
        BaseMod.addCharacter(new Epsilon(CardCrawlGame.playerName), epsilonModHelper.assetPath("img/character/SovietButton.png"), epsilonModHelper.assetPath("img/character/SovietCover.png"), abstractCharacterEnum.EPSILON);
        logger.info("========================= 人物加载完毕 =========================");
    }

    public void receiveEditKeywords() {
        logger.info("========================= 加载关键词 =========================");
        Gson gson = new Gson();
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else if (Settings.language == Settings.GameLanguage.RUS) {
            lang = "RUS";
        }

        String json = Gdx.files.internal(epsilonModHelper.assetPath("localization/" + lang + "/keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[]) gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            Keyword[] var5 = keywords;
            int var6 = keywords.length;
            for (int var7 = 0; var7 < var6; ++var7) {
                Keyword keyword = var5[var7];
                BaseMod.addKeyword("EpsilonMod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
        logger.info("========================= 关键词加载毕 =========================");
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        // 这里添加注册本地化文本
        BaseMod.loadCustomStringsFile(CardStrings.class, epsilonModHelper.assetPath("localization/" + lang + "/cards.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, epsilonModHelper.assetPath("localization/" + lang + "/characters.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, epsilonModHelper.assetPath("localization/" + lang + "/ui.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, epsilonModHelper.assetPath("localization/" + lang + "/powers.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, epsilonModHelper.assetPath("localization/" + lang + "/relics.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class, epsilonModHelper.assetPath("localization/" + lang + "/events.json"));
    }
}

