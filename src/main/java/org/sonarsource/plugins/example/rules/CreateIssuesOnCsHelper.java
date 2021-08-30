/*
 * Example Plugin for SonarQube
 * Copyright (C) 2009-2020 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.plugins.example.rules;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;

import java.util.List;

/**
 * Generates issues on all java files at line 1. This rule
 * must be activated in the Quality profile.
 */
public class CreateIssuesOnCsHelper {

    private static final double ARBITRARY_GAP = 2.0;
    private CsVariableNames csVariableNames = new CsVariableNames();

    /**
     * @param context     Sonar context
     * @param javaFile    Scanning file
     * @param currentLine Current analysis line
     * @param lineNum     Current line number
     */
    public void makeIssues(SensorContext context, InputFile javaFile, String currentLine, int lineNum, String currentClass) {
        // no need to define the severity as it is automatically set according
        // to the configured Quality profile
        // Compact code
        currentLine = currentLine.trim().replaceAll("\\s+", " ");
        // Detect what kind of code is this ?
        SOURCE_CATEGORY sourceCategory = detectCategory(currentLine, currentClass);
        try {
            detectIssue(currentLine, sourceCategory);
        } catch (IssueException ex) {
            // no need to define the severity as it is automatically set according
            // to the configured Quality profile
            NewIssue exIssue = context.newIssue()
                    .forRule(ex.getRuleKey())
                    // gap is used to estimate the remediation cost to fix the debt
                    .gap(ARBITRARY_GAP);

            NewIssueLocation exLocation = exIssue.newLocation()
                    .on(javaFile)
                    .at(javaFile.selectLine(lineNum))
                    .message(ex.getMessage());
            exIssue.at(exLocation);
            exIssue.save();
        }
    }

    public void detectIssue(String line, SOURCE_CATEGORY sourceCategory) throws IssueException {
        if (SOURCE_CATEGORY.FUNCTION_CONSTRUCT == sourceCategory) {
            detectIssueCA01(line);
            detectIssueCA36(line);
        }
        if (SOURCE_CATEGORY.VARIABLE_DECLARE == sourceCategory) {
            detectIssueCA06(line);
        }
        if (SOURCE_CATEGORY.VARIABLE_DECLARE == sourceCategory) {
            detectIssueVariables(line, csVariableNames.getCs09(), IssueKeys.CA09);
            detectIssueVariables(line, csVariableNames.getCs10(), IssueKeys.CA10);
            detectIssueVariables(line, csVariableNames.getCs11(), IssueKeys.CA11);
            detectIssueVariables(line, csVariableNames.getCs12(), IssueKeys.CA12);
            detectIssueVariables(line, csVariableNames.getCs13(), IssueKeys.CA13);
            detectIssueVariables(line, csVariableNames.getCs14(), IssueKeys.CA14);
            detectIssueVariables(line, csVariableNames.getCs15(), IssueKeys.CA15);
            detectIssueVariables(line, csVariableNames.getCs16(), IssueKeys.CA16);
            detectIssueVariables(line, csVariableNames.getCs17(), IssueKeys.CA17);
            detectIssueVariables(line, csVariableNames.getCs18(), IssueKeys.CA18);
            detectIssueVariables(line, csVariableNames.getCs19(), IssueKeys.CA19);
            detectIssueVariables(line, csVariableNames.getCs20(), IssueKeys.CA20);
            detectIssueVariables(line, csVariableNames.getCs21(), IssueKeys.CA21);
            detectIssueVariables(line, csVariableNames.getCs22(), IssueKeys.CA22);
            detectIssueVariables(line, csVariableNames.getCs23(), IssueKeys.CA23);
            detectIssueVariables(line, csVariableNames.getCs24(), IssueKeys.CA24);
            detectIssueVariables(line, csVariableNames.getCs25(), IssueKeys.CA25);
            detectIssueVariables(line, csVariableNames.getCs26(), IssueKeys.CA26);
            detectIssueVariables(line, csVariableNames.getCs27(), IssueKeys.CA27);
            detectIssueVariables(line, csVariableNames.getCs28(), IssueKeys.CA28);
            detectIssueVariables(line, csVariableNames.getCs29(), IssueKeys.CA29);
            detectIssueVariables(line, csVariableNames.getCs30(), IssueKeys.CA30);
            detectIssueVariables(line, csVariableNames.getCs31(), IssueKeys.CA31);
            detectIssueVariables(line, csVariableNames.getCs32(), IssueKeys.CA32);
            detectIssueVariables(line, csVariableNames.getCs33(), IssueKeys.CA33);
            detectIssueVariables(line, csVariableNames.getCs34(), IssueKeys.CA34);
            detectIssueVariables(line, csVariableNames.getCs35(), IssueKeys.CA35);
            detectIssueVariables(line, csVariableNames.getCs37(), IssueKeys.CA37);
            detectIssueVariables(line, csVariableNames.getCs38(), IssueKeys.CA38);
            detectIssueVariables(line, csVariableNames.getCs39(), IssueKeys.CA39);
            detectIssueVariables(line, csVariableNames.getCs40(), IssueKeys.CA40);
            detectIssueVariables(line, csVariableNames.getCs41(), IssueKeys.CA41);
            detectIssueVariables(line, csVariableNames.getCs42(), IssueKeys.CA42);
            detectIssueVariables(line, csVariableNames.getCs43(), IssueKeys.CA43);
            detectIssueVariables(line, csVariableNames.getCs44(), IssueKeys.CA44);
            detectIssueVariables(line, csVariableNames.getCs45(), IssueKeys.CA45);
            detectIssueVariables(line, csVariableNames.getCs46(), IssueKeys.CA46);
            detectIssueCA47(line);
            detectIssueCA48(line);
        }
    }

