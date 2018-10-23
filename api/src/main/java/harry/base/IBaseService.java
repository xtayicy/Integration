package harry.base;

import java.util.List;

/**
 * 
 * @author harry
 * @param <T>
 */
public interface IBaseService<T> {
	public void insert(T entity) throws RuntimeException;
	
	public List<T> selectAll();
	
	public void update(T entity) throws RuntimeException;
}
