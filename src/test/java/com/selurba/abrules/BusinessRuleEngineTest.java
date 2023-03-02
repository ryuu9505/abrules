package com.selurba.abrules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BusinessRuleEngineTest {

    @Test
    void shouldHaveNoRulesInitially() {
        final BusinessRuleEngine engine = new BusinessRuleEngine();

        assertEquals(0, engine.count());
    }

    @Test
    void shouldAddTwoActions() {
        final BusinessRuleEngine engine = new BusinessRuleEngine();

        engine.addAction(() -> {});
        engine.addAction(() -> {});

        assertEquals(2, engine.count());
    }

    @Test
    void shouldExecuteOneAction() {
        final BusinessRuleEngine engine = new BusinessRuleEngine();
        final Action mockAction = mock(Action.class);

        engine.addAction(mockAction);
        engine.run();

        verify(mockAction).execute();
    }
}