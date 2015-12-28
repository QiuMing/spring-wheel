package SecurityTool;

import java.util.UUID;

/**
 * Created by Ming on 2015/12/11.
 */
public final class RandomNumUtils {
    public static String uuid16() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
