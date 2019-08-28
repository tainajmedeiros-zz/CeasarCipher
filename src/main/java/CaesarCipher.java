import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CaesarCipher {
    private List<Character> specialChars;

    // constructor
    CaesarCipher() {
        // creating list of characters that should be ignored
        this.specialChars = new ArrayList<>();
        this.specialChars.add('.');
        this.specialChars.add(' ');
        this.specialChars.add(',');
    }

    // function responsible for decrypting the text
    public String dencryp(String text, int shift) {
        String cipherText = "";
        char letter;
        int n = text.length();
        // calling function to remove accents
        text = removeAccents(text);

        // scrolling through ciphertext
        for (int i=0; i<n; i++) {
            letter = text.charAt(i);
            // checking what is the character type
            if ( !this.isCharDencryp(letter) ) {
                cipherText = cipherText + (char) (letter);
            } else {
                // calling the function responsible for the rotation of the alphabet
                letter = rotateAlphabet(letter, shift);
                cipherText = cipherText + (char)(letter - shift);

            }
        }
        // lowering the text
        cipherText = cipherText.toLowerCase();

        return cipherText;
    }

    // function responsible for checking if the character should be ignored
    public boolean isCharDencryp(char c){
        return !Character.isDigit(c) && !this.specialChars.contains(c);
    }

    //function responsible for removing accents from text
    public String removeAccents(String text) {
        String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    // function responsible for rotating the alphabet
    public char rotateAlphabet(char letter, int shift){
        char result;
        int aux = Character.getNumericValue(letter);
        // check if the rotation is greater than the alphabet
        if((aux-shift) < 10){
            result = (char)('z' - (9 - aux));
            return result;
        }
        return letter;
    }

}