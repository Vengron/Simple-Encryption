package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IOHandler {
    private String mode;
    private String data;
    private int key;
    private String importedFile;
    private String exportedFile;
    private Cipher cipher;

    public IOHandler() {
        mode = "enc";
        data = "";
        key = 0;
        cipher = new ShiftCipher();
    }

    public void processArgs(String[] args) {
        if (checkArgsValues(args)) {
            System.out.println("Error. Value not found.");
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
                if ("dec".equals(args[i + 1])) {
                    mode = args[i + 1];
                }
            } else if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            } else if ("-data".equals(args[i])) {
                data = args[i + 1];
            } else if ("-in".equals(args[i])) {
                importedFile = args[i + 1];
            } else if ("-out".equals(args[i])) {
                exportedFile = args[i + 1];
                System.out.println(exportedFile);
            } else if ("-alg".equals(args[i])) {
                if ("unicode".equals(args[i + 1])) {
                    cipher = new UnicodeCipher();
                }
            }
        }
    }

    private boolean checkArgsValues(String[] args) {
        boolean missingValue = false;
        for (int i = 0; i < args.length - 1; ++i) {
            if (args[i].contains("-")) {
                missingValue = args[i + 1].contains("-");
            }
        }
        return missingValue;
    }

    private void importData() {
        File file = new File(getImportedFile());
        try (Scanner files = new Scanner(file)) {
            data = files.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("Error. File not found.");
        }
    }

    public void checkImport() {
        if (importedFile != null && "".equals(data)) {
            importData();
        }
    }

    public void printOrSave(String message) {
        if (exportedFile != null) {
            try (FileWriter fileWriter = new FileWriter(exportedFile)) {
                fileWriter.write(message + "\n");
            } catch (IOException e) {
                System.out.println("Error. File cannot be created.");
            }
        } else {
            System.out.println(message);
        }
    }

    public String getImportedFile() {
        return importedFile;
    }

    public String getMode() {
        return mode;
    }

    public String getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public Cipher getCipher() {
        return cipher;
    }
}
