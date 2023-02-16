package com.github.kishorp.ibdb.ibdbdomain.repos.authors;
import com.github.kishorp.ibdb.ibdbdomain.entity.Author;
import java.util.List;

/**
 *
 * - Interface for custom filter method apart from standard Mongo Repo methods
 */
public interface AuthorFilteringRepository {


    /**
     * <li>Filters All Authors by Name and Email</li>
     * <li>If no parameters provided, it will simply returns all available Authors</li>
     *
     * @param name Name on which  filtering is done
     * @param email Email on which filtering is done
     * @return <li>When Params provided, Filtered list of Authors.</li>
     *         <li> In no params provided then returns all available Authors.</li>
     *         <li>Empty List when no records found matching params</li>
     */
    List<Author> filterByNameEmail(String name, String email);

}
