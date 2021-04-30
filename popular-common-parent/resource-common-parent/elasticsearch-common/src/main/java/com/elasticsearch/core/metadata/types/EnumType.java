package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjs
 */
public class EnumType extends AbstractType<EnumType> implements DataType {
    public static final String ID = "enum";
    private volatile List<Element> elements;
    private boolean multi;

    public EnumType() {
    }

    @Override
    public String getId() {
        return "enum";
    }

    @Override
    public String getName() {
        return "枚举";
    }

    public EnumType multi(boolean multi) {
        this.multi = multi;
        return this;
    }

    @Override
    public ValidateResult validate(Object value) {
        return this.elements == null ? ValidateResult.fail("值[" + value + "]不在枚举中") : (ValidateResult) this.elements.stream().filter((ele) -> {
            return this.match(value, ele);
        }).findFirst().map((e) -> {
            return ValidateResult.success(e.value);
        }).orElseGet(() -> {
            return ValidateResult.fail("值[" + value + "]不在枚举中");
        });
    }

    private boolean match(Object value, EnumType.Element ele) {
        if (value instanceof Map) {
            Map<Object, Object> mapVal = (Map) value;
            return this.match(mapVal.getOrDefault("value", mapVal.get("id")), ele);
        } else {
            return ele.value.equals(String.valueOf(value)) || ele.text.equals(String.valueOf(value));
        }
    }

    @Override
    public String format(Object value) {
        String stringVal = String.valueOf(value);
        return this.elements == null ? stringVal : (String) this.elements.stream().filter((ele) -> {
            return ele.value.equals(String.valueOf(value));
        }).findFirst().map(EnumType.Element::getText).orElse(stringVal);
    }

    public EnumType addElement(EnumType.Element element) {
        if (this.elements == null) {
            synchronized (this) {
                if (this.elements == null) {
                    this.elements = new ArrayList();
                }
            }
        }

        this.elements.add(element);
        return this;
    }

    public List<EnumType.Element> getElements() {
        return this.elements;
    }

    public boolean isMulti() {
        return this.multi;
    }

    public void setElements(List<EnumType.Element> elements) {
        this.elements = elements;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public static class Element {
        private String value;
        private String text;
        private String description;

        public static EnumType.Element of(String value, String text) {
            return of(value, text, (String) null);
        }

        public static EnumType.Element of(Map<String, String> map) {
            return of((String) map.get("value"), (String) map.get("text"), (String) map.get("description"));
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap();
            map.put("value", this.value);
            map.put("text", this.text);
            map.put("description", this.description);
            return map;
        }

        public String getValue() {
            return this.value;
        }

        public String getText() {
            return this.text;
        }

        public String getDescription() {
            return this.description;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @ConstructorProperties({"value", "text", "description"})
        private Element(String value, String text, String description) {
            this.value = value;
            this.text = text;
            this.description = description;
        }

        public static EnumType.Element of(String value, String text, String description) {
            return new EnumType.Element(value, text, description);
        }

        public Element() {
        }
    }
}
