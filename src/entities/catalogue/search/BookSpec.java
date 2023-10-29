package entities.catalogue.search;

import entities.catalogue.search.spec.Spec;

import java.util.HashMap;
import java.util.Map;

public class BookSpec {
    private Map<String, Spec> properties;

    public BookSpec(Map<String, Spec> specs) {
        if (specs == null) {
            this.properties = new HashMap<>();
        } else {
            this.properties = new HashMap<>(specs);
        }
    }

    public Spec getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, Spec> getProperties() {
        return properties;
    }

    public boolean matches(Map<String, Spec<?>> otherSpec) {
        for (Map.Entry<String, Spec<?>> property: otherSpec.entrySet()) {
            if (!properties.get(property.getKey()).matches(property.getValue())) {
                return false;
            }
        }
        return true;
    }
}
