package studio.rashka.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.*;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class JSON {

    public void sendRequest(Object requestObject, String method, String httpURL, boolean data) {

        final Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String requestJson = json.toJson(requestObject); // this is just an example

        final HttpRequest request = new HttpRequest(method);
        final String url = httpURL;
        request.setUrl(url);

        request.setContent(requestJson);
        if (data) request.setContent("{\"data\":" + requestJson + "}");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");

        Gdx.app.log("JSON", request.getContent());

        Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
            public void handleHttpResponse(HttpResponse httpResponse) {

                int statusCode = httpResponse.getStatus().getStatusCode();
                if(statusCode != HttpStatus.SC_OK) {
                    Gdx.app.log("JSON", "Request Failed");
                    return;
                }
                //String responseJson = httpResponse.getResultAsString();
                try {
                    Gdx.app.log("JSON", httpResponse.getResultAsString());
                }
                catch(Exception exception) {
                    exception.printStackTrace();
                }
            }

            public void failed(Throwable t) {
                Gdx.app.log("JSON", "Request Failed Completely");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("JSON", "Request cancelled");
            }
        });
    }
}