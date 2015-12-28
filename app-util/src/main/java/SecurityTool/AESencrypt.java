package SecurityTool;

/**
 * Created by Ming on 2015/11/25.
 */

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 用来进行AES的加密和解密程序
 *
 * @author Steven
 *
 */
public class AESencrypt {

    // 加密算法
    private String ALGO = "AES";

    // 加密密钥
    // private static final byte[] keyValue = new byte[] { 'T', 'h', 'e',  'B','e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    // 16位的加密密钥
    private byte[] keyValue = new byte[] { 'T', 'h', 'e',  'B','e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    /**
     * 加密
     *
     * @param Data
     * @return
     * @throws Exception
     */
    public String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    /**
     * 解密
     *
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    /**
     * 根据密钥和算法生成Key
     *
     * @return
     * @throws Exception
     */
    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public String getALGO() {
        return ALGO;
    }

    public void setALGO(String aLGO) {
        ALGO = aLGO;
    }

    public byte[] getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(byte[] keyValue) {
        this.keyValue = keyValue;
    }

    public static void main(String[] args) throws Exception {
        // 创建加解密
        AESencrypt aes = new AESencrypt();
        // 设置加解密算法
        //aes.setALGO("AES");
        // 设置加解密密钥
       aes.setKeyValue("4E7FF1C1F04F4B36".getBytes());
        // 要进行加密的密码
        String password = "cat123@#steven";
        // 进行加密后的字符串
        String passwordEnc = aes.encrypt(password);
        String passwordDec = aes.decrypt(passwordEnc);
        System.out.println("原来的密码 : " + password);
        System.out.println("加密后的密码 : " + passwordEnc);
        System.out.println("解密后的原密码 : " + passwordDec);
    }
}