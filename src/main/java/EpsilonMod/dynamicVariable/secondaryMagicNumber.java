package EpsilonMod.dynamicVariable;

import EpsilonMod.cards.abstracts.abstractEpsilonCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class secondaryMagicNumber extends DynamicVariable {
    @Override
    public String key() {
        return "SM"; // 返回变量的标识符，不包括感叹号
    }

    @Override
    public boolean isModified(AbstractCard card) {
        // 返回该变量是否被修改，用于显示橙色高亮
        return ((abstractEpsilonCard) card).isModifiedSecondaryM;
    }

    @Override
    public int value(AbstractCard card) {
        // 返回该变量的值
        return ((abstractEpsilonCard) card).secondaryM;
    }

    @Override
    public int baseValue(AbstractCard card) {
        // 返回该变量的基础值
        return ((abstractEpsilonCard) card).baseSecondaryM;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((abstractEpsilonCard) card).isUpgradeSecondaryM;
    }
}