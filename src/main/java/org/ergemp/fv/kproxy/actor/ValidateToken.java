package org.ergemp.fv.kproxy.actor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class ValidateToken {
    public static Boolean validate (String gToken){
        Boolean retVal = false ;

        try {
            CloseableHttpClient client = HttpClients.createDefault();

            //localhost:8091/rest/token/valid?tokenName=8c1f2b29-efad-4472-98ba-07baa2768efb
            //HttpGet request = new HttpGet("http://localhost:8091/rest/token/valid?tokenName=" + gToken);

            HttpGet request = new HttpGet("http://localhost:8091/rest/token/valid");
            URI uri = new URIBuilder(request.getURI()).addParameter("tokenName", gToken).build();
            ((HttpRequestBase) request).setURI(uri);

            HttpResponse response = client.execute(request);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            /*
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            */

            String readLine;
            StringBuffer sb = new StringBuffer();

            while ((readLine = rd.readLine()) != null) {
                sb.append(readLine);
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObj = mapper.readTree(sb.toString());

            retVal = Boolean.valueOf(jsonObj.get("isValid").textValue());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            return retVal;
        }
    }
}
