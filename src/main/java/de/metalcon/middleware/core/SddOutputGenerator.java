package de.metalcon.middleware.core;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.sdd.api.responses.SddSucessfulReadResponse;

public class SddOutputGenerator {

    private static ObjectMapper mapper = new ObjectMapper();

    public static SddOutput get(
            SddSucessfulReadResponse response,
            Muid nodeId,
            String detail) {
        String output = response.get(nodeId, detail);
        if (output == null) {
            return null;
        }

        try {
            JsonNode root = mapper.readValue(output, JsonNode.class);
            String nodeType = root.get("type").textValue();
            Class<?> clazz =
                    Class.forName("de.metalcon.middleware.sdd."
                            + nodeType.toLowerCase() + "."
                            + buildClassName(nodeType, detail));
            return loadClass(root, clazz);
        } catch (IOException | ClassNotFoundException | InstantiationException
                | IllegalAccessException | NoSuchFieldException
                | InvocationTargetException e) {
            throw new RuntimeException("Couldn't create SddOutput.", e);
        }
    }

    private static SddOutput loadClass(JsonNode root, Class<?> clazz)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException,
            NoSuchFieldException, SecurityException {
        SddOutput output = (SddOutput) clazz.newInstance();
        Field id = SddOutput.class.getDeclaredField("muid");
        id.setAccessible(true);
        id.set(output, Muid.createFromID(root.get("id").asLong()));

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            String attr = field.getName();
            Class<?> type = field.getType();

            if (type.equals(String.class)) {
                field.set(output, root.get(attr).textValue());
            } else if (type.isArray()) {
                Class<?> componentType = type.getComponentType();
                ArrayNode arrayNode = (ArrayNode) root.get(attr);
                SddOutput[] array =
                        (SddOutput[]) Array.newInstance(componentType,
                                arrayNode.size());
                int i = 0;
                for (JsonNode item : arrayNode) {
                    array[i] = loadClass(item, componentType);
                    ++i;
                }

                field.set(output, array);
            } else {
                field.set(output, loadClass(root.get(attr), type));
            }
        }

        return output;
    }

    private static String buildClassName(String nodeType, String detail) {
        return capitalize(nodeType) + capitalize(detail);
    }

    private static String capitalize(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

}
