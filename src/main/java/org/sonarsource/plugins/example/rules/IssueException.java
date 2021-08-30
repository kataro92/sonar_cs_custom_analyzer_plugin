package org.sonarsource.plugins.example.rules;

import org.sonar.api.rule.RuleKey;

public class IssueException extends Exception {
    private RuleKey ruleKey;

    public IssueException(RuleKey ruleKey, String errorMessage) {
        super(errorMessage);
        this.ruleKey = ruleKey;
    }

    public IssueException(RuleKey ruleKey) {
        this.ruleKey = ruleKey;
    }

    public RuleKey getRuleKey() {
        return ruleKey;
    }
}
