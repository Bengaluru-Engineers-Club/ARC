package in.becsoft.arc.core.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSON {
        public static Gson getGson(){
            return new GsonBuilder().create();
        }
}
