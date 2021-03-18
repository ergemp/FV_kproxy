package org.ergemp.fv.kproxy.Servlet.Handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.ergemp.fv.kproxy.Servlet.Response.Response200;
import org.ergemp.fv.kproxy.Servlet.Response.Response400;
import org.ergemp.fv.kproxy.Servlet.Response.Response500;
import org.ergemp.fv.kproxy.actor.SendMessage;
import org.ergemp.fv.kproxy.config.GetKafkaProducer;
import org.ergemp.fv.kproxy.model.GenericDataModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RestProxy extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        PrintWriter prt = response.getWriter();
        BufferedReader rdr = request.getReader();

        String readLine;
        StringBuffer sb = new StringBuffer();

        while ((readLine = rdr.readLine()) != null) {
            sb.append(readLine);
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObj = mapper.readTree(sb.toString());

        if (
                jsonObj.get("token") != null &&
                jsonObj.get("ts") != null &&
                jsonObj.get("label") != null &&
                jsonObj.get("payload") != null
        ) {

            Producer producer = GetKafkaProducer.get("RestProxy01");


            GenericDataModel model = new GenericDataModel(
                    "f2d52d8e-26f3-40c1-910a-4c6a001f2589",
                    System.currentTimeMillis(),
                    jsonObj.get("label").textValue(),
                    jsonObj.get("payload").textValue() );

            SendMessage.send(producer, jsonObj.get("token").textValue(), model.toString());

            System.out.print(model.toString());

            producer.close();

            Response200.set200(response);
        }
        else {
            Response400.set400(response);
        }

    }

}
