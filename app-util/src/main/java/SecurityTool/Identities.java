package SecurityTool;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by Ming on 2015/12/10.
 */
public final   class Identities {
    //private static SecureRandom random  = new SecureRandom();
    private static Identities instance = new Identities();

    private Identities(){};

    public static Identities getInstance()
    {
        return instance;
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args){
        System.out.println(Identities.uuid());
    }
}


