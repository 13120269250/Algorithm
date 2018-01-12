package RSA;

/**
 * 引进的包都是Java自带的jar包
 * 秘钥相关包
 * base64 编解码
 * 这里只用到了编码
 */
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import sun.misc.BASE64Encoder;

/*
 * 公私钥生成工具类
 */
public class RSASecrteKey {

    public static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey"; //公钥
    private static final String PRIVATE_KEY = "RSAPrivateKey"; //私钥

    //map对象中存放公私钥
    public static Map<String, String> initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();
        
        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        
        String publicKeyBASE64 = encryptBASE64(publicKey);
        String privateKeyBASE64 = encryptBASE64(privateKey);
        
        //公私钥对象存入map中
        Map<String, String> keyMap = new HashMap<String, String>(2);
        keyMap.put(PUBLIC_KEY, publicKeyBASE64);
        keyMap.put(PRIVATE_KEY, privateKeyBASE64);
        return keyMap;
    }

    //编码返回字符串
    public static String encryptBASE64(Key key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key.getEncoded());
    }
    
    /** 
     * 公钥加密
     *  
     * @param str 
     *            需要加密的密文
     * @param publicKeyStr 
     *            公钥数据
     * @return cipherText 
     *            公钥加密后的密文
     * @throws Exception 
     *            公钥加密时产生的异常 
     */  
    public static String publicEncrypt(String str, String publicKeyStr) throws Exception {  
    	String cipherText = "";
    	try {
    		KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            byte[] encodedKey = Base64.decode(publicKeyStr);  
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));  
    		
	        Cipher cipher = Cipher.getInstance("RSA");         
            cipher.init(Cipher.ENCRYPT_MODE, pubKey); 
            byte[] cipherByte = cipher.doFinal(str.getBytes());
            cipherText = Base64.encode(cipherByte); 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return cipherText;   
    }  
    
    /** 
     * 私钥解密
     *  
     * @param secretStr 
     *            需要加密的密文
     * @param publicKeyStr 
     *            公钥数据
     * @return cipherText 
     *            公钥加密后的密文
     * @throws Exception 
     *            公钥加密时产生的异常 
     */  
    public static String praviteDecrypt(String secretStr, String privateKeyStr) throws Exception {  
    	String plainText = "";
    	try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKeyStr));   
            KeyFactory keyf = KeyFactory.getInstance("RSA");  
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);  
    		
    		Cipher cipher = Cipher.getInstance("RSA");         
            cipher.init(Cipher.DECRYPT_MODE, priKey);  
            byte[] cipherByte = Base64.decode(secretStr);
            byte[] plainByte = cipher.doFinal(cipherByte); 
            plainText = new String(plainByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return plainText;   
    }  
    
    /*
     * 测试
     */
    public static void main(String[] args) {
        Map<String, String> keyMap;
        String input = "hello world"; 
        try {
        	//生成密钥
            keyMap = initKey();
            String publicKey = keyMap.get(PUBLIC_KEY);
            String privateKey = keyMap.get(PRIVATE_KEY);
            System.out.println(publicKey);
            System.out.println(privateKey);
            
            String publicEncrypt = publicEncrypt(input,publicKey);
            System.out.println(publicEncrypt);
            
            String praviteDecrypt = praviteDecrypt(publicEncrypt,privateKey);
            System.out.println(praviteDecrypt);
            
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
    }
    
}
