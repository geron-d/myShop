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

@Slf4j
@Service
@RequiredArgsConstructor
public class TypeAPIService implements TypeService<Type> {

    private final TypeRepository typeRepository;

    @Override
    public Type findType(Long id) {
        return typeRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public Long createType(TypeDTO dto) {
        Type type = buildType(dto);
        return typeRepository.save(type).getId();
    }

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

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Type findType(String name) {
        return typeRepository.findTypeByName(name);
    }

    @Override
    public List<Type> findTypes() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> searchTypes(String search) {
        return typeRepository.searchAllByNameContains(search);
    }

    @Override
    @Transactional
    public List<Type> findTypes(List<String> typeNames) {
        return typeNames.stream()
                .map(this::findType)
                .collect(Collectors.toList());
    }

    private boolean checkType(Long id) {
        return typeRepository.existsById(id);
    }

    private Type buildType(TypeDTO dto) {
        return Type.builder()
                .name(dto.getName())
                .build();
    }

}
