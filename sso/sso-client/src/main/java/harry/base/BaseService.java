package harry.base;

import java.util.Collection;
import java.util.List;

/**
 * 
 * @author harry
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T,ID> {
	public T get(ID pk);
	
	public void update(T t);
	
	public void save(T t);
	
	public void deleteById(List<ID> idList);
	
	public void save(Collection<T> ts);
}