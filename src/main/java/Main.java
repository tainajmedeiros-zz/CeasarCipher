import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        String textoDescriptografado;

        Connection request = new Connection();
        String result = request.getRequest();

        FileWriter file;

        String sha = "";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("answer.json"));

            JSONObject jsonObj = new JSONObject(result);
            Integer shift = jsonObj.getInt("numero_casas");
            String texto = jsonObj.getString("cifrado");

            CaesarCipher tc = new CaesarCipher();

            textoDescriptografado = tc.dencryp(texto, shift);

            jsonObj.put("decifrado", textoDescriptografado);

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(textoDescriptografado.getBytes("utf8"));
            sha = String.format("%040x", new BigInteger(1, digest.digest()));

            jsonObj.put("resumo_criptografico", sha);

            writer.write(jsonObj.toString());
            writer.close();

            String json = jsonObj.toString();
            //System.out.println(jsonObj.toString());
            String encodedString = Base64.getEncoder().encodeToString(json.getBytes());
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            String decodedString = new String(decodedBytes);
            /*byte[] byt = System.Text.Encoding.UTF8.GetBytes(jsonObj.toString());
            // convert the byte array to a Base64 string
            strModified = Convert.ToBase64String(byt);*/


            result = request.postRequest("answer", decodedBytes);
            System.out.println(result);

        } catch (IOException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}