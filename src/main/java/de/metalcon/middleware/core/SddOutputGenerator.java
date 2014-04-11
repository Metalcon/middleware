package de.metalcon.middleware.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
                | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Couldn't create SddOutput.", e);
        }
    }

    private static SddOutput loadClass(JsonNode root, Class<?> clazz)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        SddOutput output = (SddOutput) clazz.newInstance();

        for (Method method : clazz.getMethods()) {
            String methodName = method.getName();
            if (!methodName.startsWith("set")
                    || method.getParameterTypes().length != 1) {
                continue;
            }

            String attr =
                    Character.toLowerCase(methodName.charAt(3))
                            + methodName.substring(4);

            // Setters have exactly 1 param
            Class<?> param = method.getParameterTypes()[0];

            if (param.equals(String.class)) {
                method.invoke(output, root.get(attr).textValue());
            } else if (param.equals(List.class)) {
                System.out.println(param);
                System.out.println(param.getComponentType());
            } else {
                param.newInstance();
                //method.invoke(output, loadClass(root.get(attr), param));
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
