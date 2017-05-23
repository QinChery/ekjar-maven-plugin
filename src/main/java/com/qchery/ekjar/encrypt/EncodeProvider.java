package com.qchery.ekjar.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * 编码策略
 *
 * @author Chery
 * @version 1.0.0
 * @date 2017/5/23
 */
public abstract class EncodeProvider implements EncryptProvider {

    /**
     * 编码方式
     */
    private EncodeAlgorithm encodeAlgorithm;

    /**
     * 加密
     *
     * @param plainBytes 明文字节
     * @return 加密后字节
     */
    public abstract byte[] encrypt(byte[] plainBytes);

    @Override
    public String encryptToString(byte[] plainBytes) {

        byte[] encryptBytes = encrypt(plainBytes);

        encodeAlgorithm = (encodeAlgorithm == null) ? EncodeAlgorithm.BASE64 : encodeAlgorithm;

        switch (encodeAlgorithm) {
            case BASE64:
                return new String(Base64.encodeBase64(encryptBytes));
            case HEX_LOWER_CASE:
                return new String(Hex.encodeHex(encryptBytes, true));
            case HEX_UPPER_CASE:
                return new String(Hex.encodeHex(encryptBytes, false));
        }

        return null;
    }

    public EncodeAlgorithm getEncodeAlgorithm() {
        return encodeAlgorithm;
    }

    public void setEncodeAlgorithm(EncodeAlgorithm encodeAlgorithm) {
        this.encodeAlgorithm = encodeAlgorithm;
    }
}
