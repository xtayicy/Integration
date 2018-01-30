package harry.base;

import java.util.List;

/**
 * 
 * @author harry
 * @param <T>
 */
public interface IBaseService<T> {
	void insert(T entity) throws RuntimeException;
	
	List<T> selectAll();
	
	void update(T entity) throws RuntimeException;
}
