package persistencia;

import com.google.gson.*;
import usuarios.Estudiante;
import usuarios.Profesor;
import usuarios.Usuario;

import java.lang.reflect.Type;

public class UsuarioAdapter implements JsonSerializer<Usuario>, JsonDeserializer<Usuario> {

    @Override
    public JsonElement serialize(Usuario src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getSimpleName());
        jsonObject.add("properties", context.serialize(src, src.getClass()));
        return jsonObject;
    }

    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement typeElement = jsonObject.get("type");
        
        if (typeElement == null) {
            throw new JsonParseException("Missing 'type' field in JSON: " + jsonObject.toString());
        }

        String type = typeElement.getAsString();
        JsonElement element = jsonObject;

        try {
            if (type.equals("Estudiante")) {
                return context.deserialize(element, Estudiante.class);
            } else if (type.equals("Profesor")) {
                return context.deserialize(element, Profesor.class);
            } else {
                throw new JsonParseException("Unknown element type: " + type);
            }
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}