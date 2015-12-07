package com.zhytnik.library.security;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.AbstractFallbackMethodSecurityMetadataSource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class SecurityMetadataAnalyzer extends AbstractFallbackMethodSecurityMetadataSource {
    private Set<Class<? extends Annotation>> authorizeAttributes;

    public SecurityMetadataAnalyzer() {
        authorizeAttributes = new HashSet<>();
        authorizeAttributes.add(MinAccessed.class);
        authorizeAttributes.add(Accessed.class);
    }

    @Override
    protected Collection<ConfigAttribute> findAttributes(Method method, Class<?> targetClass) {
        return findAttributes(method);
    }

    @Override
    protected Collection<ConfigAttribute> findAttributes(Class<?> clazz) {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    private Collection<ConfigAttribute> findAttributes(Method method) {
        for (Class<? extends Annotation> annotationType : authorizeAttributes) {
            Annotation annotation = AnnotationUtils.findAnnotation(method, annotationType);
            if (isNull(annotation)) {
                continue;
            }
            if (annotation instanceof Accessed) {
                return extractConfigAttributes((Accessed) annotation);
            }
            if (annotation instanceof MinAccessed) {
                return extractConfigAttributes((MinAccessed) annotation);
            }
        }
        return null;
    }

    private Collection<ConfigAttribute> extractConfigAttributes(Accessed annotation) {
        Collection<ConfigAttribute> attributes = new HashSet<>();
        UserRole[] roles = annotation.value();
        for (UserRole role : roles) {
            attributes.add(role::getAuthority);
        }
        return attributes;
    }

    private Collection<ConfigAttribute> extractConfigAttributes(MinAccessed annotation) {
        Collection<ConfigAttribute> attributes = new HashSet<>();
        int minLevel = annotation.value().getSecurityLevel();
        for (UserRole role : UserRole.values()) {
            if (role.getSecurityLevel() >= minLevel) {
                attributes.add(role::getAuthority);
            }
        }
        return attributes;
    }
}
