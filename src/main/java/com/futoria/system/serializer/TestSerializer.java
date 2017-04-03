package com.futoria.system.serializer;

import com.futoria.core.serializer.SubjectSerializer;
import com.futoria.core.serializer.UserSerializer;
import com.futoria.system.model.Question;
import com.futoria.system.model.Test;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TestSerializer implements JsonSerializer<Test> {
    private static QuestionSerializer questionSerializer;

    static {
        questionSerializer = new QuestionSerializer();
    }

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     * <p>
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(Test src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        JsonArray questions = new JsonArray();

        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("uuid", src.getUuid());
        jsonObject.addProperty("needValidation", src.getNeedValidation());
        jsonObject.addProperty("title", src.getTitle());
        jsonObject.add("subject", new SubjectSerializer().serialize(src.getSubject(), typeOfSrc, context));
        jsonObject.add("owner", new UserSerializer().serialize(src.getOwner(), typeOfSrc, context));

        try {
            for (Question question : src.getQuestions())
                questions.add(questionSerializer.serialize(question, typeOfSrc, context));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        jsonObject.add("questions", questions);

        return jsonObject;
    }
}
