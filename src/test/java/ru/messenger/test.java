package ru.messenger;

import org.junit.Test;
import ru.messenger.cipher.AdvancedEncryptionStandard;

import java.nio.charset.StandardCharsets;

public class test {

    @Test
    public void testEncrypt() throws Exception {
        byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes(StandardCharsets.UTF_8);
        byte[] plainText = "Hello world!".getBytes(StandardCharsets.UTF_8);
        AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(encryptionKey);
        byte[] cipherText = advancedEncryptionStandard.encrypt(plainText);
        byte[] decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);

        System.out.println(new String(plainText));
        System.out.println(new String(cipherText));
        System.out.println(new String(decryptedCipherText));
    }

}
