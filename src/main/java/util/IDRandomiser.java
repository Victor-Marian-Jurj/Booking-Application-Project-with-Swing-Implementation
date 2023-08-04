package util;
import java.util.UUID;

public class IDRandomiser {
    public static String getId (){
        UUID idOne = UUID.randomUUID();
        return String.valueOf(idOne);
    }
}
