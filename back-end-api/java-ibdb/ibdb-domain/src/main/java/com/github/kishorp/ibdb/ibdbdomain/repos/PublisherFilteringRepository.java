package com.github.kishorp.ibdb.ibdbdomain.repos;
import com.github.kishorp.ibdb.ibdbdomain.entity.Publisher;
import java.util.List;

/**
 * - Interface for custom filter method apart from standard Mongo Repo methods
 */
public interface PublisherFilteringRepository {

    /**
     * <li>Filters All Publishers by Name and Email</li>
     * <li>If no parameters provided, it will simply returns all available Publishers</li>
     *
     * @param name Name on which  filtering is done
     * @param email Email on which filtering is done
     * @return <li>When Params provided, Filtered list of Publishers.</li>
     *         <li> In no params provided then returns all available Publishers.</li>
     *         <li>Empty List when no records found matching params</li>
     */
    List<Publisher> filterByNameEmail(String name, String email);
}
