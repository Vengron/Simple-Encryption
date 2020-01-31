package encryptdecrypt;

public class CryptMaker {
    private Cipher cipher;
    private String message;

    public CryptMaker(Cipher cipher) {
        this.cipher = cipher;
    }

    public void doCrypto(String method, String data, int key) {
        switch (method) {
            case "dec":
                message = cipher.decode(data, key);
                break;
            case "enc":
                message = cipher.encode(data, key);
                break;
            default:
                break;
        }
    }

    public String getMessage() {
        return message;
    }
}

interface Cipher {
    String encode(String input, int key);

    String decode(String input, int key);
}

class UnicodeCipher implements Cipher {

    public String encode(String input, int key) {
        StringBuilder encoded = new StringBuilder();
        for (char ch : input.toCharArray()) {
            ch += key;
            encoded.append(ch);
        }
        return encoded.toString();
    }

    public String decode(String input, int key) {
        StringBuilder coded = new StringBuilder();
        for (char ch : input.toCharArray()) {
            ch -= key;
            coded.append(ch);
        }
        return coded.toString();
    }
}

class ShiftCipher implements Cipher {

    public String encode(String input, int key) {
        StringBuilder encoded = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (String.valueOf(ch).matches("[a-z]")) {
                if (ch + key > 122) {
                    ch += key;
                    ch -= 26;
                    encoded.append(ch);
                } else {
                    ch += key;
                    encoded.append(ch);
                }
            } else {
                encoded.append(ch);
            }
        }
        return encoded.toString();
    }

    public String decode(String input, int key) {
        StringBuilder code = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (String.valueOf(ch).matches("[a-z]")) {
                if (ch - key < 97) {
                    ch -= key;
                    ch += 26;
                    code.append(ch);
                } else {
                    ch -= key;
                    code.append(ch);
                }
            } else {
                code.append(ch);
            }
        }
        return code.toString();
    }
}
