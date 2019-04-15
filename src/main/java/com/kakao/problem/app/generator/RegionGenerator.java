package com.kakao.problem.app.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

public class RegionGenerator implements IdentifierGenerator, Configurable {

    private String prefix;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        prefix = "reg";
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String query = String.format("select %s from %s", session.getEntityPersister(object.getClass().getName(), object)
                        .getIdentifierPropertyName(), object.getClass().getSimpleName());

        Stream ids = session.createQuery(query).stream();
        Long max = ids.map(o -> Long.parseLong(String.valueOf(o).replace(prefix, "")))
                .mapToLong(o -> Long.parseLong(String.valueOf(o)))
                .max()
                .orElse(0L);

        return prefix+(max + 1);
    }
}
