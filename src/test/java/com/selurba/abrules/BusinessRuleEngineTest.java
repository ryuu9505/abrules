package com.selurba.abrules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BusinessRuleEngineTest {

    @Test
    void shouldHaveNoRulesInitially() {
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine engine = new BusinessRuleEngine(mockFacts);

        assertEquals(0, engine.count());
    }

    @Test
    void shouldAddTwoActions() {
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine engine = new BusinessRuleEngine(mockFacts);
        final Rule ruleSendEmailToSalesWhenCEO = RuleBuilder
                .when(facts -> "CEO".equals(facts.getFact("jobTitle")))
                .then(facts -> {
                    final var name = facts.getFact("name");
                    //Mailer.sendEmail("sales@company.com", "Relevant customer: " + name);
                });
        final Rule ruleForecastAmountByDealStage = RuleBuilder
                .when(facts -> "deal".equals(facts.getFact("type")))
                .then(facts -> {
                    var dealStage = Stage.valueOf(facts.getFact("stage"));
                    var amount = Double.parseDouble(facts.getFact("amount"));
                    var forecastedAmount = amount * switch (dealStage) {
                        case LEAD -> 0.2;
                        case EVALUATING -> 0.5;
                        case INTERESTED -> 0.8;
                        case CLOSED -> 1;
                    };
                    facts.setFact("forecastedAmount", String.valueOf(forecastedAmount));
                });

        engine.addRule(ruleSendEmailToSalesWhenCEO);
        engine.addRule(ruleForecastAmountByDealStage);

        assertEquals(2, engine.count());
    }

    @Test
    void shouldExecuteAnActionWithFacts() {
        final Rule mockRule = mock(Rule.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine engine = new BusinessRuleEngine(mockFacts);

        engine.addRule(mockRule);
        engine.run();

        verify(mockRule).perform(mockFacts);
    }
}