    private void detectIssueCA06(String line) throws IssueException {
        String[] lines = line.split(" ");
        if (lines[lines.length - 1].equals(lines[lines.length - 1].toUpperCase())) {
            throw new IssueException(IssueKeys.CA06, "Uppercase the name and use _ to replace the space!");
        }
    }

    private void detectIssueCA47(String line) throws IssueException {
        String varName;
        String[] lines = line.split(" ");
        if (line.startsWith("public ")) {
            if (lines[1].equals("static")) {
                varName = lines[3];
            } else {
                varName = lines[2];
            }
            if (!varName.startsWith("_")) {
                throw new IssueException(IssueKeys.CA47, "Global variables must start with _!");
            }
        }
    }

    private void detectIssueCA48(String line) throws IssueException {
        String varName;
        String[] lines = line.split(" ");
        if (line.startsWith("private ")) {
            if (lines[1].equals("static")) {
                varName = lines[3];
            } else {
                varName = lines[2];
            }
        } else {
            varName = lines[1];
        }
        if (varName != null && !Character.isLetterOrDigit(varName.charAt(0))) {
            throw new IssueException(IssueKeys.CA48, "Local variables must start with normal character!");
        }
    }

    public void detectIssueVariables(String line, List<String[]> csX, RuleKey ruleKey) throws IssueException {
        if (!checkVariableName(line, csX)) {
            throw new IssueException(ruleKey, "Variable name for " + csX.get(0)[0] + " must start with " + csX.get(0)[1] + "!");
        }
    }

    private boolean checkVariableName(String line, List<String[]> csX) {
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
        for (String[] varCheckArgs : csX) {
            boolean isOK = false;
            boolean meetType = false;
            if (varCheckArgs[0].equals(varType) || varType.startsWith(varCheckArgs[0] + "<")) {
                meetType = true;
            }
            if (varName.startsWith(varCheckArgs[1]) || varName.startsWith("_" + varCheckArgs[1])) {
                isOK = true;
            }
            if (meetType && !isOK) {
                return false;
            }
        }
        return true;
    }

    private void detectIssueCA01(String line) throws IssueException {
        line = line.substring(0, line.indexOf('('));
        line = line.substring(line.lastIndexOf(' ') + 1);
        if (line.length() < 2) {
            throw new IssueException(IssueKeys.CA01, "This function is not Camel name!");
        }
        String firstChar = line.substring(0, 1);
        String lastChar = line.substring(line.length() - 2);
        if (!firstChar.equals(firstChar.toUpperCase())) {
            throw new IssueException(IssueKeys.CA01, "This function is not Camel name!");
        }
        if (!lastChar.equals(lastChar.toLowerCase())) {
            throw new IssueException(IssueKeys.CA01, "This function is not Camel name!");
        }
    }

    private void detectIssueCA36(String line) throws IssueException {
        String params = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
        if (StringUtils.isNotEmpty(params)) {
            String[] paramArr = params.split(",");
            for (int i = 0; i < paramArr.length; i++) {
                String[] paramAttr = paramArr[i].split(" ");
                String paramName = paramAttr[paramAttr.length - 1];
                if (!paramName.startsWith("p")) {
                    throw new IssueException(IssueKeys.CA36, "Function parameter must be start with [p]!");
                }
            }
        }
    }

    public String getMainClass(String line) {
        if (line.startsWith("class ")) {
            return line.substring(6);
        }
        return null;
    }

    public SOURCE_CATEGORY detectCategory(String line, String currentClass) {
        if (line.startsWith("//")) {
            return SOURCE_CATEGORY.COMMENT_LINE;
        }
        if (StringUtils.isEmpty(line)) {
            return SOURCE_CATEGORY.EMPTY;
        }
        if (line.startsWith("using ")) {
            return SOURCE_CATEGORY.IMPORT;
        }
        if (line.startsWith("namespace ")) {
            return SOURCE_CATEGORY.NAMESPACE;
        }
        if (line.startsWith("class ")) {
            return SOURCE_CATEGORY.CLASS_CONSTRUCT;
        }
        if (line.contains(" " + currentClass + "(")) {
            return SOURCE_CATEGORY.CLASS_CONSTRUCT;
        }
        if (line.startsWith("const ") || line.contains(" const ")) {
            return SOURCE_CATEGORY.CONSTANT;
        }
        if (isVariable(line)) {
            return SOURCE_CATEGORY.VARIABLE_DECLARE;
        }

        if (line.startsWith("public ") || line.startsWith("private ") || line.startsWith("protected ")) {
            if (line.indexOf('(') < line.indexOf(')')) {
                return SOURCE_CATEGORY.FUNCTION_CONSTRUCT;
            }
        }
        return SOURCE_CATEGORY.CODE;
    }

    private boolean isVariable(String line) {
        if (line.endsWith(";")) {
            String[] lines = line.split(" ");
            if (line.startsWith("public ") || line.startsWith("private ") || line.startsWith("protected ")) {
                if (lines.length > 2) {
                    return true;
                }
            } else {
                if (lines.length > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public enum SOURCE_CATEGORY {
        IMPORT,
        EMPTY,
        VARIABLE_DECLARE,
        NAMESPACE,
        CLASS_DECLARE,
        CLASS_CONSTRUCT,
        FUNCTION_CONSTRUCT,
        END_CONSTRUCT,
        CODE,
        COMMENT_LINE,
        CONSTANT,
    }
}
