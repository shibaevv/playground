package au.com.totemsoft.shoppingcart.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import au.com.totemsoft.shoppingcart.domain.PromotionalRule;
import au.com.totemsoft.shoppingcart.domain.ShoppingCart;
import au.com.totemsoft.shoppingcart.exception.PromotionalRuleException;
import au.com.totemsoft.shoppingcart.internal.PromotionalRuleService;

public class PromotionalRuleServiceImpl implements PromotionalRuleService {

    //private final groovy.lang.Script scriptOH;

    private final CompiledScript scriptOH;
    private final CompiledScript scriptSK;
    private final CompiledScript scriptBC;

    public PromotionalRuleServiceImpl() throws Exception {
        final ClassLoader classLoader = getClass().getClassLoader();
        //URL scriptUrl = classLoader.getResource("promo/rules.groovy");
        //script = new groovy.lang.GroovyShell().parse(scriptUrl.toURI());
        //
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine  = manager.getEngineByName("groovy");
        //
        URL scriptUrl = classLoader.getResource("promo/rule_OH.groovy");
        try (Reader reader = new InputStreamReader(scriptUrl.openStream());) {
            scriptOH = ((Compilable) engine).compile(reader);
        }
        scriptUrl = classLoader.getResource("promo/rule_SK.groovy");
        try (Reader reader = new InputStreamReader(scriptUrl.openStream());) {
            scriptSK = ((Compilable) engine).compile(reader);
        }
        scriptUrl = classLoader.getResource("promo/rule_BC.groovy");
        try (Reader reader = new InputStreamReader(scriptUrl.openStream());) {
            scriptBC = ((Compilable) engine).compile(reader);
        }
    }

    @Override
    public List<PromotionalRule> rules() throws PromotionalRuleException {
        return Stream.of(
             new PromotionalRule("rule_OH")
             , new PromotionalRule("rule_SK")
             , new PromotionalRule("rule_BC")
        ).collect(Collectors.toList());
    }

    @Override
    public void apply(ShoppingCart cart, List<PromotionalRule> rules) throws PromotionalRuleException {
        Map<String, Object> params = Collections.singletonMap("cart", cart);
        for (PromotionalRule rule : rules) {
            try {
                //invokeMethod(script, rule, cart);
                //
                invokeMethod(scriptOH, rule, params);
                invokeMethod(scriptSK, rule, params);
                invokeMethod(scriptBC, rule, params);
            } catch (Exception e) {
                throw new PromotionalRuleException(e);
            }
        }
    }

    @SuppressWarnings({ "unchecked", "unused" })
    private <T> T invokeMethod(groovy.lang.Script script, PromotionalRule rule, Object... params) {
        return (T) script.invokeMethod(rule.getName(), params);
    }

    @SuppressWarnings({ "unchecked" })
    private <T> T invokeMethod(CompiledScript script, PromotionalRule rule, Map<String, Object> params) throws ScriptException {
        Bindings bindings = new SimpleBindings(params);
        return (T) script.eval(bindings);
    }

}
