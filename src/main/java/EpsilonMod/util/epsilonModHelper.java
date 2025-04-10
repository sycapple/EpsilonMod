package EpsilonMod.util;

import EpsilonMod.actions.allUnitAttackAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class epsilonModHelper {

    static String modName = "EpsilonMod";

    public static String makeID(String id) {
        return modName + ":" + id;
    }

    public static String assetPath(String path) {
        return modName + "Resources/" + path;
    }
}
