import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        String decryptedText;
        Connection request = new Connection();
        String summary;

        try {
            // sending GET
            String result = request.getRequest();

            // creating json file
            BufferedWriter writer = new BufferedWriter(new FileWriter("answer.json"));
            JSONObject jsonObj = new JSONObject(result);

            // getting values from file
            int shift = jsonObj.getInt("numero_casas");
            String text = jsonObj.getString("cifrado");

            // decrypting plain text
            CaesarCipher encryptedText = new CaesarCipher();
            decryptedText = encryptedText.dencryp(text, shift);
            jsonObj.put("decifrado", decryptedText);

            // create the cryptographic summary
            CryptographicSummary sha = new CryptographicSummary();
            summary = sha.summary(decryptedText);

            // adding cryptographic summary to file
            jsonObj.put("resumo_criptografico", summary);
            writer.write(jsonObj.toString());
            writer.close();

            // convert the byte array to a Base64 string
            String json = jsonObj.toString();
            String encodedString = Base64.getEncoder().encodeToString(json.getBytes());
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

            // Sending POST
            result = request.postRequest("answer", decodedBytes);
            System.out.println(result);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}