package encryptdecrypt;


public class Main {
    public static void main(String[] args) {
        IOHandler ioHandler = new IOHandler();
        ioHandler.processArgs(args);
        ioHandler.checkImport();
        CryptMaker cryptMaker = new CryptMaker(ioHandler.getCipher());
        cryptMaker.doCrypto(ioHandler.getMode(), ioHandler.getData(), ioHandler.getKey());
        ioHandler.printOrSave(cryptMaker.getMessage());
    }
}
