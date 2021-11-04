import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class LZW {
    public static void main(String[] args) {
        try
        {
            if (args.length == 2) {
                File file = new File(args[1]);
                if (file.exists()) {
                    if (args[0].toLowerCase().contains("c")) { // Compress
                        String input = Files.readString(file.toPath());

                        byte[] compressedTags = compress(input);
                        for (byte tag :
                                compressedTags) {
                            System.out.println("Tag<" + Byte.toUnsignedInt(tag) + ">");
                        }

                        String newPath = file.getPath() + ".lzw";
                        File compressedFile = new File(newPath);
                        compressedFile.createNewFile();
                        Files.write(compressedFile.toPath(), compressedTags);
                    } else if (args[0].toLowerCase().contains("d")) { // Decompress
                        byte[] input = Files.readAllBytes(file.toPath());

                        String decompressedTxt = decompress(input);
                        System.out.println(decompressedTxt);

                        String newPath = file.getPath() + ".txt";

                        File decompressedFile = new File(newPath);
                        decompressedFile.createNewFile();

                        List<String> lines = new ArrayList<>();
                        lines.add(decompressedTxt);
                        Files.write(decompressedFile.toPath(), lines);
                    } else {
                        System.out.println(args[0] + " is invalid argument");
                    }
                } else {
                    System.out.println(args[1] + " is not an existing file");
                }
            } else {
                System.out.println("No arguments were supplied");
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static byte[] compress(String str) {
        ArrayList<String> dictionary = new ArrayList<>();
        List<Byte> tags = new ArrayList<>();

        for (int i = 0; i < str.length();){
            // Reset the dictionary
            if (dictionary.size() == 128) {
                dictionary.clear();
            }

            int dictionaryIndex = -1;
            StringBuilder temp_str = new StringBuilder();
            temp_str.append(str.charAt(i));
            for (int j = i + 1; j < str.length(); j++){
                temp_str.append(str.charAt(j));
                if (dictionary.contains(temp_str.toString())) {
                    dictionaryIndex = dictionary.indexOf(temp_str.toString());
                    if (j == str.length() - 1) {
                        byte tag = (byte) (dictionaryIndex + 128);
                        tags.add(tag);
                        i += temp_str.length();
                    }
                }
                else {
                    dictionary.add(temp_str.toString());

                    byte tag = (byte) (dictionaryIndex + 128);
                    if (dictionaryIndex == -1) {
                        tag = (byte) str.charAt(i);
                    }
                    tags.add(tag);
                    i += temp_str.length() - 1;
                    break;
                }
            }
        }

        byte[] bytes = new byte[tags.size()];
        for (int i = 0; i < tags.size(); i++) {
            bytes[i] = tags.get(i);
        }
        return bytes;
    }

    private static String decompress(byte[] input) {
        return null;
    }
}
