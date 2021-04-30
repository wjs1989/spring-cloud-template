package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;

import java.util.Optional;

/**
 * @author wenjs
 */
public class FileType extends AbstractType<FileType> implements DataType, Converter<String> {
    public static final String ID = "file";
    private FileType.BodyType bodyType;

    public FileType() {
        this.bodyType = FileType.BodyType.url;
    }

    @Override
    public String getId() {
        return "file";
    }

    @Override
    public String getName() {
        return "文件";
    }

    public FileType bodyType(FileType.BodyType type) {
        this.bodyType = type;
        return this;
    }

    @Override
    public ValidateResult validate(Object value) {
        return ValidateResult.success(String.valueOf(value));
    }

    @Override
    public String format(Object value) {
        return String.valueOf(value);
    }

    public String convert(Object value) {
        return value == null ? null : String.valueOf(value);
    }

    public FileType.BodyType getBodyType() {
        return this.bodyType;
    }

    public void setBodyType(FileType.BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public static enum BodyType {
        url,
        base64,
        binary;

        private BodyType() {
        }

        public static Optional<BodyType> of(String name) {
            if (name == null) {
                return Optional.empty();
            } else {
                for (BodyType value : values()) {
                    if (value.name().equalsIgnoreCase(name)) {
                        return Optional.of(value);
                    }
                }
                return Optional.empty();
            }
        }
    }
}
