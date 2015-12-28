package SecurityTool;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * Created by Ming on 2015/12/10.
 */
public class SHA256 {

    public String encrypt(String str, String salt) {
        return new Sha256Hash(str, salt, 2).toBase64();
    }

}
