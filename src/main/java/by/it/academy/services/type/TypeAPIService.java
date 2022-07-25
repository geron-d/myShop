package by.it.academy.services.type;

import by.it.academy.dtos.requests.type.TypeDTO;
import by.it.academy.entities.Type;
import by.it.academy.repositories.type.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementation of the by.it.academy.services.TypeService interface.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TypeAPIService implements TypeService {

    private final TypeRepository typeRepository;

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.TypeService#findType(Long id)
     */
    @Override
    public Type findType(Long id) {
        return typeRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.createType(TypeDTO dto)
     */
    @Override
    @Transactional
    public Long createType(TypeDTO dto) {
        Type type = buildType(dto);
        return typeRepository.save(type).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.updateType(Long id, TypeDTO dto)
     */
    @Override
    @Transactional
    public Long updateType(Long id, TypeDTO dto) {
        if (!checkType(id)) {
            throw new NoSuchElementException();
        }
        Type type = buildType(dto);
        type.setId(id);
        return typeRepository.save(type).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.deleteType(Long id)
     */
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.findType(String name)
     */
    @Override
    public Type findType(String name) {
        return typeRepository.findTypeByName(name).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.findTypes()
     */
    @Override
    public List<Type> findTypes() {
        return typeRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.searchTypes(String search)
     */
    @Override
    public List<Type> searchTypes(String search) {
        return typeRepository.searchAllByNameContains(search);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.findTypes(List<String> typeNames)
     */
    @Override
    @Transactional
    public List<Type> findTypes(List<String> typeNames) {
        return typeNames.stream()
                .map(this::findType)
                .collect(Collectors.toList());
    }

    /**
     * Check type by its id.
     *
     * @return true if such type exists and false when does not.
     */
    private boolean checkType(Long id) {
        return typeRepository.existsById(id);
    }

    /**
     * Build type by TypeDTO.
     *
     * @return type by TypeDTO.
     */
    private Type buildType(TypeDTO dto) {
        return Type.builder()
                .name(dto.getName())
                .build();
    }

}
