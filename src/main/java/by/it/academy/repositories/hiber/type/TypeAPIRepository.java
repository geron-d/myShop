package by.it.academy.repositories.hiber.type;

import by.it.academy.contants.HQL;
import by.it.academy.contants.Order;
import by.it.academy.entities.Type;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TypeAPIRepository implements TypeRepository<Type> {
    Logger log = Logger.getLogger(TypeAPIRepository.class);
    private final Session session;

    public TypeAPIRepository(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Type> getTypeById(int id) {
        Optional<Type> type = Optional.empty();
        try {
            type = Optional.of(session.get(Type.class, id));
            log.info("TypeAPIRepository - method: getTypeById: " + type);
        } catch (NullPointerException e) {
            log.info("TypeAPIRepository - method: getTypeById: " + e);
            return type;
        }
        return type;
    }

    @Override
    public Optional<Type> saveType(Type type) {
        Optional<Type> optionalType = Optional.ofNullable(type);
        if (optionalType.isPresent()) {
            if ((Objects.isNull(optionalType.get().getId())
                    || !getTypeById(optionalType.get().getId()).isPresent())
                    && !getTypeByValuableFields(optionalType.get()).isPresent()) {
                session.persist(optionalType.get());
                log.info("TypeAPIRepository - method: saveType: persist: " + optionalType);
                return getTypeByValuableFields(optionalType.get());
            } else if (!getTypeByValuableFields(optionalType.get()).isPresent()) {
                session.merge(optionalType.get());
                log.info("TypeAPIRepository - method: saveType: merge: " + optionalType);
                return getTypeByValuableFields(optionalType.get());
            } else {
                log.info("TypeAPIRepository - method: saveType: type already exist");
                return getTypeByValuableFields(optionalType.get());
            }
        }
        return optionalType;
    }

    @Override
    public void deleteType(Type type) {
        Optional<Type> optionalType = Optional.ofNullable(type);

        if (optionalType.isPresent() && Objects.nonNull(optionalType.get().getId())) {
            session.delete(optionalType.get());
            log.info("TypeAPIRepository - method: deleteType: remove: " + optionalType);
        } else {
            log.info("TypeAPIRepository - method: deleteType: type doesn't exist");
        }
    }

    @Override
    public Optional<Type> getTypeByName(String typeName) {
        Optional<Type> type = Optional.empty();
        try {
            TypedQuery<Type> query = session.createQuery(HQL.GET_TYPE_BY_NAME_HQL, Type.class);
            query.setParameter("type", typeName);
            type = Optional.of(query.getSingleResult());
            log.info("TypeAPIRepository - method: getTypeByType: " + type);
        } catch (NoResultException e) {
            log.info("TypeAPIRepository - method: getTypeByType: " + e);
            return type;
        }
        return type;
    }

    @Override
    public List<Type> getAllTypes(Order order) {
        List<Type> types;

        TypedQuery<Type> query = session.createQuery(HQL.GET_ALL_TYPES_HQL, Type.class);
        TypedQuery<Type> queryDesc = session.createQuery(HQL.GET_ALL_TYPES_DESC_HQL, Type.class);
        types = order.equals(Order.ASC)
                ? query.getResultList()
                : queryDesc.getResultList();
        log.info("TypeAPIRepository - method: getAllTypes: " + types);

        return types;
    }

    @Override
    public Optional<Type> getTypeByValuableFields(Type type) {
        Optional<Type> optionalType = Optional.ofNullable(type);
        if (optionalType.isPresent()) {
            return getTypeByName(optionalType.get().getType());
        } else {
            return optionalType;
        }
    }

    public Session getSession() {
        return session;
    }
}
