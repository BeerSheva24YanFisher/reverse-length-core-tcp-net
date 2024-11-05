package telran.appl.net;

import telran.net.Protocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class ReverseLengthProtocol implements Protocol {

    @Override
    public Response getResponse(Request request) {
        String data = request.requestData();
        String reversed = new StringBuilder(data).reverse().toString();
        int length = data.length();
        String responseText = reversed + ": " + length;
        return new Response(ResponseCode.OK, responseText);
    }
}