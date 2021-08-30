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

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Generates issues on all java files at line 1. This rule
 * must be activated in the Quality profile.
 */
public class CreateIssuesOnCsFilesSensor implements Sensor {

    private CreateIssuesOnCsHelper csHelper = new CreateIssuesOnCsHelper();

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Add custom's issues for all C# files");

        // optimisation to disable execution of sensor if project does
        // not contain C# files or if the example rule is not activated
        // in the Quality profile
        descriptor.onlyOnLanguage("cs");
        descriptor.createIssuesForRuleRepositories(CsRulesDefinition.REPOSITORY);
    }

    @Override
    public void execute(SensorContext context) {
        FileSystem fs = context.fileSystem();
        Iterable<InputFile> javaFiles = fs.inputFiles(fs.predicates().hasLanguage("cs"));
        for (InputFile javaFile : javaFiles) {
            int i = 1;
            try (BufferedReader br
                         = new BufferedReader(new InputStreamReader(javaFile.inputStream()))) {
                String line;
                String currentClass = " ";
                while ((line = br.readLine()) != null) {
                    csHelper.makeIssues(context, javaFile, line, i++, currentClass);
                    if (csHelper.getMainClass(line) != null) {
                        currentClass = csHelper.getMainClass(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
