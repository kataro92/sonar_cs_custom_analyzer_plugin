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

import org.sonar.api.rule.RuleKey;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rule.Severity;
import org.sonar.api.server.rule.RulesDefinition;

import static org.sonarsource.plugins.example.rules.IssueKeys.CS_LANGUAGE;

public class CsRulesDefinition implements RulesDefinition {

    public static final String REPOSITORY = "ominext";

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPOSITORY, CS_LANGUAGE).setName("My Custom C# Analyzer");

        bindRule(repository, IssueKeys.CA01, "10min", "5min");
        bindRule(repository, IssueKeys.CA09, "10min", "5min");
        bindRule(repository, IssueKeys.CA10, "10min", "5min");
        bindRule(repository, IssueKeys.CA11, "10min", "5min");
        bindRule(repository, IssueKeys.CA12, "10min", "5min");
        bindRule(repository, IssueKeys.CA13, "10min", "5min");
        bindRule(repository, IssueKeys.CA14, "10min", "5min");
        bindRule(repository, IssueKeys.CA15, "10min", "5min");
        bindRule(repository, IssueKeys.CA16, "10min", "5min");
        bindRule(repository, IssueKeys.CA17, "10min", "5min");
        bindRule(repository, IssueKeys.CA18, "10min", "5min");
        bindRule(repository, IssueKeys.CA19, "10min", "5min");
        bindRule(repository, IssueKeys.CA20, "10min", "5min");
        bindRule(repository, IssueKeys.CA21, "10min", "5min");
        bindRule(repository, IssueKeys.CA22, "10min", "5min");
        bindRule(repository, IssueKeys.CA23, "10min", "5min");
        bindRule(repository, IssueKeys.CA24, "10min", "5min");
        bindRule(repository, IssueKeys.CA25, "10min", "5min");
        bindRule(repository, IssueKeys.CA26, "10min", "5min");
        bindRule(repository, IssueKeys.CA27, "10min", "5min");
        bindRule(repository, IssueKeys.CA28, "10min", "5min");
        bindRule(repository, IssueKeys.CA29, "10min", "5min");
        bindRule(repository, IssueKeys.CA30, "10min", "5min");
        bindRule(repository, IssueKeys.CA31, "10min", "5min");
        bindRule(repository, IssueKeys.CA32, "10min", "5min");
        bindRule(repository, IssueKeys.CA33, "10min", "5min");
        bindRule(repository, IssueKeys.CA34, "10min", "5min");
        bindRule(repository, IssueKeys.CA35, "10min", "5min");
        bindRule(repository, IssueKeys.CA36, "10min", "5min");
        bindRule(repository, IssueKeys.CA37, "10min", "5min");
        bindRule(repository, IssueKeys.CA38, "10min", "5min");
        bindRule(repository, IssueKeys.CA39, "10min", "5min");
        bindRule(repository, IssueKeys.CA40, "10min", "5min");
        bindRule(repository, IssueKeys.CA41, "10min", "5min");
        bindRule(repository, IssueKeys.CA42, "10min", "5min");
        bindRule(repository, IssueKeys.CA43, "10min", "5min");
        bindRule(repository, IssueKeys.CA44, "10min", "5min");
        bindRule(repository, IssueKeys.CA45, "10min", "5min");
        bindRule(repository, IssueKeys.CA46, "10min", "5min");
        bindRule(repository, IssueKeys.CA47, "10min", "5min");
        bindRule(repository, IssueKeys.CA48, "10min", "5min");

        // don't forget to call done() to finalize the definition
        repository.done();
    }

    private void bindRule(NewRepository repository, RuleKey ruleKey, String gap, String effort) {
        NewRule x1Rule = repository.createRule(ruleKey.rule())
                .setName(ruleKey.toString())
                .setHtmlDescription(ruleKey.repository() + ":" + ruleKey.rule())
                .setTags("style", "custom", "ominext")
                .setStatus(RuleStatus.READY)
                .setSeverity(Severity.MINOR);
        x1Rule.setDebtRemediationFunction(x1Rule.debtRemediationFunctions().linearWithOffset(gap, effort));
    }
}
