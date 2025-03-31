package EpsilonMod.util;

public class epsilonModHelper {
    static String modName = "EpsilonMod";

    public static String makeID(String id) {
        return modName + ":" + id;
    }

    public static String assetPath(String path) {
        return modName + "Resources/" + path;
    }
}
