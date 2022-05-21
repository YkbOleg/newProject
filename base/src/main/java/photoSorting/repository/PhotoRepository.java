package photoSorting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import photoSorting.model.Photo;

import java.util.ArrayList;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    /**
     * Определение одинаковых файлов по хэшу
     * @param hash - Хэш файла
     * @return - Результат поиска совпадений
     */
    @Query("select count(ph)>0 " +
            "from Photo ph " +
            "where ph.hash = :hash")
    boolean existsIfHash(String hash);

    /**
     * Определение годов создания файлов (для выпадающего списка)
     * @return - Список годов
     */
    @Query("select distinct YEAR(ph.create) as year " +
            "from Photo ph")
    ArrayList<String> findYears();

    /**
     * Выбрать файлы по дате создания
     * @param year - Год создания файла
     * @return - Файлы за выбранный год
     */
    @Query("from Photo ph " +
            "where YEAR(ph.create) = :year")
    List<Photo> findYear(Integer year);
}