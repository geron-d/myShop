package by.it.academy.services.type;

import by.it.academy.dtos.requests.type.TypeDTO;
import by.it.academy.entities.Type;
import by.it.academy.repositories.type.TypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing type service")
public class TypeAPIServiceTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeAPIService typeAPIService;

    Type type;

    @BeforeEach
    public void setType() {
        type = Type.builder()
                .id(1L)
                .name("wireless")
                .build();
    }

    public Type getSavingType() {
        return Type.builder()
                .name(type.getName())
                .build();
    }

    public TypeDTO getTypeDTO() {
        return TypeDTO.builder()
                .name(type.getName())
                .build();
    }

    public Type getAnotherType() {
        return Type.builder()
                .id(2L)
                .name("overhead")
                .build();
    }

    @Test
    @DisplayName("JUnit test for findType method with arg id when type exists " +
            "for returning type is not null")
    void checkFindTypeWithArgIdWhenTypeExistsReturnsNotNull() {
        Mockito.when(typeRepository.findById(type.getId())).thenReturn(Optional.ofNullable(type));
        Assertions.assertNotNull(typeAPIService.findType(type.getId()));
        Mockito.verify(typeRepository, Mockito.times(1)).findById(type.getId());
    }

    @Test
    @DisplayName("JUnit test for findType method with arg id when type exists")
    void checkFindTypeWithArgIdWhenTypeExists() {
        Mockito.when(typeRepository.findById(type.getId())).thenReturn(Optional.ofNullable(type));
        Assertions.assertEquals(type, typeAPIService.findType(type.getId()));
        Mockito.verify(typeRepository, Mockito.times(1)).findById(type.getId());
    }


    @Test
    @DisplayName("JUnit test for findType method with arg id when type does not exist")
    void checkFindTypeWithArgIdWhenTypeDoesNotExist() {
        Mockito.when(typeRepository.findById(type.getId())).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class, () -> typeAPIService.findType(type.getId()));
        Mockito.verify(typeRepository, Mockito.times(1)).findById(type.getId());
    }

    @Test
    @DisplayName("JUnit test for createType method with arg TypeDTO for returning type is not null")
    void checkCreateTypeWithArgDTOReturnsNotNull() {
        Type savingType = getSavingType();
        TypeDTO dto = getTypeDTO();
        Mockito.when(typeRepository.save(savingType)).thenReturn(type);
        Assertions.assertNotNull(typeAPIService.createType(dto));
        Mockito.verify(typeRepository, Mockito.times(1)).save(savingType);
    }

    @Test
    @DisplayName("JUnit test for createType method with arg TypeDTO")
    void checkCreateTypeWithArgDTO() {
        Type savingType = getSavingType();
        TypeDTO dto = getTypeDTO();
        Mockito.when(typeRepository.save(savingType)).thenReturn(type);
        Assertions.assertEquals(type.getId(), typeAPIService.createType(dto));
        Mockito.verify(typeRepository, Mockito.times(1)).save(savingType);
    }

    @Test
    @DisplayName("JUnit test for createType method with arg TypeDTO " +
            "when type with such name already exists")
    void checkCreateTypeWithArgDTOWhenTypeWithSuchNameAlreadyExists() {
        Type savingType = getSavingType();
        TypeDTO dto = getTypeDTO();
        Mockito.when(typeRepository.save(savingType)).thenAnswer(answer -> {
            throw new SQLIntegrityConstraintViolationException();
        });
        Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () ->
                typeAPIService.createType(dto));
        Mockito.verify(typeRepository, Mockito.times(1)).save(savingType);
    }

    @Test
    @DisplayName("JUnit test for updateType method with args id and TypeDTO when type with such id exists " +
            "for returning type is not null")
    void checkUpdateTypeWithArgsIdAndTypeDTOWhenTypeWithSuchIdExistsReturnsNotNull() {
        TypeDTO dto = getTypeDTO();
        Mockito.when(typeRepository.existsById(type.getId())).thenReturn(true);
        Mockito.when(typeRepository.save(type)).thenReturn(type);
        Assertions.assertNotNull(typeAPIService.updateType(type.getId(), dto));
        Mockito.verify(typeRepository, Mockito.times(1)).existsById(type.getId());
        Mockito.verify(typeRepository, Mockito.times(1)).save(type);
    }

    @Test
    @DisplayName("JUnit test for updateType method with args id and TypeDTO when type with such id exists")
    void checkUpdateTypeWithArgsIdAndTypeDTOWhenTypeWithSuchIdExists() {
        TypeDTO dto = getTypeDTO();
        Mockito.when(typeRepository.existsById(type.getId())).thenReturn(true);
        Mockito.when(typeRepository.save(type)).thenReturn(type);
        Assertions.assertEquals(type.getId(), typeAPIService.updateType(type.getId(), dto));
        Mockito.verify(typeRepository, Mockito.times(1)).existsById(type.getId());
        Mockito.verify(typeRepository, Mockito.times(1)).save(type);
    }

    @Test
    @DisplayName("JUnit test for updateType method with args id and TypeDTO " +
            "when type with such id exists and type with such name already exists")
    void checkUpdateTypeWithArgsIdAndTypeDTOWhenTypeWithSuchIdExistsAndTypeWithSuchNameAlreadyExists() {
        TypeDTO dto = getTypeDTO();
        Mockito.when(typeRepository.existsById(type.getId())).thenReturn(true);
        Mockito.when(typeRepository.save(type)).thenAnswer(answer -> {
            throw new SQLIntegrityConstraintViolationException();
        });
        Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () ->
                typeAPIService.updateType(type.getId(), dto));
        Mockito.verify(typeRepository, Mockito.times(1)).existsById(type.getId());
        Mockito.verify(typeRepository, Mockito.times(1)).save(type);
    }

    @Test
    @DisplayName("JUnit test for updateType method with args id and TypeDTO " +
            "when type with such id does not exists")
    void checkUpdateTypeWithArgsIdAndTypeDTOWhenTypeWithSuchIdDoesNotExists() {
        TypeDTO dto = getTypeDTO();
        Mockito.when(typeRepository.existsById(type.getId())).thenReturn(false);
        Assertions.assertThrows(NoSuchElementException.class,
                () -> typeAPIService.updateType(type.getId(), dto));
        Mockito.verify(typeRepository, Mockito.times(1)).existsById(type.getId());
    }

    @Test
    @DisplayName("JUnit test for deleteType method with arg id when type with such id exists")
    void checkDeleteTypeWithArgIdWhenTypeWithSuchIdExists() {
        Mockito.doNothing().when(typeRepository).deleteById(type.getId());
        typeAPIService.deleteType(type.getId());
        Mockito.verify(typeRepository, Mockito.times(1)).deleteById(type.getId());
    }

    @Test
    @DisplayName("JUnit test for findType method with arg String typeName when type exists " +
            "for returning type is not null")
    void checkFindTypeWithArgStringTypeNameWhenTypeExistsReturnsNotNull() {
        Mockito.when(typeRepository.findTypeByName(type.getName()))
                .thenReturn(Optional.ofNullable(type));
        Assertions.assertNotNull(typeAPIService.findType(type.getName()));
        Mockito.verify(typeRepository, Mockito.times(1)).findTypeByName(type.getName());
    }

    @Test
    @DisplayName("JUnit test for findType method with arg String typeName when type exists")
    void checkFindTypeWithArgStringTypeNameWhenTypeExists() {
        Mockito.when(typeRepository.findTypeByName(type.getName()))
                .thenReturn(Optional.ofNullable(type));
        Assertions.assertEquals(type, typeAPIService.findType(type.getName()));
        Mockito.verify(typeRepository, Mockito.times(1)).findTypeByName(type.getName());
    }


    @Test
    @DisplayName("JUnit test for findType method with arg String typeName when type does not exist")
    void checkFindTypeWithArgStringTypeNameWhenTypeDoesNotExist() {
        Mockito.when(typeRepository.findTypeByName(type.getName())).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> typeAPIService.findType(type.getName()));
        Mockito.verify(typeRepository, Mockito.times(1)).findTypeByName(type.getName());
    }

    @Test
    @DisplayName("JUnit test for findTypes method without args for returning not null")
    void checkFindTypesWithoutArgsReturnsNotNull() {
        Type anotherType = getAnotherType();
        List<Type> types = List.of(type, anotherType);
        Mockito.when(typeRepository.findAll()).thenReturn(types);
        Assertions.assertNotNull(typeAPIService.findTypes());
        Mockito.verify(typeRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findTypes method without args for returning empty")
    void checkFindTypesWithoutArgsReturnsEmpty() {
        Mockito.when(typeRepository.findAll()).thenReturn(List.of());
        Assertions.assertEquals(List.of(), typeAPIService.findTypes());
        Mockito.verify(typeRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findTypes method without args when types exist")
    void checkFindTypesWithoutArgsWhenTypesExist() {
        Type anotherType = getAnotherType();
        List<Type> types = List.of(type, anotherType);
        Mockito.when(typeRepository.findAll()).thenReturn(types);
        Assertions.assertEquals(types, typeAPIService.findTypes());
        Mockito.verify(typeRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findTypes method without args when types exist for size")
    void checkFindTypesWithoutArgsWhenTypesExistForSize() {
        Type anotherType = getAnotherType();
        List<Type> types = List.of(type, anotherType);
        Mockito.when(typeRepository.findAll()).thenReturn(types);
        Assertions.assertEquals(types.size(), typeAPIService.findTypes().size());
        Mockito.verify(typeRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findTypes method without args when types exist for first Type")
    void checkFindTypesWithoutArgsWhenTypesExistForFirstType() {
        Type anotherType = getAnotherType();
        List<Type> types = List.of(type, anotherType);
        Mockito.when(typeRepository.findAll()).thenReturn(types);
        Assertions.assertEquals(types.get(0), typeAPIService.findTypes().get(0));
        Mockito.verify(typeRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for searchTypes method with arg String search when result presents " +
            "for returning not null")
    void checkSearchTypesWithArgSearchWhenResultPresentsReturnsNotNull() {
        String search = "wire";
        List<Type> types = List.of(type);
        Mockito.when(typeRepository.searchAllByNameContains(search)).thenReturn(types);
        Assertions.assertNotNull(typeAPIService.searchTypes(search));
        Mockito.verify(typeRepository, Mockito.times(1)).searchAllByNameContains(search);
    }

    @Test
    @DisplayName("JUnit test for searchTypes method with arg String search when result does not present")
    void checkSearchTypesWithArgSearchWhenResultDoesNotPresent() {
        String search = "asdasdsdasd";
        Mockito.when(typeRepository.searchAllByNameContains(search)).thenReturn(List.of());
        Assertions.assertEquals(List.of(), typeAPIService.searchTypes(search));
        Mockito.verify(typeRepository, Mockito.times(1)).searchAllByNameContains(search);
    }

    @Test
    @DisplayName("JUnit test for searchTypes method with arg String search when result presents")
    void checkSearchTypesWithArgSearchWhenResultPresents() {
        String search = "e";
        Type anotherType = getAnotherType();
        List<Type> types = List.of(type, anotherType);
        Mockito.when(typeRepository.searchAllByNameContains(search)).thenReturn(types);
        Assertions.assertEquals(types, typeAPIService.searchTypes(search));
        Mockito.verify(typeRepository, Mockito.times(1)).searchAllByNameContains(search);
    }

    @Test
    @DisplayName("JUnit test for searchTypes method with arg String search when result presents for size")
    void checkSearchTypesWithArgSearchWhenResultPresentsForSize() {
        String search = "e";
        Type anotherType = getAnotherType();
        List<Type> types = List.of(type, anotherType);
        Mockito.when(typeRepository.searchAllByNameContains(search)).thenReturn(types);
        Assertions.assertEquals(types.size(), typeAPIService.searchTypes(search).size());
        Mockito.verify(typeRepository, Mockito.times(1)).searchAllByNameContains(search);
    }

    @Test
    @DisplayName("JUnit test for searchTypes method with arg String search when result presents " +
            "for first Type")
    void checkSearchTypesWithArgSearchWhenResultPresentsForFirstType() {
        String search = "e";
        Type anotherType = getAnotherType();
        List<Type> types = List.of(type, anotherType);
        Mockito.when(typeRepository.searchAllByNameContains(search)).thenReturn(types);
        Assertions.assertEquals(types.get(0), typeAPIService.searchTypes(search).get(0));
        Mockito.verify(typeRepository, Mockito.times(1)).searchAllByNameContains(search);
    }

    @Test
    @DisplayName("JUnit test for findTypes method with arg List String typeNames for returning not null")
    void checkFindTypesWithArgStringListTypeNamesReturnsNotNull() {
        List<String> typeNames = List.of(type.getName());
        Mockito.when(typeRepository.findTypeByName(typeNames.get(0)))
                .thenReturn(Optional.ofNullable(type));
        Assertions.assertNotNull(typeAPIService.findTypes(typeNames));
        Mockito.verify(typeRepository, Mockito.times(1)).findTypeByName(typeNames.get(0));
    }

    @Test
    @DisplayName("JUnit test for findTypes method with arg List String typeNames " +
            "when types does not exist")
    void checkFindTypesWithArgStringListTypeNamesWhenTypesDoesNotExist() {
        List<String> typeNames = List.of("headpasdfdshones");
        Mockito.when(typeRepository.findTypeByName(typeNames.get(0)))
                .thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class, () -> typeAPIService.findTypes(typeNames));
        Mockito.verify(typeRepository, Mockito.times(1)).findTypeByName(typeNames.get(0));
    }

    @Test
    @DisplayName("JUnit test for findTypes method with arg List String typeNames when types exist")
    void checkFindTypesWithoutArgStringListTypeNamesWhenTypesExist() {
        List<String> typeNames = List.of(type.getName());
        List<Type> types = List.of(type);
        Mockito.when(typeRepository.findTypeByName(typeNames.get(0)))
                .thenReturn(Optional.ofNullable(type));
        Assertions.assertEquals(types, typeAPIService.findTypes(typeNames));
        Mockito.verify(typeRepository, Mockito.times(1)).findTypeByName(typeNames.get(0));
    }

    @Test
    @DisplayName("JUnit test for findTypes method with argument List String typeNames " +
            "when types exist for size")
    void checkFindTypesWithoutArgStringListTypeNamesWhenTypesExistForSize() {
        List<String> typeNames = List.of(type.getName());
        List<Type> types = List.of(type);
        Mockito.when(typeRepository.findTypeByName(typeNames.get(0)))
                .thenReturn(Optional.ofNullable(type));
        Assertions.assertEquals(types.size(), typeAPIService.findTypes(typeNames).size());
        Mockito.verify(typeRepository, Mockito.times(1)).findTypeByName(typeNames.get(0));
    }

    @Test
    @DisplayName("JUnit test for findTypes method with argument List String typeNames " +
            "when types exist for first type")
    void checkFindTypesWithoutArgStringListTypeNamesWhenTypesExistForFirstType() {
        List<String> typeNames = List.of(type.getName());
        List<Type> types = List.of(type);
        Mockito.when(typeRepository.findTypeByName(typeNames.get(0)))
                .thenReturn(Optional.ofNullable(type));
        Assertions.assertEquals(types.get(0), typeAPIService.findTypes(typeNames).get(0));
        Mockito.verify(typeRepository, Mockito.times(1)).findTypeByName(typeNames.get(0));
    }

}
