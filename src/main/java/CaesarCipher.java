import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CaesarCipher {

    private int i;
    private int n, aux;
    private String ciphertext = "";
    private List<Character> specialChars;

    CaesarCipher() {
        this.specialChars = new ArrayList<Character>();
        this.specialChars.add('.');
        this.specialChars.add(' ');
        this.specialChars.add(',');
    }

    public String dencryp(String text, int shift) {
        Character letter = ' ';
        n = text.length();
        text = removeAccents(text);

        for (i=0; i<n; i++) {
            letter = text.charAt(i);
            if ( !this.isCharDencryp(letter) ) {
                ciphertext = ciphertext + (char)(letter);
            } else {
                letter = rotateAlphabet(letter, shift);
                ciphertext = ciphertext + (char)(letter - shift);

            }
        }
        ciphertext = ciphertext.toLowerCase();
        return ciphertext;
    }

    public boolean isCharDencryp(char c){
        if(Character.isDigit(c) || this.specialChars.contains(c))
            return false;

        return true;
    }

    public String removeAccents(String text) {
        //return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public char rotateAlphabet(char letter, int shift){
        char result;
        aux = Character.getNumericValue(letter);
        if((aux-shift) < 10){
            result = (char)('z' - (9 - aux));
            return result;
        }
        return letter;
    }
}