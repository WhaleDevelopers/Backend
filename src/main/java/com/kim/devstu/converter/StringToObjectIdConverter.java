package com.kim.devstu.converter;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class StringToObjectIdConverter implements Converter<String, ObjectId> {

    @Override
    public ObjectId convert(String source) {
        // 들어온 문자열이 유효한지 먼저 확인 (null이나 공백 체크)
        if (!StringUtils.hasText(source))  return null;

        if (!ObjectId.isValid(source)) {
            throw new IllegalArgumentException("'" + source + "'은(는) 유효한 ObjectId 형식이 아닙니다.");
        }

        return new ObjectId(source);
    }
}
