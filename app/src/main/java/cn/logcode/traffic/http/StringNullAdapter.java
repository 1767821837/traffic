package cn.logcode.traffic.http;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class StringNullAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter out, String value) throws IOException {
        // TODO Auto-generated method stub
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value);
    }

    @Override
    public String read(JsonReader in) throws IOException {
        // TODO Auto-generated method stub
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return "";
        }
        return in.nextString();
    }
}
