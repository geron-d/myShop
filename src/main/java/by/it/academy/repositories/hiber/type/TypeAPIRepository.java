package by.it.academy.repositories.hiber.type;

import by.it.academy.contants.HQL;
import by.it.academy.contants.Order;
import by.it.academy.entities.Type;
import by.it.academy.repositories.hiber.category.CategoryAPIRepository;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the by.it.academy.repositories.hiber.TypeRepository interface.
 *
 * @author Maxim Zhevnov
 */
public class TypeAPIRepository implements TypeRepository<Type> {
    Logger log = Logger.getLogger(TypeAPIRepository.class);
    private final Session session;

    /**
     * Creates a new {@link TypeAPIRepository} to manage objects of the given session.
     *
     * @param session must not be {@literal null}.
     */
    public TypeAPIRepository(Session session) {
        this.session = session;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.TypeAPIRepository#getTypeById
     */
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

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.TypeAPIRepository#saveType
     */
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

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.TypeAPIRepository#deleteType
     */
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

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.TypeAPIRepository#getTypeByName
     */
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

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.TypeAPIRepository#getAllTypes
     */
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

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.TypeAPIRepository#getTypeByValuableFields
     */
    @Override
    public Optional<Type> getTypeByValuableFields(Type type) {
        Optional<Type> optionalType = Optional.ofNullable(type);
        if (optionalType.isPresent()) {
            return getTypeByName(optionalType.get().getType());
        } else {
            return optionalType;
        }
    }
}
