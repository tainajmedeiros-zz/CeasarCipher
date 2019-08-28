import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {

    // function responsible for the get connection
    public String getRequest() {

        HttpURLConnection connection = null;

        try {
            String url = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=50cd7b778f02770f9b3bf8373315b74806f97082";
            URL myurl = new URL(url);
            connection = (HttpURLConnection) myurl.openConnection();

            connection.setRequestMethod("GET");

            StringBuilder content = new StringBuilder();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String line;

            // dynamically manipulating string data
            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }

            return content.toString();

        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            assert connection != null;
            connection.disconnect();
        }
        return null;
    }

    // function responsible for the post connection
    public String postRequest(String name, byte[] byteArray){

        String file = "answer";
        String crlf = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";

        try{

            String urlSolucition = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=50cd7b778f02770f9b3bf8373315b74806f97082";
            URL url = new URL(urlSolucition);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            connection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream request = new DataOutputStream(
                    connection.getOutputStream());
            request.writeBytes(twoHyphens + boundary + crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" +
                    file + "\";filename=\"" +
                    name + "\"" + crlf);
            request.writeBytes(crlf);
            request.write(byteArray);
            request.writeBytes(crlf);
            request.writeBytes(twoHyphens + boundary +
                    twoHyphens + crlf);
            request.flush();
            request.close();

            StringBuilder response = new StringBuilder();

            // checks server's status code first
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while (null != (line = reader.readLine())) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();
                return response.toString();

            } else {
                throw new IOException("Server returned non-OK status: " + status);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        return null;

    }
}