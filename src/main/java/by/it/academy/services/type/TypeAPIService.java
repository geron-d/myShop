package by.it.academy.services.type;

import by.it.academy.contants.Order;
import by.it.academy.entities.Type;
import by.it.academy.repositories.hiber.type.TypeRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TypeAPIService implements TypeService<Type> {
    private final Session session;
    private final TypeRepository<Type> typeRepository;

    public TypeAPIService(Session session, TypeRepository<Type> typeRepository) {
        this.session = session;
        this.typeRepository = typeRepository;
    }

    @Override
    public Optional<Type> getTypeById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Type> type = typeRepository.getTypeById(id);

        transaction.commit();

        return type;
    }

    @Override
    public Optional<Type> saveType(Type type) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Type> optionalType = typeRepository.saveType(type);

        transaction.commit();

        return optionalType;
    }

    @Override
    public void deleteType(Type type) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        typeRepository.deleteType(type);

        transaction.commit();
    }

    @Override
    public Optional<Type> getTypeByName(String typeName) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Type> optionalType = typeRepository.getTypeByName(typeName);

        transaction.commit();

        return optionalType;
    }

    @Override
    public List<Type> getAllTypes(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Type> types = typeRepository.getAllTypes(order);

        transaction.commit();

        return types;
    }

    @Override
    public Optional<Type> getTypeByValuableFields(Type type) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Type> optionalType = typeRepository.getTypeByValuableFields(type);

        transaction.commit();

        return optionalType;
    }
}
