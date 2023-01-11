package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier;
        String returnType;
        String methodName;
        List<MethodSignature.Argument> arguments;

        MethodSignature methodSignature;

        String[] methodSignatureAndArguments = signatureString.split("\\(");
        arguments = parseArguments(methodSignatureAndArguments[1]);


        String[] accessModifierAndReturnTypeAndMethodName = methodSignatureAndArguments[0].split(" ");
        if (accessModifierAndReturnTypeAndMethodName.length > 2) {
            accessModifier = accessModifierAndReturnTypeAndMethodName[0];
            returnType = accessModifierAndReturnTypeAndMethodName[1];
            methodName = accessModifierAndReturnTypeAndMethodName[2];

            methodSignature = new MethodSignature(methodName, arguments);
            methodSignature.setAccessModifier(accessModifier);
            methodSignature.setReturnType(returnType);
        } else {
            returnType = accessModifierAndReturnTypeAndMethodName[0];
            methodName = accessModifierAndReturnTypeAndMethodName[1];

            methodSignature = new MethodSignature(methodName, arguments);
            methodSignature.setReturnType(returnType);
        }

        return methodSignature;
    }

    private List<MethodSignature.Argument> parseArguments(String str) {

        List<MethodSignature.Argument> argumentList = new ArrayList<>();

        String[] argumentsTypeAndName = str.substring(0, str.length() - 1).split(",");
        if (argumentsTypeAndName.length > 0 && !str.equals(")")) {
            for (String s : argumentsTypeAndName) {
                String[] typeAndName = s.trim().split(" ");
                String type = typeAndName[0];
                String name = typeAndName[1];
                argumentList.add(new MethodSignature.Argument(type, name));
            }
        }
        return argumentList;
    }
}
