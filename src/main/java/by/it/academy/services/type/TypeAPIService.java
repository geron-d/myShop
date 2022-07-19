package by.it.academy.services.type;

import by.it.academy.contants.Order;
import by.it.academy.entities.Type;
import by.it.academy.repositories.hiber.type.TypeAPIRepository;
import by.it.academy.repositories.hiber.type.TypeRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the by.it.academy.services.TypeService interface.
 *
 * @author Maxim Zhevnov
 */
public class TypeAPIService implements TypeService<Type> {
    private final Session session;
    private final TypeRepository<Type> typeRepository;

    /**
     * Creates a new {@link TypeAPIRepository} to manage objects of the given session.
     *
     * @param session        must not be {@literal null}.
     * @param typeRepository must not be {@literal null}.
     */
    public TypeAPIService(Session session, TypeRepository<Type> typeRepository) {
        this.session = session;
        this.typeRepository = typeRepository;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.TypeService#getTypeById
     */
    @Override
    public Optional<Type> getTypeById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Type> type = typeRepository.getTypeById(id);

        transaction.commit();

        return type;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.TypeService#saveType
     */
    @Override
    public Optional<Type> saveType(Type type) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Type> optionalType = typeRepository.saveType(type);

        transaction.commit();

        return optionalType;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.TypeService#deleteType
     */
    @Override
    public void deleteType(Type type) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        typeRepository.deleteType(type);

        transaction.commit();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.TypeService#getTypeByName
     */
    @Override
    public Optional<Type> getTypeByName(String typeName) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Type> optionalType = typeRepository.getTypeByName(typeName);

        transaction.commit();

        return optionalType;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.TypeService#getAllTypes
     */
    @Override
    public List<Type> getAllTypes(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Type> types = typeRepository.getAllTypes(order);

        transaction.commit();

        return types;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.TypeService#getTypeByValuableFields
     */
    @Override
    public Optional<Type> getTypeByValuableFields(Type type) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Type> optionalType = typeRepository.getTypeByValuableFields(type);

        transaction.commit();

        return optionalType;
    }
}
