package org.sonarsource.plugins.example;

import org.sonarsource.plugins.example.rules.CsVariableNames;

import java.util.List;

public class test {
    public static void main(String[] args) {
        System.out.println("class Program".substring(6));
//        for (int i = 36 ; i < 48; i++) {
//            String tmpl ="bindRule(repository, IssueKeys.CA"+i+", \"10min\", \"5min\");";
//            System.out.println(tmpl);
//        }
//        String line = "private string astrAXXXXXXXX;";
//        if (isVariable(line)) {
//            System.out.println("Is variable!");
//        }
//        CsVariableNames csVariableNames = new CsVariableNames();
//        detectIssueVariables(line, csVariableNames.getCs09());
//        String line = "public void RunSomething(String pPram1, String xParam2)".trim();
//        if (line.startsWith("public ") || line.startsWith("private ") || line.startsWith("protected ")) {
//
//            line = line.substring(0, line.indexOf('('));
//            line = line.substring(line.lastIndexOf(' ') + 1);
//            if (line.length() < 2) {
//                System.out.println(line);
//            }
//            String firstChar = line.substring(0, 1);
//            String lastChar = line.substring(line.length() - 2);
//            if (!firstChar.equals(firstChar.toLowerCase())) {
//                System.out.println(line);
//            }
//            if (!lastChar.equals(lastChar.toLowerCase())) {
//                System.out.println(line);
//            }
//
//            if (line.indexOf('(') < line.indexOf(')')) {
//                String params = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
//                if (StringUtils.isNotEmpty(params)) {
//                    String[] paramArr = params.split(",");
//                    for (int i = 0; i < paramArr.length; i++) {
//                        String[] paramAttr = paramArr[i].split(" ");
//                        String paramName = paramAttr[paramAttr.length - 1];
//                        if (!paramName.startsWith("p")) {
//                            System.out.println(paramName);
//                        }
//                    }
//                }
//            }
//        }
    }

    private static boolean isVariable(String line) {
        if (line.endsWith(";")) {
            String[] lines = line.split(" ");
            if (line.startsWith("public ") || line.startsWith("private ") || line.startsWith("protected ")) {
                if (lines.length > 3) {
                    return true;
                }
            } else {
                if (lines.length > 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void detectIssueVariables(String line, List<String[]> csX) {
        if (!checkVariableName(line, csX)) {
            System.out.println("Variable name for " + csX.get(0)[0] + " must start with " + csX.get(0)[1] + "!");
        }
    }

    private static boolean checkVariableName(String line, List<String[]> cs17) {
        String varType;
        String varName;
        String[] lines = line.split(" ");
        if (line.startsWith("public ") || line.startsWith("private ") || line.startsWith("protected ")) {
            if (lines[1].equals("static")) {
                varType = lines[2];
                varName = lines[3];
            } else {
                varType = lines[1];
                varName = lines[2];
            }
        } else {
            varType = lines[0];
            varName = lines[1];
        }
        for (String[] varCheckArgs : cs17) {
            if (varCheckArgs[0].equals(varType) && !varName.startsWith(varCheckArgs[1])) {
                return false;
            }
        }
        return true;
    }
}
