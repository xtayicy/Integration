package harry.base;

import java.util.Collection;

/**
 * 
 * @author harry
 *
 */
public interface IBaseDao<T,ID>{
	public int update(T t);
	
	public T get(ID pk);
	
	public int insert(T t);
	
	public int deleteById(Collection<ID> idList);
}
